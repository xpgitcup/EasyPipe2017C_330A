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
    <th>英文名称</th>
    <th>符号</th>
    <th>量纲</th>
    <th>参数A</th>
    <th>参数B</th>
    <th colspan="3">操作</th>
    </thead>
    <tbody>
    <g:each in="${quantityUnitList}" var="item" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>${item.unitName}</td>
            <td>${item.englishName}</td>
            <td>${item.symbol}</td>
            <td>${item.dimension}</td>
            <td>${item.factorA}</td>
            <td>${item.factorB}</td>
            <td>
                <a href="javascript: editQuantityUnit(${item.id})">编辑</a>
            </td>
            <td>
                <a href="operation4Physical/deleteQuantityUnit/${item.id}">删除</a>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>
</body>
</html>
