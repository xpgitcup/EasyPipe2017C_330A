/*
 * 统计记录总数
 * */
function countHydraulicVertex() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4HydraulicVertex/countHydraulicVertex");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listHydraulicVertex(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4HydraulicVertex/listHydraulicVertex" + getParams(pageNumber, pageSize), 0, "listHydraulicVertexDiv");
}

/*
 * 新建
 * */
function createHydraulicVertex(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4HydraulicVertex/createHydraulicVertex", id, "editHydraulicVertexDiv");
}

/*
 * 编辑
 * */
function editHydraulicVertex(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑HydraulicVertex." + id);
    ajaxRun("operation4HydraulicVertex/editHydraulicVertex", id, "editHydraulicVertexDiv");
}
