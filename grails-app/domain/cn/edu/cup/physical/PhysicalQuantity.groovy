package cn.edu.cup.physical

import groovy.json.JsonOutput

/***
 * 物理量
 * */
class PhysicalQuantity {

    //字段定义
    String quantityName
    String englishName
    String symbol
    String unitName
    String unitSymbol
    String dimension
    Boolean basic = false


    //定义字段的顺序
    static constraints = {
        quantityName(unique: true)
        englishName(unique: true)
        symbol()
        unitName()
        unitSymbol()
        dimension(nullable: true)
        basic()
    }

    static mapping = {
    }

    String toString() {
        return "${quantityName}/(${unitSymbol})";
    }

    /*
    * 统计所属单位数
    * */
    def countSubUnits() {
        def value = QuantityUnit.countByDimension(this.dimension)
        return value
    }

    /*
    * 初始化量纲
    * */
    def initDimension() {
        def basicQantityList = PhysicalQuantity.findAllByBasic(true)
        def dim = [:]
        basicQantityList.each {e->
            if (quantityName.equals(e.quantityName)) {
                dim.put(e.symbol, 1)
            } else {
                dim.put(e.symbol, 0)
            }
        }
        def jsonOutput = new JsonOutput()
        def json = jsonOutput.toJson(dim)
        dimension = json
        return json
    }

    /*
    def beforeInsert() {
        dimension = initDimension()
    }
    */

}
