var operation4DictionaryDiv;
var currentTabDictionaryDiv;

var listDataDictionaryDiv;
var paginationListDataDictionaryDiv;

var displayTreeDataKeyADiv;
var paginationDataKeyADiv;

$(function () {

    console.info("字典维护...");

    //------------------------------------------------------------------------------------------------------------------
    //总体设置
    operation4DictionaryDiv = $("#operation4DictionaryDiv");
    currentTabDictionaryDiv = readCookie("currentTabDictionaryDiv", "数据字典");

    //设置页面跳转函数
    operation4DictionaryDiv.tabs({
        onSelect: function (title, index) {
            console.info("选择标签：" + title + "---" + index);
            $.cookie("currentTabDictionaryDiv", title, {path: '/'});
        }
    })

    tabAndPage4DataDictionary();
    tabAndPage4DataKeyA();

    //------------------------------------------------------------------------------------------------------------------
    //页面跳转--放到最后，试试看
    operation4DictionaryDiv.tabs("select", currentTabDictionaryDiv);

});

