/**
 * Created by LiXiaoping on 2017/2/26.
 */

var displayTreeDataKeyADiv;
var paginationDataKeyADiv;

$(function(){
    console.info($("title").text() + "加载成功...");

    //获取当前页面的div
    displayTreeDataKeyADiv = $("#displayTreeDataKeyADiv");
    paginationDataKeyADiv = $("#paginationDataKeyADiv");

    //获取当前页
    var currentPgaeDataKeyA = readCookie("currentPgaeDataKeyA", 1);
    var pageSizeDataKeyA = readCookie("pageSizeDataKeyA", pageSize);
    var totalDataKeyA = countDataKeyA();
    console.info("记录总数：" + totalDataKeyA);

    //加载数据
    displayTreeDataKeyADiv.tree({
        url: "operation4DataKeyA/getTreeDataKeyA" + getParams(currentPgaeDataKeyA, pageSizeDataKeyA),
        onSelect: function (node) {
            showDataKeyA(node);
            $("#createDataKeyA").attr('href', 'javascript: createDataKeyA(' + node.attributes[0] + ')');
            $("#createDataKeyA_inheritModel").attr('href', 'javascript: createDataKeyA_inheritModel(' + node.attributes[0] + ')');
            $("#createDataKeyA_normalData").attr('href', 'javascript: createDataKeyA_normalData(' + node.attributes[0] + ')');
            $("#createDataKeyA_vector1D").attr('href', 'javascript: createDataKeyA_vector1D(' + node.attributes[0] + ')');
            $("#createDataKeyA_vector2D").attr('href', 'javascript: createDataKeyA_vector2D(' + node.attributes[0] + ')');
            $("#createDataKeyA_vector3D").attr('href', 'javascript: createDataKeyA_vector3D(' + node.attributes[0] + ')');
            $("#createDataKeyA_refDataModel").attr('href', 'javascript: createDataKeyA_refDataModel(' + node.attributes[0] + ')');
            console.info(node);
            console.info("当前节点：" + node.target.id);
            $.cookie("currentDataKeyA", node.target.id);
        },
        onLoadSuccess: function () {
            displayTreeDataKeyADiv.tree("collapseAll");
            var nodeid = $.cookie("currentDataKeyA");
            console.info("当初扩展到" + nodeid);
            if (nodeid) {
                var cNode = $("#" + nodeid);
                displayTreeDataKeyADiv.tree("expandTo", cNode);
            }
        }
    });
    //分页
    paginationDataKeyADiv.pagination({
        pageSize: pageSizeDataKeyA,
        total: totalDataKeyA,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage:function(pageNumber, pageSize){
            displayTreeDataKeyADiv.tree({
                url: "operation4DataKeyA/getTreeDataKeyA" + getParams(pageNumber, pageSize)
            });
            //记录当前页面
            $.cookie("currentPgaeDataKeyA", pageNumber);
            //应该清除掉当前扩展
            //$.cookie("currentDataKeyA", '', { expires: -1 });
        }
    });
    paginationDataKeyADiv.pagination("select", currentPgaeDataKeyA);
});

/*
 * 新建
 * */
function createDataKeyA(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA", id, "showDataKeyADiv");
}

function createDataKeyA_inheritModel(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA/?type=inheritModel", id, "showDataKeyADiv");
}

function createDataKeyA_normalData(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA/?type=normalData", id, "showDataKeyADiv");
}

function createDataKeyA_vector1D(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA/?type=vector1D", id, "showDataKeyADiv");
}

function createDataKeyA_vector2D(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA/?type=vector2D", id, "showDataKeyADiv");
}

function createDataKeyA_vector3D(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA/?type=vector3D", id, "showDataKeyADiv");
}

function createDataKeyA_refDataModel(id) {
    console.info("创建DataKeyA. 上级节点：" + id);
    ajaxRun("operation4DataKeyA/createDataKeyA/?type=refDataModel", id, "showDataKeyADiv");
}

/*
 * 编辑
 * */
function editDataKeyA(id) {
    console.info("编辑DataKeyA." + id);
    ajaxRun("operation4DataKeyA/editDataKeyA", id, "showDataKeyADiv");
}

/*
 * 统计记录总数
 * */
function countDataKeyA() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4DataKeyA/countDataKeyA");
    console.info("统计结果：" + total);
    return total;
}

/*
 * 显示当前属性
 * */
function showDataKeyA(node) {
    console.info("显示当前系统属性" + node);
    if (node !== null) {
        var id = node.attributes[0];
        console.info("id=" + id);
        ajaxRun("operation4DataKeyA/getDataKeyA", id, "showDataKeyADiv");
    }
    else {
        console.info("error 找不到id!");
        $("#showDataKeyADiv").html("");
    }
}
