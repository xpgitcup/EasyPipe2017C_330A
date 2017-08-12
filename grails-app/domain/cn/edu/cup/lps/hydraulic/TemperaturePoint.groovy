package cn.edu.cup.lps.hydraulic

class TemperaturePoint {

    double mileage
    double temperature

    static belongsTo = [ambientTemperature: AmbientTemperature]

    static constraints = {
        mileage()
        temperature()
    }

    String toString() {
        return "[${mileage}, ${temperature}]"
    }

}
