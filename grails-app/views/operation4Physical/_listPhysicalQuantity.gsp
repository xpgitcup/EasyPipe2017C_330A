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
    <th>单位名称</th>
    <th>单位符号</th>
    <th>量纲</th>
    <th>基本物理量</th>
    <th>单位数</th>
    <th colspan="3">操作</th>
    </thead>
    <tbody>
    <g:each in="${physicalQuantityList}" var="item" status="i">
        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
            <td>
                <a href="operation4Physical/selectCurrentPhysicalQuantity/${item.id}">
                    ${item.quantityName}
                </a>
            </td>
            <td>${item.englishName}</td>
            <td>${item.symbol}</td>
            <td>${item.unitName}</td>
            <td>${item.unitSymbol}</td>
            <td>${item.dimension}</td>
            <td>${item.basic}</td>
            <td>${item?.countSubUnits()}</td>
            <td>
                <a href="javascript: editPhysicalQuantity(${item.id})">编辑</a>
            </td>
            <td>
                <a href="javascript: createQuantityUnit(${item.id})">新单位</a>
            </td>
            <td>
                <g:if test="${!item.basic}">
                    <a href="operation4Physical/deletePhysicalQuantity/${item.id}">删除</a>
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
