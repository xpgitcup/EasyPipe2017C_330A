package cn.edu.cup.system

import cn.edu.cup.dictionary.BaseDataType
import cn.edu.cup.dictionary.DataKey
import cn.edu.cup.lps.HydraulicProject
import cn.edu.cup.lps.PipeNetwork
import cn.edu.cup.lps.hydraulic.HydraulicEdge
import cn.edu.cup.lps.hydraulic.HydraulicVertex
import cn.edu.cup.physical.PhysicalQuantity
import cn.edu.cup.physical.QuantityUnit
import cn.edu.cup.physical.UnitSystem
import cn.edu.cup.userLibrary.UserClass
import cn.edu.cup.userLibrary.UserLibrary
import cn.edu.cup.userLibrary.UserLibraryClassify
import grails.core.GrailsApplication
import grails.gorm.transactions.Transactional

@Transactional
class InitService {

    def dataSource

    //加载数据库初始化脚本
    def loadScripts(String dir) {
        def File sf = new File(dir)
        println "load scripts ${dir}"
        if (sf.exists()) {
            if (sf) {
                sf.eachFile { f ->
                    if (f.isFile()) {
                        executeScript(f)
                    }
                }
            }
        }
    }

    //执行附加脚本
    def executeScript(File sf) {
        //def File sf = new File(fileName)
        println "init - ${sf}"
        if (sf) {
            def db
            def sql = sf.text
            db = new groovy.sql.Sql(dataSource)
            //println "init - ${sql}"
            def lines = sql.split(";")
            lines.each() { it ->
                //println "line: ${it}"
                it = it.trim()
                if (!it.isEmpty()) {
                    db.executeUpdate(it)
                }
            }
        }
    }

    /*
    * 初始化物理量
    * */
    def initBasicPhysicalQuantity() {
        insertPhysicalQuantity("长度", "length", "L", "米", "m", true)
        insertPhysicalQuantity("质量", "mass", "M", "千克", "kg", true)
        insertPhysicalQuantity("时间", "time", "T", "秒", "s", true)
        insertPhysicalQuantity("电流", "current", "I", "安培", "A", true)
        insertPhysicalQuantity("热力学温度", "temperature", "K", "开尔文", "K", true)
        insertPhysicalQuantity("物质的量", "amount of substance", "n(v)", "摩尔", "mol", true)
        insertPhysicalQuantity("发光强度", "luminous intensity", "I(Iv)", "坎德拉", "cd", true)
        //初始化量纲
        PhysicalQuantity.list().each { e->
            e.dimension = "${e.initDimension()}"
            e.save()
        }
    }

    /*
    * 插入物理量
    * */
    def insertPhysicalQuantity(quantityName, englishName, symbol, unitName, unitSymbol, basic) {
        if (PhysicalQuantity.countByQuantityName(quantityName) == 0) {
            def pq = new PhysicalQuantity(
                    quantityName: quantityName,
                    englishName: englishName,
                    symbol: symbol,
                    unitName: unitName,
                    unitSymbol: unitSymbol,
                    basic: basic
            )
            pq.save()
        }
    }

    /*
    * 初始化系统单位制
    * */
    def initUnitSystem() {
        def si = [
                "L":"m",
                "M":"kg",
                "T":"s",
                "I":"A",
                "K":"K",
                "n(v)":"mol",
                "I(Iv)":"cd"
        ]
        def u = []
        if (UnitSystem.count()<1) {
            u.add(new UnitSystem(systemName: "SI", description: "国际单位制"))
            u.add(new UnitSystem(systemName: "English", description: "英制"))
            u.add(new UnitSystem(systemName: "自定义A", description: "自定义"))
            u.each {e->
                e.save()
            }
            println("初始化单位制系统...ok")
            if (QuantityUnit.count()<1) {
                PhysicalQuantity.findAllByBasic(true).each {e->
                    def qu = new QuantityUnit(
                            unitName: e.unitName,
                            symbol: si.get(e.symbol),
                            dimension: e.dimension,
                            factorA: 1,
                            factorB: 0,
                            unitSystem: u[0]
                    )
                    qu.save()
                }
                println("初始化物理量单位...ok")
            }
        }
    }

    /*
    * 插入物理量单位
    * */
    def insertQuantityUnit(unitName, englishName, symbol, dimension, factorA, factorB, unitSystem) {
        if (QuantityUnit.countByUnitName(unitName)<1) {
            def u = new QuantityUnit(unitName: unitName,
                    englishName: englishName,
                    symbol: symbol,
                    dimension: dimension,
                    factorA: factorA,
                    factorB: factorB,
                    unitSystem: unitSystem)
            u.save()
        }
    }

    /*
    * 初始化系统数据
    * */
    def initSystemData(domains) {
        println("初始化系统数据......")
        initBasicPhysicalQuantity()
        initUnitSystem()
        initSystemUsers()
        initSystemMenuItems(domains)
    }

    /*
    * 初始化系统菜单
    * */
    def initSystemMenuItems(domains) {
        if (SystemMenu.count() < 1) {
            def m0 = new SystemMenu(
                    menuContext: "底层管理",
                    menuAction: "#",
                    menuDescription: "对系统的菜单结构进行底层维护",
                    upMenuItem: null,
                    roleAttribute: "底层管理",
                    menuOrder: 0
            )
            m0.save(true)
            //----------------------------------------------------------------------------------------------------------
            //创建正对各个域类控制器的菜单
            domains.sort()
            domains.each() { e ->
                def m01 = new SystemMenu(
                        menuContext: "${e.name}",
                        menuAction: "${e.name}/index",
                        menuDescription: "对${e.name}属性进行维护",
                        upMenuItem: m0,
                        roleAttribute: "底层管理",
                        menuOrder: 0
                )
                m01.save(true)
            }
            def m011 = new SystemMenu(
                    menuContext: "系统状态",
                    menuAction: "Operation4SystemStatus",
                    menuDescription: "显示当前的系统状态",
                    upMenuItem: m0,
                    roleAttribute: "底层管理",
                    menuOrder: 0
            )
            m011.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m1 = new SystemMenu(
                    menuContext: "系统维护",
                    menuAction: "#",
                    menuDescription: "对系统的菜单结构进行用户友好的维护",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m1.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m11 = new SystemMenu(
                    menuContext: "属性维护",
                    menuAction: "Operation4SystemAttribute/index",
                    menuDescription: "对系统的用户属性进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m11.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m12 = new SystemMenu(
                    menuContext: "用户维护",
                    menuAction: "Operation4SystemUser/index",
                    menuDescription: "对系统的用户进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m12.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m13 = new SystemMenu(
                    menuContext: "菜单维护",
                    menuAction: "Operation4SystemMenu/index",
                    menuDescription: "对系统的菜单用户进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m13.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m14 = new SystemMenu(
                    menuContext: "日志维护",
                    menuAction: "Operation4SystemLog/index",
                    menuDescription: "对系统的日志进行用户友好的维护",
                    upMenuItem: m1,
                    menuOrder: 0
            )
            m14.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m2 = new SystemMenu(
                    menuContext: "公共服务",
                    menuAction: "#",
                    menuDescription: "对所有用户开放的功能",
                    upMenuItem: null,
                    roleAttribute: "公共服务",
                    menuOrder: 0
            )
            m2.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m21 = new SystemMenu(
                    menuContext: "社区沟通",
                    menuAction: "Operation4SystemChat/index",
                    menuDescription: "与系统中的用户对话",
                    upMenuItem: m2,
                    menuOrder: 0
            )
            m21.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m3 = new SystemMenu(
                    menuContext: "基础数据",
                    menuAction: "#",
                    menuDescription: "维护数据字典+物理单位",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m3.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m31 = new SystemMenu(
                    menuContext: "数据字典",
                    menuAction: "Operation4DataKey/index",
                    menuDescription: "数据字典维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m31.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m32 = new SystemMenu(
                    menuContext: "数据维护",
                    menuAction: "Operation4Data/index",
                    menuDescription: "数据维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m32.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m33 = new SystemMenu(
                    menuContext: "单位维护",
                    menuAction: "Operation4Physical/index",
                    menuDescription: "物理单位维护",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m33.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m34 = new SystemMenu(
                    menuContext: "用户库维护",
                    menuAction: "operation4UserLibrary/index",
                    menuDescription: "维护用户代码库",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m34.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m35 = new SystemMenu(
                    menuContext: "数据字典A维护",
                    menuAction: "operation4DataKeyA/index",
                    menuDescription: "维护数据字典（新）",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m35.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m36 = new SystemMenu(
                    menuContext: "数据A维护",
                    menuAction: "operation4Dictionary/index",
                    menuDescription: "维护数据字典（新）",
                    upMenuItem: m3,
                    menuOrder: 0
            )
            m36.save(true)
            //----------------------------------------------------------------------------------------------------------
            //----------------------------------------------------------------------------------------------------------
            def m4 = new SystemMenu(
                    menuContext: "管道模拟",
                    menuAction: "#",
                    menuDescription: "管道模拟",
                    upMenuItem: null,
                    roleAttribute: "系统维护",
                    menuOrder: 0
            )
            m4.save(true)
            //----------------------------------------------------------------------------------------------------------
            def m41 = new SystemMenu(
                    menuContext: "管道",
                    menuAction: "Operation4PipeSimulation/index",
                    menuDescription: "管道数据维护",
                    upMenuItem: m4,
                    menuOrder: 0
            )
            m41.save(true)
        }
    }

    /**
     * 初始化用户数据
     **/
    def initSystemUsers() {
        if (SystemUser.count() < 1) {
            newUser("李晓平", "3764", '底层管理 系统维护 公共服务')
            newUser("宫敬", "2156", '底层管理 系统维护 公共服务')
            newUser("吴海浩", "3181", '底层管理 系统维护 公共服务')

            newUser("洪炳沅", "527527", '底层管理 系统维护 公共服务')
            newUser("闵元", "519519", '底层管理 系统维护 公共服务')
            newUser("苏越", "519519", '底层管理 系统维护 公共服务')
            newUser("李愚", "519519", '底层管理 系统维护 公共服务')
            newUser("周艳红", "519519", '底层管理 系统维护 公共服务')
            newUser("万洋洋", "519519", '底层管理 系统维护 公共服务')
            newUser("韦宝成", "519519", '底层管理 系统维护 公共服务')
            newUser("王茀玺", "519519", '底层管理 系统维护 公共服务')
            newUser("金浩", "519519", '底层管理 系统维护 公共服务')
        }
    }

    private void newUser(userName, password, attribute) {
        def u5 = new SystemUser(userName: userName, password: password, roleAttribute: attribute)
        u5.save(true)
    }

    /**
     * 填充测试数据
     */
    def fillSamples() {
        println("填充测试数据......")
        //用户
        fillSampleUsers()
        //属性
        fileSampleAttributes()
        //菜单
        fillSampleMenus()
        //对话
        fillSampleChat()
        //数据字典
        //fillSampleDataKey()
        //程序标题
        fillSampleTitle()
        //用户类库
        fillSampleUserLibrary()
        //水力学模拟
        //fillSampleHydraulicSimulation()
    }

    /*
    * 水力学模拟
    * */
    def fillSampleHydraulicSimulation() {
        for (int i=0; i<15; i++) {
            def pn = new PipeNetwork(name: "管道${i}")
            pn.save(true)

            def hvs = []
            for (int j=0; j<8; j++) {
                def hv = new HydraulicVertex(name: "节点${i}-${j}", pipeNetwork: pn)
                hv.save(true)
                hvs.add(hv)
            }

            for (int ii=0; ii<hvs.size(); ii++) {
                if (ii>0) {
                    def he = new HydraulicEdge(start: hvs[ii-1], end: hvs[ii])
                    he.save(true)
                    println("${he}")
                }
            }

            /*
            def hes = pn.hydraulicVertexes
            println("开始创建边...")
            hes.eachWithIndex{ HydraulicVertex entry, int ii ->
                if (ii>0) {
                    def he = new HydraulicEdge(start: hes[ii-1], end: hes[ii])
                    he.save(true)
                    println("${he}")
                }
            }
            */  // 这个不工作。。。

            def h = new HydraulicProject(name: "管道${i}模拟工程", pipeNetwork: pn)
            h.save(true)

        }

        /*
        def pns = PipeNetwork.list()
        pns.each {p->
            def hes = p.hydraulicVertexes
            hes.eachWithIndex{ HydraulicVertex entry, int ii ->
                if (ii>0) {
                    def he = new HydraulicEdge(start: hes[ii-1], end: hes[ii])
                    he.save(true)
                    println("${he}")
                }
            }
        }
        */ //不工作

    }

    /*
    * 用户类库
    * */
    def fillSampleUserLibrary() {
        for (int i=0; i<15; i++) {
            def u = new UserLibraryClassify(
                    name: "测试${i}",
                    path: "TestPath${i}",
                    description: "这是测试${i}"
            )
            u.save()
            for (int j=0; j<3; j++) {
                def uu = new UserLibrary(
                        name: "测试类${i}",
                        description: "属于这是测试${i}",
                        fileName: "测试类${i}文件",
                        developer: "${i}",
                        uploadDate: new Date(),
                        userLibraryClassify: u
                )
                uu.save()
                for (int k=0; k<3; k++) {
                    def uuu = new UserClass(
                            name: "用户类${i}",
                            description: "用户类${i}--",
                            userLibrary: uu
                    )
                    uuu.save()
                }
            }
        }
    }

    def fillSampleTitle() {
        println("初始化系统标题......")
        if (SystemTitle.count()<1) {
            def systemTitle = new SystemTitle(
                    applicationTitle: "EasyPipeNetwork 管网模拟种子程序",
                    applicationLogo: "cuplogoA.png",
                    applicationLayout: "mainEasyUI"
            )
            systemTitle.save(true)
            //----------------------------------------------------------------------------------------------------------
            if (SystemSponser.countBySystemTitle(systemTitle)<1) {
                newSponser(systemTitle, "中国石油大学", "cuplogoA.png")
                newSponser(systemTitle, "中海油", "logo_cnooc.png")
                newSponser(systemTitle, "中联煤", "logo_cbm.png")
            }
            //----------------------------------------------------------------------------------------------------------
            if (SystemCarousel.countBySystemTitle(systemTitle)<1) {
                newCarousel(systemTitle, "课题组", "课题组.jpg")
                newCarousel(systemTitle, "多相流", "多相流.png")
                newCarousel(systemTitle, "抽油机", "u68.jpg")
            }
        }
    }

    private void newSponser(systemTitle, name, logo) {
        def ns = new SystemSponser(systemTitle: systemTitle, name: name, logo: logo)
        ns.save(true)
    }

    private void newCarousel(systemTitle, name, png) {
        def nc = new SystemCarousel(systemTitle: systemTitle, name: name, imageName: png)
        nc.save(true)
    }

    private void fillSampleDataKey() {
        println("测试数据字典的数据...")
        def dw = ["kg", "m", "s", "MPa", "m^3/s", "kg/s"]
        for (int i=0; i<30; i++) {
            def d = new DataKey(
                    keyContext: "key${i}",
                    dataValueType: BaseDataType.project
            )
            d.save(true)
            for (int j=0; j<3; j++) {
                def dd = new DataKey(
                        keyContext: "key${i}_${j}",
                        dataValueType: BaseDataType.projectCase,
                        upKey: d
                )
                dd.save(true)
                for (int k=0; k<3; k++) {
                    def ddd = new DataKey(
                            keyContext: "key${i}_${j}_${k}",
                            dataValueType: BaseDataType.dataModel,
                            upKey: d
                    )
                    ddd.save(true)
                    for (int l=0; l<5; l++) {
                        def dddd = new DataKey(
                                keyContext: "key${i}_${j}_${k}_${l}",
                                dataValueType: BaseDataType.simpleData,
                                quantityUnit: dw[(i+l)%6],
                                upKey: ddd
                        )
                        dddd.save(true)
                    }
                }
            }
        }
    }

    private void fillSampleChat() {
        for (int i = 0; i < 20; i++) {
            def c = new SystemChat(
                    speaker: "李晓平",
                    speakTo: "张${i}李${i + 1}",
                    message: " I speak to 张${i}李${i + 1} that ${i * i}",
                    startTime: new Date()
            )
            c.save(true)
            def d = new SystemChat(
                    speakTo: "李晓平",
                    speaker: "张${i}李${i + 1}",
                    message: "张${i}李${i + 1} tell me ${i * i}",
                    startTime: new Date()
            )
            d.save(true)
        }
    }

    private void fillSampleMenus() {
        if (SystemMenu.count() < 1) {
            println("填充测试性的菜单数据......")

            println("${GrailsApplication.simpleName}")
            println("${GrailsApplication.APPLICATION_ID}")
            println("${Application.properties.name}")
            //println("${Application.metaPropertyValues}")

            for (int i = 0; i < 10; i++) {
                def mm = new SystemMenu(
                        menuContext: "菜单${i}",
                        menuAction: "动作${i}",
                        menuDescription: "描述：${i}",
                        upMenuItem: null,
                        menuOrder: i
                )
                mm.save(true)

                for (int j = 0; j < i; j++) {
                    def mmn = new SystemMenu(
                            menuContext: "菜单${i}.${j}",
                            menuAction: "动作${i}.${j}",
                            menuDescription: "描述：${i}.${j}",
                            upMenuItem: mm,
                            menuOrder: j
                    )
                    mmn.save(true)
                }
            }
        }
    }

    private void fileSampleAttributes() {
        if (SystemAttribute.count() < 1) {
            println("测试性的属性...")
            def attributeA = new SystemAttribute(name: "系统操作权限")
            attributeA.save(true)
            for (int i = 0; i < 10; i++) {
                def aa = new SystemAttribute(name: "权限${i}", upAttribute: attributeA)
                aa.save(true)
            }
            def attributeB = new SystemAttribute(name: "读取权限")
            attributeB.save(true)
            for (int i = 0; i < 10; i++) {
                def aa = new SystemAttribute(name: "读取权限${i}", upAttribute: attributeB)
                aa.save(true)
            }
        }
    }

    private void fillSampleUsers() {
        println("测试性的用户...")
        for (int i = 0; i < 20; i++) {
            def u = new SystemUser(userName: "张${i}李${i + 1}", password: "1")
            u.save(true)
        }
    }

}
