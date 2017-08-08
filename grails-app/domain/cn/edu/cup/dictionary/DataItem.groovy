package cn.edu.cup.dictionary

class DataItem {

    DataKey labelKey
    String value
    //Integer dataId

    static belongsTo = [parentItem: DataItem]

    static hasMany = [subItems: DataItem]

    static mapping = {
        //sort('labelKey')
        subItems sort: 'labelKey'
    }

    static constraints = {
        //dataId(nullable: true)
        labelKey()
        value(nullable: true)
        parentItem(nullable: true)
    }

    String toString() {
        return "${labelKey}=${value}"
    }

}