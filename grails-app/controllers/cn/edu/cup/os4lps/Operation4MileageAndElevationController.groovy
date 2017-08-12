package cn.edu.cup.os4lps

import cn.edu.cup.lps.PipeNetwork
import cn.edu.cup.lps.hydraulic.MileageAndElevation
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4MileageAndElevationController {

    def commonService

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
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

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
    * 删除对象
    * */

    @Transactional
    def deleteMileageAndElevation(MileageAndElevation mileageAndElevation) {
        mileageAndElevation.delete()
        redirect(controller: 'operation4MileageAndElevation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateMileageAndElevation(MileageAndElevation mileageAndElevation) {
        println("准备更新：${mileageAndElevation}")
        mileageAndElevation.save flush: true
        redirect(controller: 'operation4MileageAndElevation', action: 'index')
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

    def index() { }
}
