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
    <!--asset:javascript src="d3/d3.min.js"/-->
    <script src="//d3js.org/d3.v3.min.js"></script>
    <style>

    .axis path,
    .axis line {
        fill: none;
        stroke: black;
        shape-rendering: crispEdges;
    }

    .axis text {
        font-family: sans-serif;
        font-size: 11px;
    }

    </style>

</head>

<body>
<h1>Sample line</h1>
<g:javascript>
    var width = 600;
    var height = 600;
    var dataset = [30, 20, 45, 12, 21];

    var svg = d3.select("body").append("svg")
            .attr("width", width)
            .attr("height", height);

    //var xScale = d3.scaleLinear()
    //        .domain([0, d3.max(dataset)])
    //        .range([0, 500]);
    var xScale = d3.scale.linear()
            .domain([0,d3.max(dataset)])
            .range([0,500]);

    var axis = d3.svg.axis().scale(xScale).orient("bottom");
    //var axis = d3.svg.axisBottom();
    //var axis = d3.svg.axis();


    svg.append("g")
            .attr("class", "axis")
            .attr("transform", "translate(10,160)")
            .call(axis);

    svg.selectAll("rect")
            .data(dataset)
            .enter()
            .append("rect")
            .attr("x", 10)
            .attr("y", function (d, i) {
        return i * 30;
    })
            .attr("width", xScale)           //注意这里
            .attr("height", 28)
            .attr("fill", "red");
</g:javascript>
</body>
</html>
