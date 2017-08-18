
var operation4DictionaryDiv;
var currentTabDictionaryDiv;

$(function () {
    console.info("数据字典A维护...");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4DictionaryDiv = $("#operation4DictionaryDiv");
    currentTabDictionaryDiv = readCookie("currentTabDictionaryDiv", "模型列表");

    //设置页面跳转函数
    operation4DictionaryDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            switch (title) {
                case "编辑":
                case "导入管道":
                case "拓扑结构":
                    break;
                default:
                    $.cookie("currentTabDictionaryDiv", title, {path: '/'});
                    break;
            }
        }
    })

    tabAndPage4DataKeyA();

    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4DictionaryDiv.tabs("select", currentTabDictionaryDiv);

});

