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

    var profile = ajaxCall("operation4PipeNetwork/showPipeNetworkProfile/" + id, 0);
    var data = profile.data

    console.info("下面是数据：");
    console.info(data);

    var pipeNetworkProfileDiv = document.getElementById("pipeNetworkProfileDiv");
    var pipeNetworkProfile = echarts.init(pipeNetworkProfileDiv);

    var option =
        {
            title: {
                text: profile.name
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
    var elements = ajaxCall("operation4PipeNetwork/showPipeNetworkAsJson/" + id, 0);

    console.info(elements);

    drawTopo(elements);
}

/**/
function prepareImportFromExcel(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4PipeNetwork/prepareImportFromExcel", id, "prepareImportFromExcelDiv");
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
    ajaxRun("operation4PipeNetwork/listPipeNetwork" + getParams(pageNumber, pageSize), 0, "listPipeNetworkDiv");
}

/*
 * 新建
 * */
function createPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4PipeNetwork/createPipeNetwork", id, "editPipeNetworkDiv");
}

/*
 * 编辑
 * */
function editPipeNetwork(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑PipeNetwork." + id);
    ajaxRun("operation4PipeNetwork/editPipeNetwork", id, "editPipeNetworkDiv");
}
