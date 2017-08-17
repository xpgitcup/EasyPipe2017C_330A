package cn.edu.cup.dictionary

class DataKeyA {

    BasicDataType basicDataType //数据类型
    String dataTag              //数据标签
    String dataUnit             //数据单位
    String appendParameter      //附加参数
    int    inheritCount         //继承计数器

    DataKeyA upDataKey

    static hasMany = [subDataKeys: DataKeyA]

    static mapping = {
        subDataKeys sort: 'dataTag', 'id'
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
        def p = upDataKey
        while (p) {
            inheritCount++
            p = p.upDataKey
        }
    }

    def beforeUpdate() {
        calculateInheritCount()
    }

    //------------------------------------------------------------------------------------------------------------------

    /**
     * 生成数据模板
     * */
    def createTemplate(String path) {

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
