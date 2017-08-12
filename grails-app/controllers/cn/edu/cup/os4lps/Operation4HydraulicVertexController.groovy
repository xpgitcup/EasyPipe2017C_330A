package cn.edu.cup.os4lps

import cn.edu.cup.lps.hydraulic.HydraulicVertex
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4HydraulicVertexController {

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
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateHydraulicVertex(HydraulicVertex hydraulicVertex) {
        println("准备更新：${hydraulicVertex}")
        hydraulicVertex.save flush: true
        redirect(controller: 'operation4PipeSimulation', action: 'index')
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

    def index() { }
}
