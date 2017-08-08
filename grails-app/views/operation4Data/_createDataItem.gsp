<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItem.label', default: 'DataItem')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-dataItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                 default="Skip to content&hellip;"/></a>

<div id="create-dataItem" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.dataItem}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.dataItem}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
    <!--g:form resource="${this.dataItem}" method="POST"-->
        <g:form controller="operation4Data" action="updateDataItem">
        <fieldset class="form">
            <!--f:all bean="dataItem"/-->
            <f:field bean="dataItem" property="labelKey" value="${dataItem.labelKey}"/>
            <f:field bean="dataItem" property="value" label="${dataItem.labelKey.keyContext}"/>
            <!--f:field bean="dataItem" property="parentItem"/-->
            <hr>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:form>
</div>
</body>
</html>
