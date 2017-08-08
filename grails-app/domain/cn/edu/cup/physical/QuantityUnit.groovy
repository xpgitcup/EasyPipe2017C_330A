package cn.edu.cup.physical

/*
 * 物理量单位
 * */
class QuantityUnit {

    //字段定义
    String unitName
    String englishName
    String symbol
    String dimension
    Double factorA = 1
    Double factorB = 0

    static belongsTo = [unitSystem: UnitSystem]

    static constraints = {
        unitName(unique: true);
        englishName(nullable: true)
        symbol()
        dimension(nullable: true)
        factorA();
        factorB();
    }

    //定义排序
    static mapping = {
    }

    String toString() {
        return "${symbol}"
    }

    def toIsoUnit(Double value) {
        return value * factorA + factorB
    }

    def fromIsoUnit(Double value) {
        return (value - factorB) / factorA
    }

}
