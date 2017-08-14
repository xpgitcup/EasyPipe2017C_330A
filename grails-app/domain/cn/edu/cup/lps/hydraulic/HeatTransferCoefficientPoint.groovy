package cn.edu.cup.lps.hydraulic

class HeatTransferCoefficientPoint {

    double mileage
    double heatTransferCoefficient

    static belongsTo = [overallHeatTransferCoefficient: OverallHeatTransferCoefficient]

    static constraints = {
    }

    String toString() {
        return "[${mileage}, ${heatTransferCoefficient}]"
    }

}
