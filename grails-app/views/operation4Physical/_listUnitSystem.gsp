<!--
  To change this license header, choose License Headers in Project Properties.
  To change this template file, choose Tools | Templates
  and open the template in the editor.
-->

<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Sample title</title>
</head>

<body>
<table>
    <thead>
    <th>名称</th>
    <th>说明</th>
    <th>单位数</th>
    <th colspan="3">操作</th>
    </thead>
    <tbody>
    <g:each in="${unitSystemList}" var="item" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${item.systemName}</td>
            <td>${item.description}</td>
            <td>${item?.quantityUnits.size()}</td>
            <td>
                <a href="javascript: editUnitSystem(${item.id})">编辑</a>
            </td>
            <td>
                <g:if test="${item?.quantityUnits.size()<1}">
                    <a href="operation4Physical/deleteUnitSystem/${item.id}">删除</a>
                </g:if>
                <g:else>
                    不能删除
                </g:else>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>
