package cn.edu.cup.common

import cn.edu.cup.dictionary.DataItemA
import cn.edu.cup.dictionary.DataKeyA
import grails.gorm.transactions.Transactional
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

@Transactional
class ExcelService {



    /*
    * 导入数据
    * */

    def importDataFromExcelFile(DataKeyA dataKeyA, File excelFile) {
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
            for (int i=2; i<rowCount; i++) {
                dataKeyA.subDataKeys.eachWithIndex { DataKeyA entry, int index ->
                    def cell = sheet.getCell(index, i)
                    def subItem = new DataItemA(dataKeyA: entry, upDataItem: dataItem, dataValue: cell.contents)
                    subItem.save(true)
                }
            }
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
