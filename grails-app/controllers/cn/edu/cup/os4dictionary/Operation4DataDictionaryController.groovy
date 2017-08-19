package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.DataDictionary
import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.OK

class Operation4DataDictionaryController {

    /*
    * 统计记录个数
    * */

    def countDataDictionary() {
        def count = DataDictionary.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * DataDictionary
    * */

    def listDataDictionary() {
        def dataDictionaryList = DataDictionary.list(params)
        if (request.xhr) {
            render(template: 'listDataDictionary', model: [dataDictionaryList: dataDictionaryList])
        } else {
            respond dataDictionaryList
        }
    }

    /*
    * 创建对象
    * */

    def createDataDictionary(DataDictionary dataDictionary) {
        def newDataDictionary = new DataDictionary()
        if (request.xhr) {
            render(template: 'createDataDictionary', model: [dataDictionary: newDataDictionary])
        } else {
            respond newDataDictionary
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deleteDataDictionary(DataDictionary dataDictionary) {
        dataDictionary.delete()
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updateDataDictionary(DataDictionary dataDictionary) {
        if (dataDictionary == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataDictionary.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataDictionary.errors, view:'edit'
            return
        }

        dataDictionary.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'dataDictionary.label', default: 'DataDictionary'), dataDictionary.id])
                redirect(controller: 'operation4Dictionary', action: 'index')
            }
            '*'{ respond dataDictionary, [status: OK] }
        }
    }

    /*
    * 编辑对象
    * */

    def editDataDictionary(DataDictionary dataDictionary) {
        if (request.xhr) {
            render(template: 'editDataDictionary', model: [dataDictionary: dataDictionary])
        } else {
            respond dataDictionary
        }
    }

    def index() { }
}
