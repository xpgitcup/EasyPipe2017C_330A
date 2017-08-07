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
    <asset:javascript src="d3/d3.min.js"/>
</head>

<body>
<h1>Sample line</h1>
<g:javascript>
    var width = 600;
    var height = 600;
    var svg = d3.select("body").append("svg")
            .attr("width", width)
            .attr("height", height);

    var dataset = [30, 20, 45, 12, 21];

    svg.selectAll("rect")
            .data(dataset)
            .enter()
            .append("rect")
            .attr("x", 10)
            .attr("y", function (d, i) {
        return i * 30;
    })
            .attr("width", function (d, i) {
        return d * 10;
    })
            .attr("height", 28)
            .attr("fill", "red");
</g:javascript>
</body>
</html>
