<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
<!--meta name="layout" content="main"/-->
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
    <g:set var="entityName" value="DataKeyA"/>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>${entityName}维护</title>
    <asset:javascript src="cn/edu/cup/dictionary/${entityName}.js"/>
</head>

<body>
<div class="nav" role="navigation">
    <ul>
        <li><a class="create" href="javascript: createDataKeyA(0)">新建：根/模型节点</a></li>
        <li><a id="createDataKeyA_inheritModel" class="create" href="#">派生：新模型节点</a></li>
        <li><a id="createDataKeyA_normalData" class="create" href="#">新建：普通数据节点</a></li>
        <li><a id="createDataKeyA_vector1D" class="create" href="#">新建：一维数组节点</a></li>
        <li><a id="createDataKeyA_vector2D" class="create" href="#">新建：二维数组节点</a></li>
        <li><a id="createDataKeyA_vector3D" class="create" href="#">新建：三维数组节点</a></li>
        <li><a id="createDataKeyA_refDataModel" class="create" href="#">新建：模型引用节点</a></li>
    </ul>
</div>

<div class="container">
    <div class="row-fluid">
        <div class="col-md-4 column">
            <div>
                <g:if test="${flash.message}">
                    <div class="message" role="status">${flash.message}</div>
                </g:if>
            </div>
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
</body>
</html>
