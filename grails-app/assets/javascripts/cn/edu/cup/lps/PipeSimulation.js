var operation4PipeSimulationDiv;
var currentTabPipeSimulationDiv;

var listHydraulicProjectDiv;
var paginationListHydraulicProjectDiv;

var listPipeNetworkDiv;
var paginationListPipeNetworkDiv;

var listHydraulicVertexDiv;
var paginationListHydraulicVertexDiv;

var listMileageAndElevationDiv;
var paginationListMileageAndElevationDiv;

var pipeNetworkDiv;

var listAmbientTemperatureDiv;
var paginationListAmbientTemperatureDiv;

$(function () {
    console.info("管道模拟...");

    //------------------------------------------------------------------------------------------------------------------
    //管道标签下的子标签
    pipeNetworkDiv = $("#pipeNetworkDiv");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4PipeSimulationDiv = $("#operation4PipeSimulationDiv");
    currentTabPipeSimulationDiv = readCookie("currentTabPipeSimulationDiv", "模拟工程");

    //设置页面跳转函数
    operation4PipeSimulationDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "编辑":
                case "导入管道":
                case "拓扑结构":
                    break;
                default:
                    $.cookie("currentTabPipeSimulationDiv", title, {path: '/'});
                    break;
            }
        }
    })

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listHydraulicProjectDiv = $("#listHydraulicProjectDiv");
    paginationListHydraulicProjectDiv = $("#paginationListHydraulicProjectDiv");

    //获取当前页
    var currentPgaeHydraulicProject = readCookie("currentPgaeHydraulicProject", 1);
    var pageSizeHydraulicProject = readCookie("pageSizeHydraulicProject", pageSize);
    var totalHydraulicProject = countHydraulicProject();
    //console.info("记录总数：" + totalHydraulicProject);


    //分页
    paginationListHydraulicProjectDiv.pagination({
        pageSize: pageSizeHydraulicProject,
        total: totalHydraulicProject,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listHydraulicProject(pageNumber, pageSize);
            $.cookie("currentPgaeHydraulicProject", pageNumber);
        }
    });
    paginationListHydraulicProjectDiv.pagination("select", currentPgaeHydraulicProject);
    //------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listPipeNetworkDiv = $("#listPipeNetworkDiv");
    paginationListPipeNetworkDiv = $("#paginationListPipeNetworkDiv");

    //获取当前页
    var currentPgaePipeNetwork = readCookie("currentPgaePipeNetwork", 1);
    var pageSizePipeNetwork = readCookie("pageSizePipeNetwork", pageSize);
    var totalPipeNetwork = countPipeNetwork();
    //console.info("记录总数：" + totalPipeNetwork);


    //分页
    paginationListPipeNetworkDiv.pagination({
        pageSize: pageSizePipeNetwork,
        total: totalPipeNetwork,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listPipeNetwork(pageNumber, pageSize);
            $.cookie("currentPgaePipeNetwork", pageNumber);
        }
    });
    paginationListPipeNetworkDiv.pagination("select", currentPgaePipeNetwork);
    //------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listHydraulicVertexDiv = $("#listHydraulicVertexDiv");
    paginationListHydraulicVertexDiv = $("#paginationListHydraulicVertexDiv");

    //获取当前页
    var currentPgaeHydraulicVertex = readCookie("currentPgaeHydraulicVertex", 1);
    var pageSizeHydraulicVertex = readCookie("pageSizeHydraulicVertex", pageSize);
    var totalHydraulicVertex = countHydraulicVertex();
    //console.info("记录总数：" + totalHydraulicVertex);


    //分页
    paginationListHydraulicVertexDiv.pagination({
        pageSize: pageSizeHydraulicVertex,
        total: totalHydraulicVertex,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listHydraulicVertex(pageNumber, pageSize);
            $.cookie("currentPgaeHydraulicVertex", pageNumber);
        }
    });
    paginationListHydraulicVertexDiv.pagination("select", currentPgaeHydraulicVertex);
    //------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listMileageAndElevationDiv = $("#listMileageAndElevationDiv");
    paginationListMileageAndElevationDiv = $("#paginationListMileageAndElevationDiv");

    //获取当前页
    var currentPgaeMileageAndElevation = readCookie("currentPgaeMileageAndElevation", 1);
    var pageSizeMileageAndElevation = readCookie("pageSizeMileageAndElevation", pageSize);
    var totalMileageAndElevation = countMileageAndElevation();
    //console.info("记录总数：" + totalMileageAndElevation);


    //分页
    paginationListMileageAndElevationDiv.pagination({
        pageSize: pageSizeMileageAndElevation,
        total: totalMileageAndElevation,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listMileageAndElevation(pageNumber, pageSize);
            $.cookie("currentPgaeMileageAndElevation", pageNumber);
        }
    });
    paginationListMileageAndElevationDiv.pagination("select", currentPgaeMileageAndElevation);
    //------------------------------------------------------------------------------------------------------------------

    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listAmbientTemperatureDiv = $("#listAmbientTemperatureDiv");
    paginationListAmbientTemperatureDiv = $("#paginationListAmbientTemperatureDiv");

    //获取当前页
    var currentPgaeAmbientTemperature = readCookie("currentPgaeAmbientTemperature", 1);
    var pageSizeAmbientTemperature = readCookie("pageSizeAmbientTemperature", pageSize);
    var totalAmbientTemperature = countAmbientTemperature();
    //console.info("记录总数：" + totalAmbientTemperature);


    //分页
    paginationListAmbientTemperatureDiv.pagination({
        pageSize: pageSizeAmbientTemperature,
        total: totalAmbientTemperature,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listAmbientTemperature(pageNumber, pageSize);
            $.cookie("currentPgaeAmbientTemperature", pageNumber);
        }
    });
    paginationListAmbientTemperatureDiv.pagination("select", currentPgaeAmbientTemperature);
    //------------------------------------------------------------------------------------------------------------------

    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4PipeSimulationDiv.tabs("select", currentTabPipeSimulationDiv);

});

/*
* 准备导入地温
* */
function prepareImportAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4PipeSimulation/prepareImportAmbientTemperature", id, "prepareImportAmbientTemperatureDiv");
}

/*
 * 统计记录总数
 * */
function countAmbientTemperature() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4PipeSimulation/countAmbientTemperature");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listAmbientTemperature(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4PipeSimulation/listAmbientTemperature" + getParams(pageNumber, pageSize), 0, "listAmbientTemperatureDiv");
}

/*
 * 新建
 * */
function createAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeSimulation/createAmbientTemperature", id, "editAmbientTemperatureDiv");
}

/*
 * 编辑
 * */
function editAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑AmbientTemperature." + id);
    ajaxRun("operation4PipeSimulation/editAmbientTemperature", id, "editAmbientTemperatureDiv");
}

//----------------------------------------------------------------------------------------------------------------------

/*
 * 统计记录总数
 * */
function countMileageAndElevation() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4PipeSimulation/countMileageAndElevation");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listMileageAndElevation(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4PipeSimulation/listMileageAndElevation" + getParams(pageNumber, pageSize), 0, "listMileageAndElevationDiv");
}

/*
 * 新建
 * */
function createMileageAndElevation(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeSimulation/createMileageAndElevation", id, "editMileageAndElevationDiv");
}

/*
 * 编辑
 * */
function editMileageAndElevation(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑MileageAndElevation." + id);
    ajaxRun("operation4PipeSimulation/editMileageAndElevation", id, "editMileageAndElevationDiv");
}

//----------------------------------------------------------------------------------------------------------------------
/*
 * 统计记录总数
 * */
function countHydraulicProject() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4PipeSimulation/countHydraulicProject");
    console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listHydraulicProject(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4PipeSimulation/listHydraulicProject" + getParams(pageNumber, pageSize), 0, "listHydraulicProjectDiv");
}

/*
 * 新建
 * */
function createHydraulicProject(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeSimulation/createHydraulicProject", id, "editHydraulicProjectDiv");
}

/*
 * 编辑
 * */
function editHydraulicProject(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑");
    //console.info("编辑HydraulicProject." + id);
    ajaxRun("operation4PipeSimulation/editHydraulicProject", id, "editHydraulicProjectDiv");
}

//----------------------------------------------------------------------------------------------------------------------

/*
* 准备导入高程里程
* */
function prepareImportMileageAndElevation(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4PipeSimulation/prepareImportMileageAndElevation", id, "prepareImportMileageAndElevationDiv");
}


/*
 * 统计记录总数
 * */
function countHydraulicVertex() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4PipeSimulation/countHydraulicVertex");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listHydraulicVertex(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4PipeSimulation/listHydraulicVertex" + getParams(pageNumber, pageSize), 0, "listHydraulicVertexDiv");
}

/*
 * 新建
 * */
function createHydraulicVertex(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeSimulation/createHydraulicVertex", id, "editHydraulicVertexDiv");
}

/*
 * 编辑
 * */
function editHydraulicVertex(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑HydraulicVertex." + id);
    ajaxRun("operation4PipeSimulation/editHydraulicVertex", id, "editHydraulicVertexDiv");
}
