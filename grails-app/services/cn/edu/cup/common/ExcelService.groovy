package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import grails.gorm.transactions.Transactional
import groovy.json.JsonOutput
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

@Transactional
class ExcelService {


    /*
    * 生成模板
    * */
    def createTemplate(DataKeyA dataKeyA, String path) {

        def fileName = "${path}/${dataKeyA.dataTag}_${dataKeyA.id}.xls"

        try {
            //  打开文件
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${dataKeyA.dataTag}", 0);

            /*
            * 整体的策略：
            *
            * 1.数据按列分布
            * 2.如果遇到数组，数组的标题就是appendParameter,逗号分隔
            * 3.
            * */
            Label label
            Label labelUnit
            def colIndex = 0
            if (dataKeyA.isDataModel()) {
                //先处理继承的问题
                def ms = []
                def pe = dataKeyA.upDataKey
                while (pe) {
                    ms.add(pe)
                    if (pe.isDataModel()) {
                        pe = pe.upDataKey
                    } else {
                        pe = null
                    }
                }
                println("${ms}")
                ms.reverse().each { me ->
                    me.subDataKeys.each() { it ->
                        if (!it.isDataModel()) {
                            colIndex = createCell4Field(it, colIndex, sheet)
                        }
                    }
                }
                //再处理本身
                dataKeyA.subDataKeys.eachWithIndex() { e, i ->
                    println("${e}")
                    if (!e.isDataModel()) {
                        //只有具体的数据属性才出现在模板中
                        colIndex = createCell4Field(e, colIndex, sheet)
                    }
                }
            } else {
                label = new Label(0, 0, "这不是一个数据模型，无法生成模板")
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

    private int createCell4Field(DataKeyA e, int colIndex, Sheet sheet) {
        Label labelUnit
        Label label

        label = new Label(colIndex, 0, e.dataTag)
        labelUnit = new Label(colIndex, 1, e.dataUnit)
        sheet.addCell(label)
        sheet.addCell(labelUnit)

        if (e.refDataModel) {
            labelUnit = new Label(colIndex, 1, "{ref: ${e.refDataModel.id}}")
            sheet.addCell(labelUnit)
        }

        if (e.isEnumeration) {
            labelUnit = new Label(colIndex, 1, "{${e.appendParameter}}")
            sheet.addCell(labelUnit)
        }

        if (e.dimension > 1) {
            //println("处理附加信息：${e.appendParameter}")
            if (e.appendParameter) {
                def ss = e.appendParameter.split(",")
                for (int i=0; i<ss.size(); i++) {
                    labelUnit = new Label(colIndex + i, 1, "{${ss[i]}}")
                    sheet.addCell(labelUnit)
                }
            }
            else {
                labelUnit = new Label(colIndex, 1, "超过一维的数据，需要设置附加属性")
                sheet.addCell(labelUnit)
            }
        }


        colIndex += e.dimension

        return colIndex
    }

    /*
    * 导入数据
    * */

    def importDataFromExcelFile4DataKeyA(DataKeyA dataKeyA, File excelFile) {
        def message = []

        try {
            //  打开文件
            Workbook book = Workbook.getWorkbook(excelFile);
            //  首先查找对应的sheet
            Sheet sheet = book.getSheet("${dataKeyA.dataTag}")

            if (sheet) {
                importDataFromSheet(dataKeyA, sheet, message)
            } else {
                message.add("找不到对应的[${dataKeyA.dataTag}]sheet.")
            }
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return message
    }

    def importDataFromSheet(DataKeyA dataKeyA, Sheet sheet, message) {
        def errorCount = 0
        dataKeyA.subDataKeys.eachWithIndex { e, index->
            def cell = sheet.getCell(index, 0)
            if (!cell.contents.equals(e.dataTag)) {
                errorCount++
                message.add("${e.dataTag}不匹配.")
            }
        }
        if (errorCount==0) {
            def dataItem = new DataItemA(dataKeyA: dataKeyA)
            dataItem.save(true)
            def rowCount = sheet.rows
            //处理数据
            dataKeyA.subDataKeys.eachWithIndex { DataKeyA entry, int index ->
                if (entry.single) {
                    //只导入一行
                    def cell = sheet.getCell(index, 2)
                    def subItem = new DataItemA(dataKeyA: entry, upDataItem: dataItem, dataValue: cell.contents)
                    subItem.save(true)
                } else {
                    //导入所有行
                    for (int i=2; i<rowCount; i++) {
                        //导入多维数据
                        importVectorB(sheet, entry, index, i)
                    }
                }
            }
        }
    }

    private void importVectorB(Sheet sheet, DataKeyA entry, int index, int i) {
        def dataItem
        def v = []
        for (int j = 0; j < entry.dimension; j++) {
            def cell = sheet.getCell(index + j, i)
            v.add(cell.contents)
        }
        JsonOutput jsonOutput = new JsonOutput()
        def vv = jsonOutput.toJson(v)
        def subItem = new DataItemA(dataKeyA: entry, upDataItem: dataItem, dataValue: vv)
        subItem.save(true)
    }

    private void importVectorA(DataKeyA entry, int index, int i) {
        def dataItem
        for (int j = 0; j < entry.dimension; j++) {
            def cell = sheet.getCell(index + j, i)
            def subItem = new DataItemA(dataKeyA: entry, upDataItem: dataItem, dataValue: cell.contents)
            subItem.save(true)
        }
    }

    @Transactional
    def importExcel2DataItem(DataKeyA dataKey, File excelFile) {
        def message = checkExcelFile(dataKey, excelFile)
    }

    /*
    * excel文件验证
    * */

    def checkExcelFile(DataKeyA dataKey, File excelFile) {
        def message = [:]
        return message
    }

    /*
    * DataKey输出到excel
    * */

    def exportDataKey2Excel(DataKeyA dataKey, String fileName) {
        return fileName
    }

    /*
     * 输入参数是params.filename=需要导入的excel文件名
     * */

    def importExcelFile(params) {
        def filename
        def data = []
        if (params.filename) {
            filename = params.filename
            def sheetIndex
            if (params.sheetIndex) {
                sheetIndex = params.sheetIndex as int
            } else {
                sheetIndex = 0
            }
            try {
                //  打开文件
                File file = new File(filename)
                println "importExcelFile ${file}"
                Workbook book = Workbook.getWorkbook(file);
                //  生成名为“第一页”的工作表，参数0表示这是第一页
                Sheet sheet = book.getSheet(sheetIndex);

                def rowCount = sheet.getRows()
                def colCount = sheet.getColumns()

                println "importExcelFile ${rowCount}, ${colCount}"

                Cell cell;
                for (int i = 0; i < rowCount; i++) {
                    def row = []
                    for (int j = 0; j < colCount; j++) {
                        cell = sheet.getCell(j, i)
                        row.add(cell.getContents())
                    }
                    data.add(row)
                }
                //并关闭文件
                book.close();

            } catch (Exception e) {
                println "importExcelFile error: ${e}";
            }
        } else {
            println "usage: importExcelFile([filename, sheetIndex])"
        }
        return data
    }

    def exportExcelFile(String filename, List dataList) {
        try {
            //  打开文件
            File file = new File(filename)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("第一页", 0);

            Label label
            dataList.eachWithIndex() { e, i ->
                println "ExcelService ${e}"
                def tmps = e.collect()
                tmps.eachWithIndex() { f, j ->
                    label = new Label(j, i, f.toString())
                    //  将定义好的单元格添加到工作表中
                    sheet.addCell(label);
                }
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
    }

}
