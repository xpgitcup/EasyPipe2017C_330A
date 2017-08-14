package cn.edu.cup.lps

import cn.edu.cup.lps.hydraulic.AmbientTemperature
import cn.edu.cup.lps.hydraulic.HeatTransferCoefficientPoint
import cn.edu.cup.lps.hydraulic.HydraulicEdge
import cn.edu.cup.lps.hydraulic.HydraulicVertex
import cn.edu.cup.lps.hydraulic.MileageAndElevation
import cn.edu.cup.lps.hydraulic.OverallHeatTransferCoefficient
import jxl.Cell
import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook
import org.apache.commons.math3.analysis.interpolation.LinearInterpolator
import org.apache.commons.math3.analysis.polynomials.PolynomialSplineFunction

class PipeNetwork {

    String name

    static hasMany = [
            hydraulicVertexes: HydraulicVertex,
            mileageAndElevations: MileageAndElevation,
            overallHeatTransferCoefficients: OverallHeatTransferCoefficient,
            ambientTemperatures: AmbientTemperature
    ]

    static constraints = {
        name(unique: true)
        hydraulicVertexes sort: "id"
    }

    String toString() {
        return "${name}/(${hydraulicVertexes?.size()})"
    }

    //==================================================================================================================

    /*
    * 建立单支管线的边信息
    * */
    def updateEdges4OneBranch() {
        int m = hydraulicVertexes.size()
        for (int i=1; i<m; i++) {
            def e = new HydraulicEdge(start: hydraulicVertexes[i-1], end: hydraulicVertexes[i])
            e.save()
            println("${e}")
        }
    }

    /*
    * 根据里程-高程，生成逻辑坐标
    * 只是针对单支管线--
    * */
    def setupLogicalPosition() {
        //首先形成横纵坐标的两个向量
        def x = []
        def y = []
        mileageAndElevations.each { e->
            e.elevationPoints.each { ee->
                x.add(ee.mileage)
                y.add(ee.elevation)
            }
        }
        def xmin = x.min()
        def xmax = x.max()
        def ymin = y.min()
        def ymax = y.max()
        def dx = xmax - xmin
        def dy = ymax - ymin

        //声明插值对象
        double[] vx = x.toArray()
        double[] vy = y.toArray()
        println("${vx}")
        println("${vy}")
        LinearInterpolator linearInterpolator = new LinearInterpolator()
        PolynomialSplineFunction polynomialSplineFunction = linearInterpolator.interpolate(vx, vy)
        hydraulicVertexes.each { e->
            def xx = e.mileage
            def yy = polynomialSplineFunction.value(xx)
            e.elevation = yy
            //----------------------------------------------------------------------------------------------------------
            if (dx>0) {
                e.xLocation = (e.mileage - xmin) / dx
            }
            if (dy>0) {
                e.yLocation = (e.elevation - ymin) / dy
            }
            //----------------------------------------------------------------------------------------------------------
            e.save()
        }
    }

    /*
    * 获取边的数量
    * */

    def edgesCount() {
        return edges().size()
    }

    /*
    * 获取当前图的边
    * */

    def edges() {
        def es = []
        hydraulicVertexes.each { e ->
            def fes = HydraulicEdge.findAllByStart(e)
            fes.each { ee ->
                addUp(es, ee)
            }

            def fee = HydraulicEdge.findAllByEnd(e)
            fee.each { ee ->
                addUp(es, ee)
            }
        }
        println("${es}")
        return es
    }

    /*
    * 增加边，并判断是否重复
    * */

    private void addUp(List<HydraulicEdge> es, HydraulicEdge ee) {
        if (!es.contains(ee)) {
            es.add(ee)
        }
    }

    /*
    * 从Excel文件中导入
    * */

    def importFromExcel(File excelFile) {
        try {
            if (excelFile) {
                Workbook book = Workbook.getWorkbook(excelFile)
                Sheet sheet = book.getSheet(0)

                //importSingleBranchPipelineME(sheet)
                importOneBranch(sheet)

                book.close()
            }
        } catch (Exception e) {
            println "importExcelFile error: ${e}";
        }
    }

    /*
    * 导入一系列节点
    * */

    def void importOneBranch(Sheet sheet) {

        //清理之前的顶点
        hydraulicVertexes.each { e->
            e.delete(true)
        }

        //开始导入新的顶点
        def nRows = sheet.rows
        for (int i=1; i<nRows; i++) {
            def v = importHydraulicVertex(sheet, i)
            if (v) {
                v.save(true)
            }
        }
    }

    /*
    * 导入节点。。。
    * */

    def HydraulicVertex importHydraulicVertex(Sheet sheet, int rowIndex) {
        def nCols = sheet.columns
        def v = null
        Cell[] cells = new Cell[nCols]
        for (int i = 0; i < nCols; i++) {
            cells[i] = sheet.getCell(i, rowIndex)
        }
        println("本行： ${nCols}")
        switch (nCols) {
            case 2:
                def n = cells[0].getContents()
                def mi = Double.parseDouble(cells[1].getContents())
                v = new HydraulicVertex(name: n, mileage: mi, pipeNetwork: this)
                break
            case 3:
                def n = cells[0].getContents()
                def mi = Double.parseDouble(cells[1].getContents())
                def ev = Double.parseDouble(cells[2].getContents())
                v = new HydraulicVertex(name: n, mileage: mi, elevation: ev, pipeNetwork: this)
                break
        }
        return v
    }

    /*
    * 导入单支管线的高程、里程
    * */

    private void importSingleBranchPipelineME(Sheet sheet) {
        //------------------------------------------------------------------------------------------------------
        def m = sheet.rows
        def hvs = []
        def minx = 0
        def maxx = 0
        def miny = 0
        def maxy = 0
        //------------------------------------------------------------------------------------------------------
        Cell[] cells = new Cell[3]
        for (int ii = 1; ii < m; ii++) {
            for (int j = 0; j < 3; j++) {
                cells[j] = sheet.getCell(j, ii)
            }
            def n = cells[0].getContents()
            def mi = Double.parseDouble(cells[1].getContents())
            def ev = Double.parseDouble(cells[2].getContents())
            def v = new HydraulicVertex(name: n, mileage: mi, elevation: ev, pipeNetwork: this)
            //v.save(true)
            hvs.add(v)
            //--------------------------------------------------------------------------------------------------
            if (minx > v.mileage) {
                minx = v.mileage
            }
            if (maxx < v.mileage) {
                maxx = v.mileage
            }
            if (miny > v.elevation) {
                miny = v.elevation
            }
            if (maxy < v.elevation) {
                maxy = v.elevation
            }
        }
        //------------------------------------------------------------------------------------------------------
        def xl = (maxx - minx) * 1.1
        def yl = (maxy - miny) * 1.1
        if (xl > 0) {
            hvs.each { e ->
                e.xLocation = (e.mileage - minx) / xl;
            }
        }
        if (yl > 0) {
            hvs.each { e ->
                e.yLocation = (e.heatTransferCoefficient - miny) / yl;
            }
        }
        hvs.each { e -> e.save(true) }
        //------------------------------------------------------------------------------------------------------
        for (int i = 1; i < hvs.size(); i++) {
            def e = new HydraulicEdge(start: hvs[i - 1], end: hvs[i])
            e.save(true)
        }
        //------------------------------------------------------------------------------------------------------
    }

    def importFromExcel(String fileName) {
        try {
            File excelFile = new File(fileName)
            importFromExcel(excelFile)
        } catch (Exception e) {
            println "importExcelFile error: ${e}";
        }
    }

    /*
    * Excel文件模板
    * */

    def static createTemplate(rootPath) {
        def fileName = rootPath + "pipeNetworks/pipeNetwork.xls"
        try {
            //  打开文件
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("管道名称", 0);

            sheet.addCell(new Label(0, 0, "站点/节点"))
            sheet.addCell(new Label(1, 0, "里程（km）"))
            sheet.addCell(new Label(2, 0, "高程（m）"))

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

    /*
    * 导出到Excel文件
    * */

    def exportPipeNetworkToExcel(rootPath) {

        def fileName = rootPath + "pipeNetworks/${this.name}.xls"
        try {
            //  打开文件
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${this.name}", 0);

            sheet.addCell(new Label(0, 0, "站点/节点"))
            sheet.addCell(new Label(1, 0, "里程（km）"))
            sheet.addCell(new Label(2, 0, "高程（m）"))

            Label label
            this.hydraulicVertexes.eachWithIndex() { e, i ->
                sheet.addCell(new Label(0, i + 1, e.name));
                sheet.addCell(new Label(1, i + 1, e.mileage));
                sheet.addCell(new Label(2, i + 1, e.elevation));
            }

            //  写入数据并关闭文件
            book.write();
            book.close();

        } catch (Exception e) {
            println "exportExcelFile error: ${e}";
        }
        return fileName
    }

}
