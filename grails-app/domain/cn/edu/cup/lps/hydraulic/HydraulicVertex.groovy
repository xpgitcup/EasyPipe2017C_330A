package cn.edu.cup.lps.hydraulic

import cn.edu.cup.lps.PipeNetwork

class HydraulicVertex {

    String name
    //高程、里程
    Double mileage
    Double elevation
    //显示坐标
    Double xLocation
    Double yLocation
    //经纬度
    Double longitude    //经度
    Double latitude     //纬度
    //大地坐标
    Double xLocationGeo
    Double yLocationGeo

    static belongsTo = [pipeNetwork: PipeNetwork]

    Set<HydraulicVertex> getEnds() {
        HydraulicEdge.findAllByStart(this).collect { it.end }
    }

    static mapping = {
        sort('id')
    }

    static constraints = {
        name()
        mileage(nullable: true)
        elevation(nullable: true)
        xLocation(nullable: true)
        yLocation(nullable: true)
        longitude(nullable: true)
        latitude(nullable: true)
        xLocationGeo(nullable: true)
        yLocationGeo(nullable: true)
    }

    String toString() {
        return "${name}"
    }
}
