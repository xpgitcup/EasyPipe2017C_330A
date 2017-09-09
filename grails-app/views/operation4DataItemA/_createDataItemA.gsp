<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'dataItemA.label', default: 'DataItemA')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<a href="#create-dataItemA" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                  default="Skip to content&hellip;"/></a>

<div class="nav" role="navigation">
    <ul>
        <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
        <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]"/></g:link></li>
    </ul>
</div>

<div id="create-dataItemA" class="content scaffold-create" role="main">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${this.dataItemA}">
        <ul class="errors" role="alert">
            <g:eachError bean="${this.dataItemA}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message
                        error="${error}"/></li>
            </g:eachError>
        </ul>
    </g:hasErrors>
<!--g:form resource="${this.dataItemA}" method="POST"-->
    <g:uploadForm controller="operation4DataItemA" action="saveDataItemA">
        <fieldset class="form">
            <!--f:all bean="dataItemA"/-->

            <table>
                <f:with bean="dataItemA">
                    <tr>
                        <td>
                            <!--f:field property="dataKeyA"/-->
                            ${dataItemA.dataKeyA}
                            <g:hiddenField name="dataKeyA.id" value="${dataItemA.dataKeyA.id}"/>
                        </td>
                        <td>
                            <!--f:field property="dataValue"/-->
                        </td>
                    </tr>
                </f:with>
            </table>
            <table>
                <g:each in="${dataItemA.subDataItems}" var="subItem" status="i">
                    <tr>
                        <td>
                            ${dataItemA.subDataItems[i].dataKeyA}
                            <g:hiddenField name="subDataItems[${i}].dataKeyA.id"
                                           value="${dataItemA.subDataItems[i].dataKeyA.id}"/>
                            <g:hiddenField name="subDataItems[${i}].upDataItem.id"
                                           value="${dataItemA.id}"></g:hiddenField>
                        </td>
                        <g:if test="${subItem.dataKeyA.isFile}">
                            <td>
                                <g:textField name="subDataItems[${i}].dataValue" id="file_${i}"/>
                            </td>
                            <td>
                                <input type="file" name="uploadFile" id="input_${i}" onchange="updateUploadFileName(${i})"/>
                            </td>
                        </g:if>
                        <g:else>
                            <g:if test="${subItem.dataKeyA.single == true}">
                                <td>
                                    <g:textField name="subDataItems[${i}].dataValue"/>
                                </td>
                            </g:if>
                            <g:else>
                                <td>
                                    <ul>
                                        <li>${subItem.dataKeyA.appendParameter}</li>
                                        <li>
                                            <g:textArea name="subDataItems[${i}].dataValue"/>
                                        </li>
                                    </ul>
                                </td>
                            </g:else>
                        </g:else>
                    </tr>
                </g:each>
            </table>
        </fieldset>
        <fieldset class="buttons">
            <g:submitButton name="create" class="save"
                            value="${message(code: 'default.button.create.label', default: 'Create')}"/>
        </fieldset>
    </g:uploadForm>
<!--/g:form-->
</div>
</body>
</html>
