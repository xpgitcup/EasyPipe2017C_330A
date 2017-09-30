package cn.edu.cup.dictionary

class DataItemB {

    DataKeyB    dataKey
    String      dataValue

    static belongsTo = [upDataItem: DataItemB]

    static hasMany = [subDataItems: DataItemB]

    static mapping = {
        subDataItems sort: 'dataKey'
    }

    static constraints = {
        dataKey()
        dataValue(nullable: true)
        upDataItem(nullable: true)
    }

    String toString() {
        return "${dataKey}=${dataValue}"
    }
}
