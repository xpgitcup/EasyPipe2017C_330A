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
    <g:set var="entityName" value="Data"/>
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
                当前项目：${session.currentProject}
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
                <a href="javascript: createDataKey_DataModel(${session.currentProject.id})">
                    创建模型--（${session.currentProject}）
                </a>
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

<g:if test="${flash.message}">
    <div class="message" role="status">${flash.message}</div>
</g:if>
<div class="easyui-tabs" id="operation4DataDiv">
    <div title="项目列表">
        <div id="listDataKey_ProjectDiv"></div>
        <div id="paginationListDataKey_ProjectDiv" class="easyui-pagination"></div>
    </div>
    <div title="算例列表">
        <div id="listDataKey_ProjectCaseDiv"></div>
        <div id="paginationListDataKey_ProjectCaseDiv" class="easyui-pagination"></div>
    </div>
    <div title="模型列表">
        <div id="listDataKey_DataModelDiv"></div>
        <div id="paginationListDataKey_DataModelDiv" class="easyui-pagination"></div>
    </div>
    <div title="数据列表">
        <div id="listDataItem_DataDiv"></div>
        <div id="paginationListDataItem_DataDiv" class="easyui-pagination"></div>
    </div>
    <div title="数据编辑">
        <div id="editDataKeyDiv"></div>
        <div id="inputDataDiv"></div>
    </div>
    <div title="批量数据编辑">
        <div id="importDataDiv"></div>
    </div>

</div>

</body>
</html>
