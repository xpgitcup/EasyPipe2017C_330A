package cn.edu.cup.os

import cn.edu.cup.dictionary.BaseDataType
import cn.edu.cup.dictionary.DataItem
import cn.edu.cup.dictionary.DataItemController
import cn.edu.cup.dictionary.DataKey
import grails.converters.JSON
import grails.transaction.Transactional

@Transactional(readOnly = true)
class Operation4DataController {

    def excelService
    def commonService

    /*
    * 创建对象
    * */
    def createDataKey(DataKey dataKey) {
        println("创建： ${params}")
        def dataType = BaseDataType.valueOf(params.type)
        def newDataKey = new DataKey(upKey: dataKey, dataValueType: dataType)
        if (request.xhr) {
            render(template: 'editDataKey', model: [dataKey: newDataKey])
        } else {
            respond newDataKey
        }
    }

    /*
    * 保存对象
    * */
    @Transactional
    def updateDataKey(DataKey dataKey) {
        println("准备更新：${dataKey}")
        dataKey.save flush:true
        redirect(action: 'index')
    }

    /*
    * 上传数据文件
    * */
    @Transactional
    def importDataItem() {
        //println("${params}")
        def dataKey = DataKey.get(params.id)
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        excelService.importExcel2DataItem(dataKey, sf)
        redirect(action: "index")
    }


    /*
    * downLoadTemplate
    * 下载数据模型的模板
    * */
    def downLoadTemplate(DataKey dataKey) {
        def fileName = createTemplate(dataKey)
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }

    /*
    * 下载，关键参数是：
    * downLoadFileName
    * */
    def getTemplate(params) {
        commonService.downLoadFile(params)
    }

    /*
    * 批量数据导入
    * */
    def prepareImportDataItem4Key(DataKey dataKey) {

        String fileName = createTemplate(dataKey)

        //println("----文件名：${fileName}")
        //fileName.replace('\\',"/")
        //println("----新文件名：${fileName}")

        def sf = new File(fileName)

        def fn = sf.toURI().toString().substring(6)

        def theModel = [dataKey: dataKey, fileName: fn] //sf.toPath()]
        if (request.xhr) {
            render(template: "prepareImportDataItem", model: theModel)
        } else {
            theModel
        }
    }

    private String createTemplate(DataKey dataKey) {
        def fileName = servletContext.getRealPath("/") + "templates/${dataKey.keyContext}.xls"
        def rf = new File(fileName)
        if (!rf.exists()) {
            excelService.exportDataKey2Excel(dataKey, fileName)
        }
        fileName
    }

    /*
    * 统计数据
    * */
    def countDataItem() {
        def value = DataItem.count()

        if (session.currentDataModel) {
            def dataModel = session.currentDataModel
            value = DataItem.countByLabelKey(dataModel)
        }

        def result = [count: value]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }


    /*
    * 显示数据
    * */
    def listDataItem() {
        def dataItemList = DataItem.list(params)
        if (session.currentDataModel) {
            def dataModel = session.currentDataModel
            dataItemList = DataItem.findAllByLabelKey(dataModel, params)
        }
        dataItemList.subItems.sort{
            it.labelKey
        }
        def theModel = [dataItemList: dataItemList]
        if (request.xhr) {
            render(template: "listDataItem", model: theModel)
        } else {
            theModel
        }
    }

    /*
    * 保存
    * */
    @Transactional
    def updateDataItem(DataItem dataItem) {
        println("${params}")
        if (dataItem == null) {
            notFound()
            return
        }
        if (dataItem.hasErrors()) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'dataItem.label', default: 'DataItem'), dataItem.id])
            def model = [dataItem: dataItem]
            if (request.xhr) {
                render(template: "createDataItem", model: model)
            } else {
                println("${flash.message}")
                redirect(action: "index", model: model)
            }
        } else {
            dataItem.save(flush: true)
            dataItem.subItems.each {e->
                e.save(flush: true)
                println("subItem: ${e}")
            }
            println("svae ${dataItem} ok...")
            redirect(action: "index")
        }
    }

    /*
    * 创建数据项
    * */
    def createDataItem4Key(DataKey dataKey) {
        params.labelKey = dataKey
        def dataItem = new DataItem(params)
        dataItem.subItems = []      //初始化子节点
        //增加子节点
        dataItem.labelKey.subKey.each {e->
            def subItem = new DataItem(labelKey: e, parentItem: dataItem)
            dataItem.subItems.add(subItem)
        }
        //--------------------------------------------------------------------------------------------------------------
        def theModel = [dataItem: dataItem]
        def templateName = "createDataItem"
        if (request.xhr) {
            println("创建数据项...")
            println("${dataItem}")
            render(template: templateName, model: theModel)
        } else {
            theModel
        }
    }

    /*
    * 释放
    * */
    def clearDataKey(DataKey dataKey) {
        switch (dataKey.dataValueType) {
            case BaseDataType.project:
                session.removeAttribute("currentProject")
                session.removeAttribute("currentProjectCase")
                session.removeAttribute("currentDataModel")
                break
            case BaseDataType.projectCase:
                session.removeAttribute("currentProjectCase")
                break
            case BaseDataType.dataModel:
                session.removeAttribute("currentDataModel")
                break
        }
        redirect(action: "index")
    }

    /*
    * 选择
    * */
    def selectDataKey(DataKey dataKey) {
        switch (dataKey.dataValueType) {
            case BaseDataType.project:
                session.currentProject = dataKey
                session.removeAttribute("currentProjectCase")
                session.removeAttribute("currentDataModel")
                break
            case BaseDataType.projectCase:
                if (dataKey.upKey.dataValueType == BaseDataType.project) {
                    session.currentProject = dataKey.upKey
                    session.currentProjectCase = dataKey
                }
                break
            case BaseDataType.dataModel:
                if (dataKey.upKey.dataValueType == BaseDataType.project) {
                    session.currentProject = dataKey.upKey
                    session.currentDataModel = dataKey
                }
                break
        }
        redirect(action: "index")
    }

    /*
    * 统计
    * */

    def countDataKey() {
        def value = DataKey.count()

        if (params.dataType) {
            def baseDataType = BaseDataType.valueOf(params.dataType)
            value = DataKey.countByDataValueType(baseDataType)

            switch (baseDataType) {
                case BaseDataType.project:
                    break;
                case BaseDataType.projectCase:
                    if (session.currentProject) {
                        value = DataKey.countByDataValueTypeAndUpKey(baseDataType, session.currentProject)
                    }
                    break;
                case BaseDataType.dataModel:
                    if (session.currentProject) {
                        value = DataKey.countByDataValueTypeAndUpKey(baseDataType, session.currentProject)
                    }
                    break;
            }
        }


        def result = [count: value]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 查询
    * */

    def listDataKey() {
        //println("${params}")
        def dataKeyList = DataKey.list(params)

        if (params.dataType) {
            def baseDataType = BaseDataType.valueOf(params.dataType)
            dataKeyList = DataKey.findAllByDataValueType(baseDataType, params)

            switch (baseDataType) {
                case BaseDataType.project:
                    break;
                case BaseDataType.projectCase:
                    if (session.currentProject) {
                        dataKeyList = DataKey.findAllByDataValueTypeAndUpKey(baseDataType, session.currentProject, params)
                    }
                    break;
                case BaseDataType.dataModel:
                    if (session.currentProject) {
                        dataKeyList = DataKey.findAllByDataValueTypeAndUpKey(baseDataType, session.currentProject, params)
                    }
                    break;
            }
        }

        def theModel = [dataKeyList: dataKeyList]
        if (request.xhr) {
            render(template: "listDataKey", model: theModel)
        } else {
            theModel
        }
    }

    def index() {}
}
