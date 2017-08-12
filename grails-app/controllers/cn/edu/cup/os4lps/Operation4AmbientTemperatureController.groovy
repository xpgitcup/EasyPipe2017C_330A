package cn.edu.cup.os4lps

import cn.edu.cup.lps.PipeNetwork
import cn.edu.cup.lps.hydraulic.AmbientTemperature
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4AmbientTemperatureController {

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
        redirect(controller: 'operation4PipeSimulation', action: 'index')
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
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateAmbientTemperature(AmbientTemperature ambientTemperature) {
        println("准备更新：${ambientTemperature}")
        ambientTemperature.save flush: true
        redirect(controller: 'operation4PipeSimulation', action: 'index')
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

    def index() { }
}
