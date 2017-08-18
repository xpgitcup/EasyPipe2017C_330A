package cn.edu.cup.dictionary

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

        def fileName = "${path}/${this.id}"

        try {
            //  打开文件
            File file = new File(fileName)
            WritableWorkbook book = Workbook.createWorkbook(file);
            //  生成名为“第一页”的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("${this.dataTag}", 0);
            Label label
            if (isDataModel()) {
                this.subDataKeys.eachWithIndex() { e, i ->
                    println "ExcelService ${e} ${e.quantityUnit}"

                    label = new Label(i, 0, e.keyContext)
                    sheet.addCell(label);

                    label = new Label(i, 1, e.quantityUnit)
                    sheet.addCell(label);
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
