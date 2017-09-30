package cn.edu.cup.dictionary

class DataDictionaryB {

    String name

    static hasMany = [datakeys: DataKeyB]

    static constraints = {
        name(unique: true)
    }

    String toString() {
        return "${name}/(${datakeys?.size()})"
    }

    def modelCount() {
        datakeys.countBy { e->
            e.subDataKeys.size() > 0
        }
    }

}
