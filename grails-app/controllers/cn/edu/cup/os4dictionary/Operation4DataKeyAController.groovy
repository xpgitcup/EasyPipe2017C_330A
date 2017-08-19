package cn.edu.cup.os4dictionary

import cn.edu.cup.dictionary.BasicDataType
import cn.edu.cup.dictionary.DataDictionary
import cn.edu.cup.dictionary.DataKeyA
import cn.edu.cup.dictionary.DataKeyAController
import cn.edu.cup.dictionary.JsFrame
import grails.converters.JSON
import grails.gorm.transactions.Transactional

import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.NO_CONTENT

@Transactional(readOnly = true)
class Operation4DataKeyAController extends DataKeyAController{

    def treeViewService
    def commonService

    /*
    * 下载模板
    * */
    def downloadTemplate(DataKeyA dataKeyA) {
        def path = servletContext.getRealPath("/") + "templates"
        def fileName = dataKeyA.createTemplate(path)
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }

    /*
    * 统计数据模型的数量
    * */
    def countDataKeyA4DataModel() {
        def q = DataKeyA.createCriteria()
        def count = q.get{
            projections{
                count 'dataTag'
                'in' ('basicDataType', [BasicDataType.dataModel, BasicDataType.inheritModel])
            }
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
    * 对象列表--数据模型
    * */

    def listDataKeyA4DataModel() {
        def dataKeyAList = DataKeyA.list(params)

        def q = DataKeyA.createCriteria()
        dataKeyAList = q.list(params) {
            projections{
                'in' ('basicDataType', [BasicDataType.dataModel, BasicDataType.inheritModel])
            }
        }

        if (request.xhr) {
            render(template: 'listDataKeyA', model: [dataKeyAList: dataKeyAList])
        } else {
            respond dataKeyA
        }
    }

    /*
    * 删除对象
    * */
    @Transactional
    def deleteDataKeyA(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        dataKeyA.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect action:"index", method:"GET", controller: "operation4Dictionary"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    /*
    * 创建对象
    * */
    def createDataKeyA(DataKeyA dataKeyA) {
        def newDataKeyA = new DataKeyA(upDataKey: dataKeyA)
        //处理数据类型
        if (params.type) {
            def dataValueType = BasicDataType.valueOf(params.type)
            newDataKeyA.basicDataType = dataValueType
        }
        //处理所述字典
        def dataDictionary
        if (params.dataDictionary) {
            dataDictionary = DataDictionary.get(params.dataDictionary)
            if (dataDictionary) {
                newDataKeyA.dictionary = dataDictionary
            }
        }
        if (request.xhr) {
            render(template: 'createDataKeyA', model: [dataKeyA: newDataKeyA])
        } else {
            respond newDataKeyA
        }
    }

    /*
    * 保存对象
    * */
    @Transactional
    def saveDataKeyA(DataKeyA dataKeyA) {
        if (dataKeyA == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (dataKeyA.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond dataKeyA.errors, view:'create'
            return
        }

        dataKeyA.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'dataKeyA.label', default: 'DataKeyA'), dataKeyA.id])
                redirect(controller: 'operation4Dictionary', action: 'index', model: [dataKeyA: dataKeyA])
            }
            '*' { respond dataKeyA, [status: CREATED] }
        }
    }


    @Transactional
    def updateDataKeyA(DataKeyA dataKeyA) {
        println("准备更新：${dataKeyA}")
        dataKeyA.save flush:true
        redirect(action: 'index')
    }

    /*
    * 编辑对象
    * */
    def editDataKeyA(DataKeyA dataKeyA) {
        if (request.xhr) {
            render(template: 'editDataKeyA', model: [dataKeyA: dataKeyA])
        } else {
            respond dataKeyA
        }
    }

    /*
    * 统计根属性
    * */
    def countDataKeyA() {
        def count = DataKeyA.countByUpDataKeyIsNull()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
        //return count //就是不行
    }

    /*
    * 获取当前id对应的对象
    * */
    def getDataKeyA(DataKeyA dataKeyA) {
        def theModel = [dataKeyA: dataKeyA]
        if (request.xhr) {
            render(template: "showDataKeyA", model:theModel)
        } else {
            theModel
        }
    }

    /*
    * 获取json格式的树形结构数据
    * 获取某个节点下的所有子节点
    * */
    def getDataKeyATree(DataKeyA dataKeyA) {
        def data = dataKeyA.subDataKeys
        params.context = "dataTag"
        params.subItems = "subDataKeys"
        params.attributes = "id"    //
        params.useMethod = true
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 获取json格式的树形结构数据
    * 获取根节点
    * */
    def getTreeDataKeyA() {
        def data
        def dataDictionary
        def did = request.getCookie("currentDataDictionary")
        if (did > 0) {
            dataDictionary = DataDictionary.get(did)
        }
        if (dataDictionary) {
            data = DataKeyA.findAllByDictionaryAndUpDataKeyIsNull(dataDictionary, params)
        } else {
            data = DataKeyA.findAllByUpDataKeyIsNull(params)     //这是必须调整的
        }
        println("${data} ${dataDictionary}")
        params.context = "dataTag"
        params.subItems = "subDataKeys"
        params.attributes = "id"    //
        def result = treeViewService.generateNodesString(data, params, JsFrame.EasyUI)
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    def index() { }
}
