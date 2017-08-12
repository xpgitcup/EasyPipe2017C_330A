/*
 * 统计记录总数
 * */
function countHydraulicProject() {
    console.info("开始统计...")
    var total = ajaxCalculate("operation4HydraulicProject/countHydraulicProject");
    console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listHydraulicProject(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4HydraulicProject/listHydraulicProject" + getParams(pageNumber, pageSize), 0, "listHydraulicProjectDiv");
}

/*
 * 新建
 * */
function createHydraulicProject(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4HydraulicProject/createHydraulicProject", id, "editHydraulicProjectDiv");
}

/*
 * 编辑
 * */
function editHydraulicProject(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑");
    //console.info("编辑HydraulicProject." + id);
    ajaxRun("operation4HydraulicProject/editHydraulicProject", id, "editHydraulicProjectDiv");
}
