package cn.edu.cup.lps.hydraulic

import cn.edu.cup.lps.PipeNetwork

class ElevationPoint {

    double mileage
    double elevation

    static belongsTo = [mileageAndElevation: MileageAndElevation]

    static constraints = {
    }

    String toString() {
        return "[${mileage}, ${elevation}]"
    }

}
