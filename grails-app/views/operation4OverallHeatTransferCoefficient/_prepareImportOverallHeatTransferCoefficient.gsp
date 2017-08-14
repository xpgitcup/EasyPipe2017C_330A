<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItem.label', default: 'PipeNetwork')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-dataItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                               default="Skip to content&hellip;"/></a>

<div id="list-dataItem" class="content scaffold-list" role="main">
    <h1>${pipeNetwork}</h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <fieldset>
        <table>
            <tr>
                <td>数据格式模板可以下载：</td>
                <td>
                    <a href="#">数据模板</a>
                </td>
            </tr>
        </table>
        <g:uploadForm controller="operation4OverallHeatTransferCoefficient" action="importOverallHeatTransferCoefficient">
            <table>
                <tr>
                    <f:with bean="overallHeatTransferCoefficient">
                        <g:hiddenField name="id" value="${mileageAndElevation?.id}"/>
                        <g:hiddenField name="version" value="${mileageAndElevation?.version}"/>
                        <g:hiddenField name="pipeNetwork.id" value="${pipeNetwork?.id}"/>
                        <td>
                            <label>请指定名称</label>
                            <!--g:textField name="name" value="${pipeNetwork.name}-高程-里程"/-->
                            <f:field property="name" value="${pipeNetwork.name}-高程-里程"/>
                        </td>
                        <td>
                            <label>请选择对应的管段起点</label>
                            <!--g:select name="start" from="${pipeNetwork.hydraulicVertexes}"/-->
                            <f:field property="start"/>
                        </td>
                        <td>
                            <label>请选择对应的管段起点</label>
                            <!--g:select name="end" from="${pipeNetwork.hydraulicVertexes}"/-->
                            <f:field property="end"/>
                        </td>
                    </f:with>
                </tr>
                <tr>
                    <td>
                        <label>请为-${pipeNetwork}-选择(总传热系数)数据文件(*.xls)</label>
                    </td>
                    <td>
                        <input type="file" name="uploadedFile">
                        <g:hiddenField name="id" value="${pipeNetwork.id}"/>
                    </td>
                </tr>
            </table>
            <fieldset class="buttons">
                <g:submitButton name="create" class="save"
                                value="${message(code: 'default.button.create.label', default: 'Create')}"/>
            </fieldset>
        </g:uploadForm>
    </fieldset>
</div>
</body>
</html>