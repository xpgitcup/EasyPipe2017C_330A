package cn.edu.cup.lps.hydraulic

import cn.edu.cup.lps.PipeNetwork
import jxl.Cell
import jxl.Sheet
import jxl.Workbook

class AmbientTemperature {

    HydraulicVertex start
    HydraulicVertex end
    String name

    static belongsTo = [pipeNetwork: PipeNetwork]
    static hasMany = [temperaturePoints: TemperaturePoint]

    static mapping = {
        temperaturePoints sort: 'id'
    }

    static constraints = {
        name(unique: true)
        start()
        end()
    }

    String toString() {
        return "${name}/(${pipeNetwork}:${temperaturePoints?.size()})"
    }

    //------------------------------------------------------------------------------------------------------------------
    def importFromeExcelFile(File excelFile) {
        try {
            if (excelFile) {
                Workbook book = Workbook.getWorkbook(excelFile)
                Sheet sheet = book.getSheet(0)

                importTemperaturePoints(sheet)

                book.close()
            }
        } catch (Exception e) {
            println "importExcelFile error: ${e}";
        }
    }

    def importTemperaturePoints(sheet) {
        //清理之前的顶点
        temperaturePoints.each { e->
            e.delete(true)
        }

        //开始导入新的顶点
        def nRows = sheet.rows
        for (int i=1; i<nRows; i++) {
            def v = importTemperaturePoint(sheet, i)
            if (v) {
                v.save(true)
            }
        }
    }

    def importTemperaturePoint(Sheet sheet, int rowIndex) {
        def nCols = sheet.columns
        def v = null
        Cell[] cells = new Cell[nCols]
        for (int i = 0; i < nCols; i++) {
            cells[i] = sheet.getCell(i, rowIndex)
        }
        println("本行： ${nCols} ${cells}")
        switch (nCols) {
            case 2:
                def x = Double.parseDouble(cells[0].getContents())
                def y = Double.parseDouble(cells[1].getContents())
                v = new TemperaturePoint(mileage: x, temperature: y, ambientTemperature: this)
                break
        }
        return v
    }

}
