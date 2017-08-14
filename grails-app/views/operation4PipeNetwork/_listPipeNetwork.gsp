<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'pipeNetwork.label', default: 'PipeNetwork')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-pipeNetwork" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div id="list-pipeNetwork" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <th>管道名称</th>
        <th colspan="4">节点&详细信息</th>
        <th colspan="4">操作</th>
        <th>连接关系数</th>
        </thead>
        <tbody>
        <g:each in="${pipeNetworkList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>
                    <a href="javascript: showPipeNetwork(${item.id})">
                        ${item.name}
                    </a>
                    <a href="javascript: showPipeNetworkTopo(${item.id})">
                        ${item.name}拓扑
                    </a>
                </td>
                <td>节点=${item.hydraulicVertexes?.size()}</td>
                <td>高程=${item.mileageAndElevations?.size()}</td>
                <td>地温=${item.ambientTemperatures?.size()}</td>
                <td>K=${item.overallHeatTransferCoefficients?.size()}</td>
                <td>
                    <g:if test="${item.edges().size() > 0}">
                        <a href="operation4PipeSimulation/exportToExcel/${item.id}">导出</a>
                    </g:if>
                    <g:else>
                        <a href="javascript: prepareImportFromExcel(${item.id})">导入节点位置</a>
                        <a href="operation4PipeNetwork/updateHydraulicEdges/${item.id}">更新连接关系</a>
                    </g:else>
                </td>
                <td>
                    <g:if test="${item.mileageAndElevations?.size() > 0}">
                        <a href="javascript: showPipeNetworkProfile(${item.id})">纵断面图</a>
                        <a href="operation4PipeNetwork/updateLogicalPosition/${item.id}">更新逻辑坐标</a>
                    </g:if>
                    <g:else>
                        <a href="javascript: prepareImportMileageAndElevation(${item.id})">导入高程里程</a>
                    </g:else>
                </td>
                <td>
                    <g:if test="${item.ambientTemperatures?.size() > 0}">
                        <a href="javascript: showPipeNetworkProfile(${item.id})">纵断面图</a>
                    </g:if>
                    <g:else>
                        <a href="javascript: prepareImportAmbientTemperature(${item.id})">导入环境温度</a>
                    </g:else>
                </td>
                <td>
                    <g:if test="${item.overallHeatTransferCoefficients?.size() > 0}">
                        <a href="javascript: showPipeNetworkProfile(${item.id})">纵断面图</a>
                    </g:if>
                    <g:else>
                        <a href="javascript: prepareImportOverallHeatTransferCoefficient(${item.id})">导入总传热系数</a>
                    </g:else>
                </td>
                <td>${item.edgesCount()}</td>
            </tr>
            <tr>
                <td colspan="10">${item.edges()}</td>
            </tr>
        </g:each>
        </tbody>
    </table>

</div>
</body>
</html>