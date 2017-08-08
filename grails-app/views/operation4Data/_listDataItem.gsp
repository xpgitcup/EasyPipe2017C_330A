<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataItem.label', default: 'DataItem')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-dataItem" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-dataItem" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <table>
                <thead>
                <th>模型标题</th>
                <th>模型注释</th>
                <th>数据列表</th>
                </thead>
                <tbody>
                    <g:each in="${dataItemList}" var="item" status="i">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td>${item.labelKey.keyContext}</td>
                            <td colspan="${item.subItems?.size()}">${item.value}</td>
                        </tr>
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <g:each in="${item.subItems}" var="sItem" status="j">
                                <td>
                                    ${sItem.labelKey.keyContext}=${sItem.value}
                                </td>
                            </g:each>
                        </tr>
                    </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>