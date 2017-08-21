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
<!--g:form resource="${this.dataKeyA}" method="POST"-->
    <g:form controller="operation4DataKeyA" action="saveDataKeyA" method="POST">
        <div class="container">
            <div class="col-md-6">
                <fieldset class="form">
                    <!--f:all bean="dataKeyA"/-->
                    <f:with bean="dataKeyA">
                        <f:field property="dataTag" label="标签"/>
                        <f:field property="refDataModel" label="引用"/>
                        <f:field property="dataUnit" label="单位"/>
                        <f:field property="dimension" label="维度"/>
                        <f:field property="appendParameter" label="附加"/>
                        <f:field property="upDataKey" label="超类"/>
                        <f:field property="isEnumeration" label="枚举？"/>
                        <f:field property="single" label="单行？"/>
                        <f:field property="orderNumber" label="序号"/>
                        <f:field property="dictionary"/>
                    </f:with>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save"
                                    value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                </fieldset>
            </div>

            <div class="col-md-6">
                <h2>辅助信息输入</h2>
                <p>对于枚举类型，请在辅助信息中输入各个分量，然后点击输入按钮。</p>
                <p>对数组，请在辅助信息中输入各列的标题，然后点击输入按钮。</p>
                <hr>
                <g:textArea name="appendText" id="appendText"></g:textArea>
                <input type="button" value="输入" onclick="updateAppendForm4DataKeyA()">
            </div>
        </div>
    </g:form>
</div>
</body>
</html>
