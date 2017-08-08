<%--
  Created by IntelliJ IDEA.
  User: LiXiaoping
  Date: 2016/8/30
  Time: 22:16
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main">
    <asset:javascript src="cup/operation4Data.js"/>
    <title>用户模板</title>
</head>

<body>
<div class="content scaffold-list" role="main">
    <table>
        <tr>
            <td>关键字</td>
            <td>自定义上传(仅限-模板2)</td>
            <td>模板1</td>
            <td>日期时间</td>
            <td>模板2</td>
            <td>日期时间</td>
        </tr>
        <g:each in="${keyList}" var="item" status="i">
            <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                <td>${item.key}</td>
                <td>
                    <g:uploadForm action="uploadTemplateFile" controller="operation4Data">
                        <span>
                            <input id="${item.key}" type="file" name="uploadedFile"/>
                            <input type="hidden" name="keyId" value="${item.key}"/>
                            <input type="submit" name="上传" value="上传"/>
                        </span>
                    </g:uploadForm>
                </td>
            <!--td>${item.value}</td-->
                <g:each in="${item.value}" status="j" var="fitem">
                    <td>
                        <a href="operation4Data/downloadFile?filename=${fitem}">
                            ${fitem.name}
                        </a>
                    </td>
                    <td>
                        <g:formatDate format="yyyy-MM-dd HH:mm:ss" date="${fitem.lastModified()}"/>
                    </td>
                </g:each>
            </tr>
        </g:each>
    </table>
</div>
</body>
</html>