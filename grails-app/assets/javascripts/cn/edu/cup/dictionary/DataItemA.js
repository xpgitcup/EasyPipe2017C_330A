/*
* 处理显示标签页的转换，以及分页显示问题
* */
function tabAndPage4DataItemA() {
    //----------------------------------------------------------------------------------------------------------------
    //获取当前页面的div
    listDataItemADiv = $("#listDataItemADiv");
    paginationListDataItemADiv = $("#paginationListDataItemADiv");

    //获取当前页
    var currentPgaeDataItemA = readCookie("currentPgaeDataItemA", 1);
    var pageSizeDataItemA = readCookie("pageSizeDataItemA", pageSize);
    var totalDataItemA = countDataItemA();
    //console.info("记录总数：" + totalDataItemA);


    //分页
    paginationListDataItemADiv.pagination({
        pageSize: pageSizeDataItemA,
        total: totalDataItemA,
        showPageList: true,
        displayMsg: '',
        layout: ['first', 'prev', 'links', 'next', 'last'],
        //翻页函数
        onSelectPage: function (pageNumber, pageSize) {
            listDataItemA(pageNumber, pageSize);
            $.cookie("currentPgaeDataItemA", pageNumber);
        }
    });
    paginationListDataItemADiv.pagination("select", currentPgaeDataItemA);
    //------------------------------------------------------------------------------------------------------------------
}

/*
* 准备导入
* */
function prepareImportDataItemA(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4DataItemA/prepareImportDataItemA", id, "prepareImportDataItemADiv");
}

/*
 * 统计记录总数
 * */
function countDataItemA() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4DataItemA/countDataItemA");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listDataItemA(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4DataItemA/listDataItemA" + getParams(pageNumber, pageSize), 0, "listDataItemADiv");
}

/*
 * 新建
 * */
function createDataItemA(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4DataItemA/createDataItemA", id, "editDataItemADiv");
}

/*
 * 编辑
 * */
function editDataItemA(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑DataItemA." + id);
    ajaxRun("operation4DataItemA/editDataItemA", id, "editDataItemADiv");
}
