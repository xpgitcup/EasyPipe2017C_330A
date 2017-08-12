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

