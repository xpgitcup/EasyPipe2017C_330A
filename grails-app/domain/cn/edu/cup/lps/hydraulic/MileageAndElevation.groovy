package cn.edu.cup.lps.hydraulic

import cn.edu.cup.lps.PipeNetwork
import jxl.Cell
import jxl.Sheet
import jxl.Workbook

class MileageAndElevation {

    HydraulicVertex start
    HydraulicVertex end
    String name

    static belongsTo = [pipeNetwork: PipeNetwork]
    static hasMany = [elevationPoints: ElevationPoint]

    static constraints = {
        name(unique: true)
        start()
        end()
    }

    String toString() {
        return "${name}/${pipeNetwork}(${elevationPoints?.size()})"
    }

    //------------------------------------------------------------------------------------------------------------------
    def importFromeExcelFile(File excelFile) {
        try {
            if (excelFile) {
                Workbook book = Workbook.getWorkbook(excelFile)
                Sheet sheet = book.getSheet(0)

                importElevationPoints(sheet)

                book.close()
            }
        } catch (Exception e) {
            println "importExcelFile error: ${e}";
        }
    }

    def importElevationPoints(sheet) {
        //清理之前的顶点
        elevationPoints.each { e->
            e.delete(true)
        }

        //开始导入新的顶点
        def nRows = sheet.rows
        for (int i=1; i<nRows; i++) {
            def v = importElevationPoint(sheet, i)
            if (v) {
                v.save(true)
            }
        }
    }

    def importElevationPoint(Sheet sheet, int rowIndex) {
        def nCols = sheet.columns
        def v = null
        Cell[] cells = new Cell[nCols]
        for (int i = 0; i < nCols; i++) {
            cells[i] = sheet.getCell(i, rowIndex)
        }
        println("本行： ${nCols}")
        switch (nCols) {
            case 2:
                def x = Double.parseDouble(cells[0].getContents())
                def y = Double.parseDouble(cells[1].getContents())
                v = new ElevationPoint(mileage: x, elevation: y, mileageAndElevation: this)
                break
        }
        return v
    }
}
