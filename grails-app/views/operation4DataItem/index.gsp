<%--
  Created by IntelliJ IDEA.
  User: Win10Lxp
  Date: 2016/8/25
  Time: 16:49
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <!--meta name="layout" content="main"-->
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
	<asset:javascript src="cup/data.js"/>
    <title>数据输入</title>
</head>

<body>
<div id="dataMaintainDiv" class="easyui-tabs">
    <div title="可输入数据项">
		<div id="list-dataKey" class="content scaffold-list" role="main">
			<h1>可输入数据项：</h1>
			<table>
			<thead>
					<tr>
						<g:sortableColumn property="keyContext" title="${message(code: 'dataKey.keyContext.label', default: 'Key Context')}" />
					  <th>数据</th>
					  <th>操作</th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${dataDetail}" status="i" var="dataItem">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td>${dataItem.key.keyContext}</td>
						<td>${dataItem.value}</td>
						<td><a href="javascript: inputData4Key(${dataItem.key.id})">录入数据</a></td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="paginationGrails">
				<g:paginate total="${dataKeyInstanceCount ?: 0}" />
			</div>
		</div>
    
    </div>
    <div title="数据"></div>
    <div title="数据编辑">
        <div id="editDataItemDiv"></div>
    </div>
</div>
</body>
</html>