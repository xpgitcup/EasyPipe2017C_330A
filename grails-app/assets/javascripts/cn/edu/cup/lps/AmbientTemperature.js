/*
* 准备导入地温
* */
function prepareImportAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "导入管道");
    ajaxRun("operation4AmbientTemperature/prepareImportAmbientTemperature", id, "prepareImportAmbientTemperatureDiv");
}

/*
 * 统计记录总数
 * */
function countAmbientTemperature() {
    //console.info("开始统计...")
    var total = ajaxCalculate("operation4AmbientTemperature/countAmbientTemperature");
    //console.info("正在听统计结果：" + total);
    return total;
}

/*
* 列表显示当前所有对象
* */
function listAmbientTemperature(pageNumber, pageSize) {
    //console.info("列表显示对象：");
    ajaxRun("operation4AmbientTemperature/listAmbientTemperature" + getParams(pageNumber, pageSize), 0, "listAmbientTemperatureDiv");
}

/*
 * 新建
 * */
function createAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    ajaxRun("operation4AmbientTemperature/createAmbientTemperature", id, "editAmbientTemperatureDiv");
}

/*
 * 编辑
 * */
function editAmbientTemperature(id) {
    operation4PipeSimulationDiv.tabs("select", "编辑")
    //console.info("编辑AmbientTemperature." + id);
    ajaxRun("operation4AmbientTemperature/editAmbientTemperature", id, "editAmbientTemperatureDiv");
}
