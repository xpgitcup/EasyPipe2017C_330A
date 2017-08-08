<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'userLibraryClassify.label', default: 'UserLibraryClassify')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<a href="#list-userLibraryClassify" class="skip" tabindex="-1"><g:message code="default.link.skip.label"
                                                                          default="Skip to content&hellip;"/></a>

<div id="list-userLibraryClassify" class="content scaffold-list" role="main">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message" role="status">${flash.message}</div>
    </g:if>
    <table>
        <thead>
        <th>名称</th>
        <th>路径</th>
        <th>类库数</th>
        <th>说明</th>
        </thead>
        <tbody>
        <g:each in="${userLibraryClassifyList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.name}</td>
                <td>${item.path}</td>
                <td>包含${item.userLibraries?.size()}个用户库</td>
                <td>${item.description}</td>
            </tr>
        </g:each>
        </tbody>
    </table>
</div>
</body>
</html>