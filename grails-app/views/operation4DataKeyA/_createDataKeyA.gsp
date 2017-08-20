<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataKeyA.label', default: 'DataKeyA')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-dataKeyA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div id="create-dataKeyA" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.dataKeyA}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.dataKeyA}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <table>
        <tr>
            <td>
            <!--g:form resource="${this.dataKeyA}" method="POST"-->
                <g:form controller="operation4DataKeyA" action="saveDataKeyA" method="POST">
                    <fieldset class="form">
                        <!--f:all bean="dataKeyA"/-->
                        <f:with bean="dataKeyA">
                            <f:field property="basicDataType"/>
                            <f:field property="dataTag"/>
                            <f:field property="dataUnit"/>
                            <f:field property="appendParameter"/>
                            <f:field property="upDataKey"/>
                            <f:field property="dictionary"/>
                            <f:field property="inheritCount"/>
                        </f:with>
                    </fieldset>
                    <fieldset class="buttons">
                        <g:submitButton name="create" class="save"
                                        value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                    </fieldset>
                </g:form>
            </td>
            <td>
                <div id="appendForm4DataKeyA">
                    <h1>辅助信息</h1>
                    <g:if test="${session.currentDataKeyA.basicDataType==cn.edu.cup.dictionary.BasicDataType.refDataModel}">
                        <div class="easyui-panel">
                            <div id="appendTreeDataKeyADiv" class="easyui-tree"></div>
                            <div id="paginationAppendDataKeyADiv" class="easyui-pagination"></div>
                        </div>
                    </g:if>
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
