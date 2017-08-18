package cn.edu.cup.dictionary

import jxl.Sheet
import jxl.Workbook
import jxl.write.Label
import jxl.write.WritableSheet
import jxl.write.WritableWorkbook

class DataKeyA {

    BasicDataType basicDataType //数据类型
    String dataTag              //数据标签
    String dataUnit             //数据单位
    String appendParameter      //附加参数
    int inheritCount         //继承计数器

    DataKeyA upDataKey

    static hasMany = [subDataKeys: DataKeyA]

    static mapping = {
        subDataKeys sort: 'id'
    }

    static constraints = {
        basicDataType()
        dataTag()
        dataUnit(nullable: true)
        appendParameter(nullable: true)
        upDataKey(nullable: true)
    }

    String toString() {
        return "${dataTag}/${basicDataType}"
    }

    //------------------------------------------------------------------------------------------------------------------
    //触发器
    def beforeInsert() {
        calculateInheritCount()
    }

    private void calculateInheritCount() {
        inheritCount = 0
        //计算继承计数器
        switch (basicDataType) {
            case BasicDataType.inheritModel:
                def p = upDataKey
                while (p) {
                    inheritCount++
                    p = p.upDataKey
                }
                break;
        }
    }

    def beforeUpdate() {
        calculateInheritCount()
    }

    //------------------------------------------------------------------------------------------------------------------

    def isDataModel() {
        return this.basicDataType in [BasicDataType.dataModel, BasicDataType.inheritModel]
    }

    /**
     * 生成数据模板
     * */
    def createTemplate(String path) {

        def fileName = "${path}/${this.dataTag}_${this.id}.xls"

        try {
            //  打开文件
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${this.dataTag}", 0);

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
            if (isDataModel()) {
                //先处理继承的问题
                if (this.basicDataType == BasicDataType.inheritModel) {
                    def ms = []
                    def pe = this.upDataKey
                    while (pe) {
                        ms.add(pe)
                        if (pe.basicDataType == BasicDataType.inheritModel) {
                            pe = pe.upDataKey
                        } else {
                            pe = null
                        }
                    }
                    println("${ms}")
                    ms.reverse().each { me->
                        me.subDataKeys.each() { it->
                            colIndex = createCell4Field(it, colIndex, sheet)
                        }
                    }
                }
                //在处理本身
                this.subDataKeys.eachWithIndex() { e, i ->
                    println("${e}")
                    colIndex = createCell4Field(e, colIndex, sheet)
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
        switch (e.basicDataType) {
            case BasicDataType.dataModel:
                break;
            case BasicDataType.inheritModel:
                break;
            case BasicDataType.normalData:
                label = new Label(colIndex, 0, e.dataTag)
                labelUnit = new Label(colIndex, 1, e.dataUnit)
                sheet.addCell(label)
                sheet.addCell(labelUnit)
                colIndex++
                break
            case BasicDataType.vector1D:
                break
            case BasicDataType.vector2D:
                label = new Label(colIndex, 0, e.dataTag)
                sheet.addCell(label)

                def heads = e.appendParameter.split(',')
                if (heads.length != 2) {
                    labelUnit = new Label(colIndex, 1, "${heads} -- 数组的标题有问题，请检查。注意：分隔符为逗号。")
                    sheet.addCell(labelUnit)
                } else {
                    labelUnit = new Label(colIndex, 1, heads[0])
                    sheet.addCell(labelUnit)
                    labelUnit = new Label(colIndex + 1, 1, heads[1])
                    sheet.addCell(labelUnit)
                }
                colIndex++
                colIndex++
                break
            case BasicDataType.vector3D:
                break
            case BasicDataType.refDataModel:
                break
        }
        return colIndex
    }

    /*
    * 导入数据
    * */

    def importFromExcelFile(File sf) {

    }

    /*
    * 导出数据
    * */

    def exportToExcelFile(File rf) {

    }

    /*
    * 生成视图--屏幕显示的视图
    * */

    def createGspTemplate(String path) {

    }

}
