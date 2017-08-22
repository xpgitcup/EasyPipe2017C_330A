package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.DataItemA
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4DataItemAController {

    def commonService

    /*
    * 统计记录个数
    * */

    def countDataItemA() {
        def count = DataItemA.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * DataItemA
    * */

    def listDataItemA() {
        def dataItemAList = DataItemA.list(params)
        if (request.xhr) {
            render(template: 'listDataItemA', model: [dataItemAList: dataItemAList])
        } else {
            respond dataItemAList
        }
    }

    /*
    * 创建对象
    * */

    def createDataItemA(DataItemA dataItemA) {
        def newDataItemA = new DataItemA()
        if (request.xhr) {
            render(template: 'createDataItemA', model: [dataItemA: newDataItemA])
        } else {
            respond newDataItemA
        }
    }

    /*
    * 删除对象
    * */

    @Transactional
    def deleteDataItemA(DataItemA dataItemA) {
        dataItemA.delete()
        redirect(controller: 'operation4DataA', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateDataItemA(DataItemA dataItemA) {
        println("准备更新：${dataItemA}")
        dataItemA.save flush: true
        redirect(controller: 'operation4DataA', action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editDataItemA(DataItemA dataItemA) {
        if (request.xhr) {
            render(template: 'editDataItemA', model: [dataItemA: dataItemA])
        } else {
            respond dataItemA
        }
    }

    def index() { }
}