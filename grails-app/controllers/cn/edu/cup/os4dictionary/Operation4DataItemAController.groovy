package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED

class Operation4DataItemAController {

    def commonService

    /*
    * 统计记录个数
    * */

    def countDataItemA() {
        //def count = DataItemA.count()    //这是必须调整的
        def count = DataItemA.countByUpDataItemIsNull()
        //println("统计结果：${count}")
        if (session.currentDataKeyA) {
            count = DataItemA.countByDataKeyAAndUpDataItemIsNull(session.currentDataKey)
        }
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
        //def dataItemAList = DataItemA.list(params)
        def dataItemAList = DataItemA.findAllByUpDataItemIsNull(params)
        if (session.currentDataKeyA) {
            dataItemAList = DataItemA.findAllByDataKeyAAndUpDataItemIsNull(session.currentDataKeyA, params)
        }
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
    def saveDataItemA(DataItemA dataItemA) {
        if (dataItemA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataItemA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataItemA.errors, view:'create'
            return
        }

        dataItemA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataItemA.label', default: 'DataItemA'), dataItemA.id])
                redirect dataItemA
            }
            '*' { respond dataItemA, [status: CREATED] }
        }
    }

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
