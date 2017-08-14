package cn.edu.cup.os4lps

import cn.edu.cup.lps.PipeNetwork
import grails.converters.JSON
import grails.gorm.transactions.Transactional

class Operation4PipeNetworkController {

    def commonService

    /*
    * 更新逻辑坐标
    * */
    @Transactional
    def updateLogicalPosition(PipeNetwork pipeNetwork) {
        pipeNetwork.setupLogicalPosition()
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 导入管道
    * */

    def prepareImportFromExcel(PipeNetwork pipeNetwork) {
        if (request.xhr) {
            render(template: "prepareImportFromExcel", model: [pipeNetwork: pipeNetwork])
        } else {
            model:
            [pipeNetwork: pipeNetwork]
        }
    }

    @Transactional
    def importFromExcel(PipeNetwork pipeNetwork) {
        def destDir = servletContext.getRealPath("/") + "uploads"
        params.destDir = destDir
        def sf = commonService.upload(params)
        println("上传${sf}成功...")
        pipeNetwork.importFromExcel(sf)
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 更新边的情况
    * */
    @Transactional
    def updateHydraulicEdges(PipeNetwork pipeNetwork) {
        pipeNetwork.updateEdges4OneBranch()
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 导出管道
    * */

    def exportToExcel(PipeNetwork pipeNetwork) {
        def rootPath = servletContext.getRealPath("/")
        def excelName = pipeNetwork.exportPipeNetworkToExcel(rootPath)
        params.downLoadFileName = excelName
        commonService.downLoadFile(params)
    }

    /*
    * 显示拓扑图
    * */
    def showPipeNetworkTopo(PipeNetwork pipeNetwork) {
        def topo = [:]
        topo.name = "${pipeNetwork.name}--拓扑图"

        def data = []
        pipeNetwork.hydraulicVertexes.each { ms ->
            data.add([name: "${ms.id}-${ms.name}", x: ms.mileage, value: ms.elevation])
        }
        topo.data = data

        def links = []
        pipeNetwork.edges().each { e->
            links.add([source: "${e.start.id}-${e.start.name}", target: "${e.end.id}-${e.end.name}"])
        }
        topo.links = links

        println("${topo}")

        if (request.xhr) {
            render topo as JSON
        } else {
            model:
            [topo: topo]
        }
    }

    /*
    * 显示管道的纵断面图
    * */

    def showPipeNetworkProfile(PipeNetwork pipeNetwork) {
        def profile = [:]
        profile.name = "${pipeNetwork.name}--纵断面图"

        def data = []
        pipeNetwork.mileageAndElevations.each { ms ->
            ms.elevationPoints.each { e ->
                data.add([e.mileage, e.elevation])
            }
        }
        profile.data = data

        def stations = []
        pipeNetwork.hydraulicVertexes.each { ms ->
            //stations.add([name: "${ms.id}-${ms.name}", x: ms.mileage, y: ms.heatTransferCoefficient])
            stations.add([ms.mileage, ms.elevation])
        }
        profile.stations = stations
        //println("${data}")

        if (request.xhr) {
            render profile as JSON
        } else {
            model:
            [data: data]
        }
    }

    /*
    * 将管道显示为Json格式
    * */

    def showPipeNetworkAsJson(PipeNetwork pipeNetwork) {
        def p = [:]
        p.put("nanme", pipeNetwork.name)
        //p.put("nodes", nodes)
        p.put("nodes", pipeNetwork.hydraulicVertexes)
        p.put("links", pipeNetwork.edges());
        if (request.xhr) {
            render p as JSON
        } else {
            model:
            [pipeNetwork: p]
        }
    }

    /*
    * 统计记录个数
    * */

    def countPipeNetwork() {
        def count = PipeNetwork.count()    //这是必须调整的
        println("统计结果：${count}")
        def result = [count: count]
        if (request.xhr) {
            render result as JSON
        } else {
            result
        }
    }

    /*
    * 列出对象
    * */

    def listPipeNetwork() {
        def pipeNetworkList = PipeNetwork.list(params)
        if (request.xhr) {
            render(template: 'listPipeNetwork', model: [pipeNetworkList: pipeNetworkList])
        } else {
            respond pipeNetworkList
        }
    }

    /*
    * 创建对象
    * */

    def createPipeNetwork(PipeNetwork pipeNetwork) {
        def newPipeNetwork = new PipeNetwork()
        if (request.xhr) {
            render(template: 'createPipeNetwork', model: [pipeNetwork: newPipeNetwork])
        } else {
            respond newPipeNetwork
        }
    }

    /*
    * 保存对象
    * */

    @Transactional
    def deletePipeNetwork(PipeNetwork pipeNetwork) {
        pipeNetwork.delete()
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 保存对象
    * */

    @Transactional
    def updatePipeNetwork(PipeNetwork pipeNetwork) {
        println("准备更新：${pipeNetwork}")
        pipeNetwork.save flush: true
        redirect(controller: 'operation4PipeSimulation', action: 'index')
    }

    /*
    * 编辑对象
    * */

    def editPipeNetwork(PipeNetwork pipeNetwork) {
        if (request.xhr) {
            render(template: 'editPipeNetwork', model: [pipeNetwork: pipeNetwork])
        } else {
            respond pipeNetwork
        }
    }

    def index() { }
}
