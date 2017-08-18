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
    <g:set var="entityName" value="Dictionary"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>
</head>

<body>

<div class="nav" role="navigation">
    <ul>
        <li><a class="create" href="javascript: createDataKeyA(0)">新建根节点</a></li>
        <li><a id="createDataKeyA" class="create" href="#">新建子节点</a></li>
        <li><a href="#">----</a></li>
        <li><a id="createDataKeyAProjectCase" class="create" href="#">projectCase子节点</a></li>
        <li><a id="createDataKeyADataModel" class="create" href="#">dataModel子节点</a></li>
    </ul>
</div>

<div class="container">
    <div class="row-fluid">
        <div class="col-md-4 column">
            <div class="panel panel-default">
                <div class="easyui-panel">
                    <div id="displayTreeDataKeyADiv" class="easyui-tree"></div>

                    <div id="paginationDataKeyADiv" class="easyui-pagination"></div>
                </div>
            </div>
        </div>

        <div class="col-md-8 column">
            <div class="panel panel-default">
                <div class="easyui-panel">
                    <div id="showDataKeyADiv"></div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>

</body>
</html>