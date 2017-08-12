package cn.edu.cup.os4lps

import cn.edu.cup.lps.HydraulicProject
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4HydraulicProjectController {

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
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateHydraulicProject(HydraulicProject hydraulicProject) {
        println("准备更新：${hydraulicProject}")
        hydraulicProject.save flush: true
        redirect(controller: 'operation4PipeSimulation', action: 'index')
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

    def index() { }
}
