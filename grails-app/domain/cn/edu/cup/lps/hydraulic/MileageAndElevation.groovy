package cn.edu.cup.lps.hydraulic

import cn.edu.cup.lps.PipeNetwork

class MileageAndElevation {

    HydraulicVertex start
    HydraulicVertex end
    String name

    static belongsTo = [pipeNetwork: PipeNetwork]
    static hasMany = [elevationPoint: ElevationPoint]

    static constraints = {
        name(unique: true)
        start()
        end()
    }

    String toString() {
        return "${name}"
    }

}
