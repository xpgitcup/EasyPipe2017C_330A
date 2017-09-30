package cn.edu.cup.dictionary

class DataKeyB {

    String dataTag                  //数据标签
    String dataUnit = '无量纲'      //数据单位
    String appendParameter = ''     //附加参数
    DataKeyType dataKeyType = DataKeyType.dataKeyNormal     //数据关键字类型
    String columnSeperator = ","   //列分割副
    String lineSeperator = ";"   //行分隔符

    int orderNumber = 0         //顺序

    DataKeyB upDataKey

    static belongsTo = [dictionary: DataDictionaryB]

    static hasMany = [subDataKeys: DataKeyB]

    static mapping = {
        subDataKeys sort: 'orderNumber', 'id'
    }

    static constraints = {
        dataTag()
        dataUnit(nullable: true)
        appendParameter(nullable: true)
        dataKeyType()
        columnSeperator()
        lineSeperator()
        orderNumber()
        upDataKey(nullable: true)
    }

    String toString() {
        if (subDataKeys) {
            return "${dictionary}.${dataTag}/(模型)"
        } else {
            return "${dictionary}.${dataTag}/(数据项)"
        }
    }

    //------------------------------------------------------------------------------------------------------------------
    //触发器
    def beforeInsert() {
        if (upDataKey) {
            dictionary = upDataKey.dictionary
            orderNumber = upDataKey.orderNumber
        }
    }

}
