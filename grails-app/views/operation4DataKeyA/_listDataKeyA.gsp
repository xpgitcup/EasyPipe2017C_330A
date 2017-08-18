<%@ page import="cn.edu.cup.dictionary.DataItemA" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'dataKeyA.label', default: 'DataKeyA')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-dataKeyA" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div id="list-dataKeyA" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <!--f:table collection="${dataKeyAList}" /-->
            <table>
                <thead>
                <th>名称</th>
                <th>数据量</th>
                <th>下载模板</th>
                <th>导入数据</th>
                </thead>
                <tbody>
                <g:each in="${dataKeyAList}" var="item" status="i">
                    <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                        <td>
                            <a>${item.dataTag}</a>
                        </td>
                        <td>
                            ${cn.edu.cup.dictionary.DataItemA.countByDataKeyA(item)}
                        </td>
                        <td>
                            <a href="operation4DataKeyA/downloadTemplate/${item.id}">下载</a>
                        </td>
                    </tr>
                </g:each>
                </tbody>
            </table>
        </div>
    </body>
</html>