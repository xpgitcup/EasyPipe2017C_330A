package cn.edu.cup.os4lps

import cn.edu.cup.lps.PipeNetwork
import cn.edu.cup.lps.hydraulic.OverallHeatTransferCoefficient
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4OverallHeatTransferCoefficientController {

    def commonService

    @Transactional
    def importOverallHeatTransferCoefficient() { //PipeNetwork pipeNetwork, OverallHeatTransferCoefficient overallHeatTransferCoefficients
        println("${params}")
        def overallHeatTransferCoefficients = new OverallHeatTransferCoefficient(name: params.name, start: params.start, end: params.end, pipeNetwork: params.pipeNetwork)
        overallHeatTransferCoefficients.save(true)
        println("创建高程里程：${overallHeatTransferCoefficients}")
        //--------------------------------------------------------------------------------------------------------------
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        //--------------------------------------------------------------------------------------------------------------
        //commonService.importObjectFromExcelFile(mae, sf)
        //--------------------------------------------------------------------------------------------------------------
        overallHeatTransferCoefficients.importFromeExcelFile(sf)
        //--------------------------------------------------------------------------------------------------------------
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    //OverallHeatTransferCoefficient===============================================================================================
    def prepareImportOverallHeatTransferCoefficient(PipeNetwork pipeNetwork) {
        def overallHeatTransferCoefficient = new OverallHeatTransferCoefficient(
                start: pipeNetwork.hydraulicVertexes.first(),
                end: pipeNetwork.hydraulicVertexes.last(),
                pipeNetwork: pipeNetwork
        )
        if (request.xhr) {
            render(template: "prepareImportOverallHeatTransferCoefficient", model: [pipeNetwork: pipeNetwork, overallHeatTransferCoefficient: overallHeatTransferCoefficient])
        } else {
            model:
            [pipeNetwork: pipeNetwork, overallHeatTransferCoefficient: overallHeatTransferCoefficient]
        }
    }

    /*
    * 统计记录个数
    * */

    def countOverallHeatTransferCoefficient() {
        def count = OverallHeatTransferCoefficient.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * OverallHeatTransferCoefficient
    * */

    def listOverallHeatTransferCoefficient() {
        def overallHeatTransferCoefficientList = OverallHeatTransferCoefficient.list(params)
        if (request.xhr) {
            render(template: 'listOverallHeatTransferCoefficient', model: [overallHeatTransferCoefficientList: overallHeatTransferCoefficientList])
        } else {
            respond overallHeatTransferCoefficientList
        }
    }

    /*
    * 创建对象
    * */

    def createOverallHeatTransferCoefficient(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        def newOverallHeatTransferCoefficient = new OverallHeatTransferCoefficient()
        if (request.xhr) {
            render(template: 'createOverallHeatTransferCoefficient', model: [overallHeatTransferCoefficient: newOverallHeatTransferCoefficient])
        } else {
            respond newOverallHeatTransferCoefficient
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteOverallHeatTransferCoefficient(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        overallHeatTransferCoefficient.delete()
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateOverallHeatTransferCoefficient(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        println("准备更新：${overallHeatTransferCoefficient}")
        overallHeatTransferCoefficient.save flush: true
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editOverallHeatTransferCoefficient(OverallHeatTransferCoefficient overallHeatTransferCoefficient) {
        if (request.xhr) {
            render(template: 'editOverallHeatTransferCoefficient', model: [overallHeatTransferCoefficient: overallHeatTransferCoefficient])
        } else {
            respond overallHeatTransferCoefficient
        }
    }

    def index() { }
}
