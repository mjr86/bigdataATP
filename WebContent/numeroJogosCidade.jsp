<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ATP - Ascenção Por Ano</title>
	<link href="<c:url value="/css/estilo.css" />" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/js/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/js/highcharts.src.js" />"></script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<div class="container">
		<h2>Número de Jogos por Cidade (10 mais)</h2>
	
		<div id="mapaNumeroJogosCidade" class="plot-area">
		</div>
	</div>

	<script>
	Highcharts.chart('mapaNumeroJogosCidade', {
	    chart: {
	        type: 'column'
	    },
	    title: {
	        text: ''
	    },
	    subtitle: {
	        text: ''
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: 'número de jogos'
	        }

	    },
	    legend: {
	        enabled: false
	    },
	    plotOptions: {
	        series: {
	            borderWidth: 0,
	            dataLabels: {
	                enabled: true,
	                format: ''
	            }
	        }
	    },
	    series: [{
	        name: 'Número de Jogos',
	        colorByPoint: true,
	        data: [
	        	<c:forEach var="mapa" items="${requestScope.mapaNumeroJogosCidade}">
		        	{
			            name: '${mapa.key}',
			            y: ${mapa.value},
			            drilldown: '${mapa.key}'
		        	},
	        	</c:forEach>
	        	]
	    }]
	    
	});
	</script>
</body>
</html>