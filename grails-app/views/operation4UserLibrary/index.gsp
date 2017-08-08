<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 实现可定制的布局 -->
    <g:if test="${layout}">
        <meta name="layout" content="${layout}"/>
    </g:if>
    <g:else>
        <g:if test="${session.layout}">
            <meta name="layout" content="${session.layout}"/>
        </g:if>
        <g:else>
            <meta name="layout" content="main"/>
        </g:else>
    </g:else>
<!-- end 实现可定制的布局 -->
<!--针对域类的包含-->
    <g:set var="entityName" value="UserLibrary"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/base/${entityName}.js"/>
    <!--end 针对域类的包含-->
</head>

<body>
<div class="nav">
    <ul>
        <li>
            <a href="#">
                当前：${session.currentProject}
            </a>
        </li>
        <li>
            <a class="create" href="operation4Data/clearDataKey/${session.currentProject?.id}">清除当前项目</a>
        </li>
        <g:if test="${session.currentProject}">
            <li>
                <a href="#">
                    当前算例：${session.currentProjectCase}
                </a>
            </li>
            <li>
                <a class="create" href="operation4Data/clearDataKey/${session.currentProjectCase?.id}">清除当前算例</a>
            </li>
            <li>
                <a href="#">
                    当前模型：${session.currentDataModel}
                </a>
            </li>
            <li>
                <a class="create" href="operation4Data/clearDataKey/${session.currentDataModel?.id}">清除当前模型</a>
            </li>
        </g:if>
    </ul>

</div>

<div class="easyui-tabs" id="operation4UserLibraryDiv">
    <div title="用户库分类">
        <div id="listUserLibraryClassifyDiv"></div>
        <div id="paginationListUserLibraryClassifyDiv" class="easyui-pagination"></div>
    </div>
    <div title="用户库">
        <div id="listUserLibraryDiv"></div>
        <div id="paginationListUserLibraryDiv" class="easyui-pagination"></div>
    </div>
    <div title="类列表">
        <div id="listUserClassDiv"></div>
        <div id="paginationListUserClassDiv" class="easyui-pagination"></div>
    </div>
    <div title="属性列表">
        <div id="listDataKey_DataDiv"></div>
        <div id="paginationListDataKey_DataDiv" class="easyui-pagination"></div>
    </div>
    <div title="方法列表">
    </div>

</div>

</body>
</html>
