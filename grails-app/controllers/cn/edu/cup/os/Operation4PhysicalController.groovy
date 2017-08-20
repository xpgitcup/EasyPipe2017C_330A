package cn.edu.cup.os

import cn.edu.cup.physical.PhysicalQuantity
import cn.edu.cup.physical.QuantityUnit
import cn.edu.cup.physical.UnitSystem
import cn.edu.cup.system.SystemUser
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class Operation4PhysicalController {

    def cookieService

    /*
    * 清除当前的物理量选择
    * */
    def clearCurrentPhysicalQuantity() {
        session.removeAttribute("currentPhysicalQuantity")
        redirect(action: "index")
    }

    //------------------------------------------------------------------------------------------------------------------
    //整体逻辑关系维护
    def selectCurrentPhysicalQuantity(PhysicalQuantity physicalQuantity) {
        session.currentPhysicalQuantity = physicalQuantity
        def tab = cookieService.getCookie("currentTabPhysicalDiv")
        println("当前-->" + tab)
        response.setCookie('currentTabPhysicalDiv', "单位维护页面")
        //cookieService.setCookie("currentTabPhysicalDiv", "单位维护页面")
        //cookieService.setCookie([name: "currentTabPhysicalDiv", value: "单位维护页面", path:  '/'])
        redirect(action: "index")
    }

    //------------------------------------------------------------------------------------------------------------------
    //UnitSystem
    /*
    * 统计记录个数
    * */
    def countUnitSystem() {
        def count = UnitSystem.count()    //这是必须调整的
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
    def listUnitSystem() {
        def unitSystemList = UnitSystem.list(params)
        if (request.xhr) {
            render(template: 'listUnitSystem', model: [unitSystemList: unitSystemList])
        } else {
            respond unitSystemList
        }
    }

    /*
    * 创建对象
    * */

    def createUnitSystem(UnitSystem unitSystem) {
        def newUnitSystem = new UnitSystem()
        if (request.xhr) {
            render(template: 'createUnitSystem', model: [unitSystem: newUnitSystem])
        } else {
            respond newUnitSystem
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteUnitSystem(UnitSystem unitSystem) {
        unitSystem.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateUnitSystem(UnitSystem unitSystem) {
        println("准备更新：${unitSystem}")
        unitSystem.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editUnitSystem(UnitSystem unitSystem) {
        if (request.xhr) {
            render(template: 'editUnitSystem', model: [unitSystem: unitSystem])
        } else {
            respond unitSystem
        }
    }

    /*
    * 获取当前id对应的对象
    * */

    def getUnitSystem(UnitSystem unitSystem) {
        def theModel = [UnitSystem: unitSystem]
        println("${unitSystem}")
        if (request.xhr) {
            render(template: "showUnitSystem", model: theModel)
        } else {
            theModel
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //QuantityUnit
    /*
    * 统计记录个数
    * */

    def countQuantityUnit() {
        def count = QuantityUnit.count()    //这是必须调整的
        if (session.currentPhysicalQuantity) {
            def pq = session.currentPhysicalQuantity
            count = QuantityUnit.countByDimension(pq.dimension)
        }
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

    def listQuantityUnit() {
        params.sort = "factorA"
        def quantityUnitList = QuantityUnit.list(params)
        if (session.currentPhysicalQuantity) {
            def pq = session.currentPhysicalQuantity
            quantityUnitList = QuantityUnit.findAllByDimension(pq.dimension, params)
        }
        if (request.xhr) {
            render(template: 'listQuantityUnit', model: [quantityUnitList: quantityUnitList])
        } else {
            respond quantityUnitList
        }
    }

    /*
    * 创建对象
    * */
    def createQuantityUnit(PhysicalQuantity physicalQuantity) {
        def newQuantityUnit = new QuantityUnit()
        /*
        if (session.currentPhysicalQuantity) {
            def cpq = session.currentPhysicalQuantity
            newQuantityUnit.dimension = cpq.dimension
        }
        */
        newQuantityUnit.dimension = physicalQuantity.dimension
        //如果没有标准单位，缺省地建立标准单位
        if (QuantityUnit.countByDimension(physicalQuantity.dimension)<1) {
            newQuantityUnit.symbol = physicalQuantity.unitSymbol
            newQuantityUnit.unitName = physicalQuantity.unitName
        }

        if (request.xhr) {
            render(template: 'createQuantityUnit', model: [quantityUnit: newQuantityUnit])
        } else {
            respond newQuantityUnit
        }
    }

    /*
    * 保存对象
    * */
    @Transactional
    def deleteQuantityUnit(QuantityUnit quantityUnit) {
        quantityUnit.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateQuantityUnit(QuantityUnit quantityUnit) {
        println("准备更新：${quantityUnit}")
        quantityUnit.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editQuantityUnit(QuantityUnit quantityUnit) {
        if (request.xhr) {
            render(template: 'editQuantityUnit', model: [quantityUnit: quantityUnit])
        } else {
            respond quantityUnit
        }
    }

    /*
    * 获取当前id对应的对象
    * */

    def getQuantityUnit(QuantityUnit quantityUnit) {
        def theModel = [QuantityUnit: quantityUnit]
        println("${quantityUnit}")
        if (request.xhr) {
            render(template: "showQuantityUnit", model: theModel)
        } else {
            theModel
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //PhysicalQuantity
    /*
    * 统计记录个数
    * */

    def countPhysicalQuantity() {
        def count = PhysicalQuantity.count()    //这是必须调整的
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

    def listPhysicalQuantity() {
        def physicalQuantityList = PhysicalQuantity.list(params)
        if (request.xhr) {
            render(template: 'listPhysicalQuantity', model: [physicalQuantityList: physicalQuantityList])
        } else {
            respond physicalQuantityList
        }
    }

    /*
    * 创建对象
    * */

    def createPhysicalQuantity(PhysicalQuantity physicalQuantity) {
        def newPhysicalQuantity = new PhysicalQuantity()
        newPhysicalQuantity.initDimension()
        if (request.xhr) {
            render(template: 'createPhysicalQuantity', model: [physicalQuantity: newPhysicalQuantity])
        } else {
            respond newPhysicalQuantity
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deletePhysicalQuantity(PhysicalQuantity physicalQuantity) {
        physicalQuantity.delete()
        redirect(action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updatePhysicalQuantity(PhysicalQuantity physicalQuantity) {
        println("准备更新：${physicalQuantity}")
        physicalQuantity.save flush: true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editPhysicalQuantity(PhysicalQuantity physicalQuantity) {
        if (request.xhr) {
            render(template: 'editPhysicalQuantity', model: [physicalQuantity: physicalQuantity])
        } else {
            respond physicalQuantity
        }
    }

    /*
    * 获取当前id对应的对象
    * */

    def getPhysicalQuantity(PhysicalQuantity physicalQuantity) {
        def theModel = [PhysicalQuantity: physicalQuantity]
        println("${physicalQuantity}")
        if (request.xhr) {
            render(template: "showPhysicalQuantity", model: theModel)
        } else {
            theModel
        }
    }

    def index() {}
}

