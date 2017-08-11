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

    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4PipeSimulationDiv.tabs("select", currentTabPipeSimulationDiv);

});

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
function countPipeNetwork() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4PipeSimulation/countPipeNetwork");
    //console.info("正在听统计结果：" + total);
    return total;
}

function showPipeNetworkProfile(id) {
    console.info("绘制纵断面图:" + id);
    operation4PipeSimulationDiv.tabs("select", "管道");
    pipeNetworkDiv.tabs("select", "纵断面图")

    var str = ajaxCall("operation4PipeSimulation/showPipeNetworkProfile/" + id, 0);
    console.info("下面是数据：");
    console.info(str);
    var data = eval(str[0]);
    console.info(data);

    var pipeNetworkProfileDiv = document.getElementById("pipeNetworkProfileDiv");
    var pipeNetworkProfile = echarts.init(pipeNetworkProfileDiv);

    var option =
        {
            title: {
                text: 'Click to Add Points'
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                containLabel: true
            },
            xAxis: {
                min: 0,
                max: 300,
                type: 'value'
            },
            yAxis: {
                min: 0,
                max: 2000,
                type: 'value',
                axisLine: {onZero: false}
            },
            series: [
                {
                    id: 'a',
                    type: 'line',
                    smooth: true,
                    data: data
                }
            ]
        }
    pipeNetworkProfile.setOption(option);
}

function showPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "拓扑结构");
    var elements = ajaxCall("operation4PipeSimulation/showPipeNetworkAsJson/" + id, 0);

    console.info(elements);

    drawTopo(elements);
}

/**/
function prepareImportFromExcel(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4PipeSimulation/prepareImportFromExcel", id, "prepareImportFromExcelDiv");
}

/*
* 管道绘图
* */
function drawTopo(items) {
    console.info("开始绘图...");
    var canvas = document.getElementById('canvas');
    var stage = new JTopo.Stage(canvas); // 创建一个舞台对象
    var scene = new JTopo.Scene(stage); // 创建一个场景对象

    var cWidth = stage.width
    var cHeight = stage.height

    stage.frames = -24;
    scene.clear();

    // 不指定布局的时候，容器的布局为自动(容器边界随元素变化）
    var container = new JTopo.Container(items.name);
    container.textPosition = 'Middle_Center';
    container.fontColor = '100,255,0';
    container.font = '18pt 微软雅黑';
    container.borderColor = '255,0,0';
    container.borderRadius = 30; // 圆角
    scene.add(container);

    function drawNode(node) {
        var anode = new JTopo.CircleNode(node.name)
        anode.setSize(10, 10);
        var x = node.xLocation * cWidth;
        var y = node.yLocation * cHeight;
        anode.setLocation(x, y);
        console.info("(" + x + "," + y + ")");
        node.tnode = anode;
        scene.add(anode);
        container.add(anode);
        return anode;
    }

    for (var i = 0; i < items.nodes.length; i++) {
        drawNode(items.nodes[i])
    }

    console.info("开始连线....")

    for (var i = 0; i < items.links.length; i++) {
        var from = items.links[i].start.id;
        var toId = items.links[i].end.id;
        //console.info(i + ":" + from + '-' + toId);

        var fromNode = arrayFind(items.nodes, 'id', from);
        var toNode = arrayFind(items.nodes, 'id', toId);

        var alink = new JTopo.Link(fromNode.tnode, toNode.tnode);

        scene.add(alink);
    }
}

/*
* 列表显示当前所有对象
* */
function listPipeNetwork(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4PipeSimulation/listPipeNetwork" + getParams(pageNumber, pageSize), 0, "listPipeNetworkDiv");
}

/*
 * 新建
 * */
function createPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeSimulation/createPipeNetwork", id, "editPipeNetworkDiv");
}

/*
 * 编辑
 * */
function editPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑PipeNetwork." + id);
    ajaxRun("operation4PipeSimulation/editPipeNetwork", id, "editPipeNetworkDiv");
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
