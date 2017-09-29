package cn.edu.cup.dictionary

class DataKeyB {

    String dataTag              //数据标签
    String dataUnit = '无量纲'  //数据单位
    String appendParameter      //附加参数
    DataKeyType dataKeyType     //数据关键字类型
    String colSeperator = ','   //列分割副
    String rowSeperator = ';'   //行分隔符
    int dimension = 1           //维度
    DataKeyA refDataModel       //引用
    int orderNumber = 0         //顺序
    boolean isEnumeration = false       //是否枚举
    boolean single = true              //单行？
    boolean isFile = false             //是文件吗？

    DataKeyA upDataKey

    static belongsTo = [dictionary: DataDictionary]

    static hasMany = [subDataKeys: DataKeyA]

    static mapping = {
        subDataKeys sort: 'orderNumber', 'id'
    }

    static constraints = {
        dataTag()
        dataUnit(nullable: true)
        appendParameter(nullable: true)
        dimension()
        refDataModel(nullable: true)
        isEnumeration()
        single()
        isFile()
        orderNumber()
        upDataKey(nullable: true)
    }

    String toString() {
        //return "${dictionary}.${dataTag}.${subDataKeys?.size()}"
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
