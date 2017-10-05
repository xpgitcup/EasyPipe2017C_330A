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
                    <g:render template="form4DataKeyA"/>
                </fieldset>
                <fieldset class="buttons">
                    <ul class="nav">
                        <li>
                            <g:submitButton name="create" class="save" id="createBtn"
                                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
                        </li>
                        <li>
                            <input type="button" value="检查" onclick="check4DataKeyA()">
                        </li>
                        <li>
                            <div class="save" id="messageDataKeyA"></div>
                        </li>
                    </ul>
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
