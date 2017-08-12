package cn.edu.cup.os4lps

import cn.edu.cup.lps.HydraulicProject
import cn.edu.cup.lps.PipeNetwork
import cn.edu.cup.lps.hydraulic.AmbientTemperature
import cn.edu.cup.lps.hydraulic.HydraulicVertex
import cn.edu.cup.lps.hydraulic.MileageAndElevation
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class Operation4PipeSimulationController {

    def commonService

    @Transactional
    def importAmbientTemperature() { //PipeNetwork pipeNetwork, AmbientTemperature ambientTemperatures
        println("${params}")
        def ambientTemperatures = new AmbientTemperature(name: params.name, start: params.start, end: params.end, pipeNetwork: params.pipeNetwork)
        ambientTemperatures.save(true)
        println("创建高程里程：${ambientTemperatures}")
        //--------------------------------------------------------------------------------------------------------------
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        //--------------------------------------------------------------------------------------------------------------
        //commonService.importObjectFromExcelFile(mae, sf)
        //--------------------------------------------------------------------------------------------------------------
        ambientTemperatures.importFromeExcelFile(sf)
        //--------------------------------------------------------------------------------------------------------------
        redirect(action: 'index')
    }

    //AmbientTemperature===============================================================================================
    def prepareImportAmbientTemperature(PipeNetwork pipeNetwork) {
        def ambientTemperature = new AmbientTemperature(
                start: pipeNetwork.hydraulicVertexes.first(),
                end: pipeNetwork.hydraulicVertexes.last(),
                pipeNetwork: pipeNetwork
        )
        if (request.xhr) {
            render(template: "prepareImportAmbientTemperature", model: [pipeNetwork: pipeNetwork, ambientTemperature: ambientTemperature])
        } else {
            model:
            [pipeNetwork: pipeNetwork, ambientTemperature: ambientTemperature]
        }
    }

    /*
    * 统计记录个数
    * */

    def countAmbientTemperature() {
        def count = AmbientTemperature.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * AmbientTemperature
    * */

    def listAmbientTemperature() {
        def ambientTemperatureList = AmbientTemperature.list(params)
        if (request.xhr) {
            render(template: 'listAmbientTemperature', model: [ambientTemperatureList: ambientTemperatureList])
        } else {
            respond ambientTemperatureList
        }
    }

    /*
    * 创建对象
    * */

    def createAmbientTemperature(AmbientTemperature ambientTemperature) {
        def newAmbientTemperature = new AmbientTemperature()
        if (request.xhr) {
            render(template: 'createAmbientTemperature', model: [ambientTemperature: newAmbientTemperature])
        } else {
            respond newAmbientTemperature
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteAmbientTemperature(AmbientTemperature ambientTemperature) {
        ambientTemperature.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateAmbientTemperature(AmbientTemperature ambientTemperature) {
        println("准备更新：${ambientTemperature}")
        ambientTemperature.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editAmbientTemperature(AmbientTemperature ambientTemperature) {
        if (request.xhr) {
            render(template: 'editAmbientTemperature', model: [ambientTemperature: ambientTemperature])
        } else {
            respond ambientTemperature
        }
    }

    //MileageAndElevation===============================================================================================
    /*
    * 统计记录个数
    * */

    def countMileageAndElevation() {
        def count = MileageAndElevation.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列出对象
    * */

    def listMileageAndElevation() {
        def mileageAndElevationList = MileageAndElevation.list(params)
        if (request.xhr) {
            render(template: 'listMileageAndElevation', model: [mileageAndElevationList: mileageAndElevationList])
        } else {
            respond mileageAndElevationList
        }
    }

    /*
    * 创建对象
    * */

    def createMileageAndElevation(MileageAndElevation mileageAndElevation) {
        def newMileageAndElevation = new MileageAndElevation()
        if (request.xhr) {
            render(template: 'createMileageAndElevation', model: [mileageAndElevation: newMileageAndElevation])
        } else {
            respond newMileageAndElevation
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteMileageAndElevation(MileageAndElevation mileageAndElevation) {
        mileageAndElevation.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateMileageAndElevation(MileageAndElevation mileageAndElevation) {
        println("准备更新：${mileageAndElevation}")
        mileageAndElevation.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editMileageAndElevation(MileageAndElevation mileageAndElevation) {
        if (request.xhr) {
            render(template: 'editMileageAndElevation', model: [mileageAndElevation: mileageAndElevation])
        } else {
            respond mileageAndElevation
        }
    }

    //HydraulicVertex===================================================================================================
    /*
    * 统计记录个数
    * */

    def countHydraulicVertex() {
        def count = HydraulicVertex.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列出对象
    * */

    def listHydraulicVertex() {
        def hydraulicVertexList = HydraulicVertex.list(params)
        if (request.xhr) {
            render(template: 'listHydraulicVertex', model: [hydraulicVertexList: hydraulicVertexList])
        } else {
            respond hydraulicVertexList
        }
    }

    /*
    * 创建对象
    * */

    def createHydraulicVertex() {
        def newHydraulicVertex = new HydraulicVertex()
        if (request.xhr) {
            render(template: 'createHydraulicVertex', model: [hydraulicVertex: newHydraulicVertex])
        } else {
            respond newHydraulicVertex
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteHydraulicVertex(HydraulicVertex hydraulicVertex) {
        hydraulicVertex.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateHydraulicVertex(HydraulicVertex hydraulicVertex) {
        println("准备更新：${hydraulicVertex}")
        hydraulicVertex.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editHydraulicVertex(HydraulicVertex hydraulicVertex) {
        if (request.xhr) {
            render(template: 'editHydraulicVertex', model: [hydraulicVertex: hydraulicVertex])
        } else {
            respond hydraulicVertex
        }
    }

    //PipeNetwork-------------------------------------------------------------------------------------------------------

    /*
    * downLoadTemplate
    * 下载数据模型的模板
    * */

    def downLoadTemplate() {
        def rootPath = servletContext.getRealPath("/")
        def fileName = PipeNetwork.createTemplate(rootPath)
        println("${fileName}")
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }

    /*
    * 准备导入高程里程
    * */

    def prepareImportMileageAndElevation(PipeNetwork pipeNetwork) {
        def mileageAndElevation = new MileageAndElevation(
                start: pipeNetwork.hydraulicVertexes.first(),
                end: pipeNetwork.hydraulicVertexes.last(),
                pipeNetwork: pipeNetwork
        )
        if (request.xhr) {
            render(template: "prepareImportMileageAndElevation", model: [pipeNetwork: pipeNetwork, mileageAndElevation: mileageAndElevation])
        } else {
            model:
            [pipeNetwork: pipeNetwork, mileageAndElevation: mileageAndElevation]
        }
    }

    @Transactional
    def importMileageAndElevation() { //PipeNetwork pipeNetwork, MileageAndElevation mileageAndElevation
        println("${params}")
        def mileageAndElevation = new MileageAndElevation(name: params.name, start: params.start, end: params.end, pipeNetwork: params.pipeNetwork)
        mileageAndElevation.save(true)
        println("创建高程里程：${mileageAndElevation}")
        //--------------------------------------------------------------------------------------------------------------
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        //--------------------------------------------------------------------------------------------------------------
        //commonService.importObjectFromExcelFile(mae, sf)
        //--------------------------------------------------------------------------------------------------------------
        mileageAndElevation.importFromeExcelFile(sf)
        //--------------------------------------------------------------------------------------------------------------
        redirect(action: 'index')
    }

    /*
    * 导入管道
    * */

    def prepareImportFromExcel(PipeNetwork pipeNetwork) {
        if (request.xhr) {
            render(template: "prepareImportFromExcel", model: [pipeNetwork: pipeNetwork])
        } else {
            model:
            [pipeNetwork: pipeNetwork]
        }
    }

    @Transactional
    def importFromExcel(PipeNetwork pipeNetwork) {
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        pipeNetwork.importFromExcel(sf)
        redirect(action: 'index')
    }

    /*
    * 导出管道
    * */

    def exportToExcel(PipeNetwork pipeNetwork) {
        def rootPath = servletContext.getRealPath("/")
        def excelName = pipeNetwork.exportPipeNetworkToExcel(rootPath)
        params.downLoadFileName = excelName
        commonService.downLoadFile(params)
    }

    /*
    * 显示管道的纵断面图
    * */

    def showPipeNetworkProfile(PipeNetwork pipeNetwork) {
        def profile = [:]

        profile.name = "${pipeNetwork.name}--纵断面图"

        def data = []

        pipeNetwork.mileageAndElevations.each { ms ->
            ms.elevationPoints.each { e ->
                data.add([e.mileage, e.elevation])
            }
        }

        profile.data = data

        println("${data}")

        if (request.xhr) {
            render profile as JSON
        } else {
            model:
            [data: data]
        }
    }

    /*
    * 将管道显示为Json格式
    * */

    def showPipeNetworkAsJson(PipeNetwork pipeNetwork) {
        def p = [:]
        p.put("nanme", pipeNetwork.name)
        //p.put("nodes", nodes)
        p.put("nodes", pipeNetwork.hydraulicVertexes)
        p.put("links", pipeNetwork.edges());
        if (request.xhr) {
            render p as JSON
        } else {
            model:
            [pipeNetwork: p]
        }
    }

    /*
    * 统计记录个数
    * */

    def countPipeNetwork() {
        def count = PipeNetwork.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列出对象
    * */

    def listPipeNetwork() {
        def pipeNetworkList = PipeNetwork.list(params)
        if (request.xhr) {
            render(template: 'listPipeNetwork', model: [pipeNetworkList: pipeNetworkList])
        } else {
            respond pipeNetworkList
        }
    }

    /*
    * 创建对象
    * */

    def createPipeNetwork(PipeNetwork pipeNetwork) {
        def newPipeNetwork = new PipeNetwork()
        if (request.xhr) {
            render(template: 'createPipeNetwork', model: [pipeNetwork: newPipeNetwork])
        } else {
            respond newPipeNetwork
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deletePipeNetwork(PipeNetwork pipeNetwork) {
        pipeNetwork.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updatePipeNetwork(PipeNetwork pipeNetwork) {
        println("准备更新：${pipeNetwork}")
        pipeNetwork.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editPipeNetwork(PipeNetwork pipeNetwork) {
        if (request.xhr) {
            render(template: 'editPipeNetwork', model: [pipeNetwork: pipeNetwork])
        } else {
            respond pipeNetwork
        }
    }

    //HydrauliProject---------------------------------------------------------------------------------------------------
    /*
    * 统计记录个数
    * */

    def countHydraulicProject() {
        def count = HydraulicProject.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列出对象
    * */

    def listHydraulicProject() {
        def hydraulicProjectList = HydraulicProject.list(params)
        if (request.xhr) {
            render(template: 'listHydraulicProject', model: [hydraulicProjectList: hydraulicProjectList])
        } else {
            respond hydraulicProjectList
        }
    }

    /*
    * 创建对象
    * */

    def createHydraulicProject() {
        def newHydraulicProject = new HydraulicProject()
        if (request.xhr) {
            render(template: 'createHydraulicProject', model: [hydraulicProject: newHydraulicProject])
        } else {
            respond newHydraulicProject
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteHydraulicProject(HydraulicProject hydraulicProject) {
        hydraulicProject.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateHydraulicProject(HydraulicProject hydraulicProject) {
        println("准备更新：${hydraulicProject}")
        hydraulicProject.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editHydraulicProject(HydraulicProject hydraulicProject) {
        if (request.xhr) {
            render(template: 'editHydraulicProject', model: [hydraulicProject: hydraulicProject])
        } else {
            respond hydraulicProject
        }
    }

    def index() {}
}
