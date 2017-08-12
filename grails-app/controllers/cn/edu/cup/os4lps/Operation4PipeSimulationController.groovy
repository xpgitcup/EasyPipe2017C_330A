package cn.edu.cup.os4lps

import cn.edu.cup.lps.HydraulicProject
import cn.edu.cup.lps.PipeNetwork
import cn.edu.cup.lps.hydraulic.AmbientTemperature
import cn.edu.cup.lps.hydraulic.HydraulicVertex
import cn.edu.cup.lps.hydraulic.MileageAndElevation
import grails.converters.JSON
import grails.gorm.transactions.Transactional

@Transactional(readOnly = true)
class Operation4PipeSimulationController {

    def commonService


    //MileageAndElevation===============================================================================================


    //PipeNetwork-------------------------------------------------------------------------------------------------------

    /*
    * downLoadTemplate
    * 下载数据模型的模板
    * */

    def downLoadTemplate() {
        def rootPath = servletContext.getRealPath("/")
        def fileName = PipeNetwork.createTemplate(rootPath)
        println("${fileName}")
        params.downLoadFileName = fileName
        commonService.downLoadFile(params)
    }




    def index() {}
}
