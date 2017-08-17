<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- 实现可定制的布局 -->
        <g:if test="${layout}">
            <meta name="layout" content="${layout}"/>
        </g:if>
        <g:else>
            <g:if test="${session.layout}">
                <meta name="layout" content="${session.layout}"/>
            </g:if>
            <g:else>
                <meta name="layout" content="main"/>
            </g:else>
        </g:else>
    <!-- end 实现可定制的布局 -->
        <g:set var="entityName" value="Dictionary"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>${entityName}维护</title>
        <asset:javascript src="cn/edu/cup/base/${entityName}.js"/>
    </head>
    <body>
    <div id="operation4DictionaryDiv" class="easyui-tabs">
        <div title="数据字典维护"></div>
    </div>
    </body>
</html>
