<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<p class="tituloCampeoes"> 
Número de Jogos por Cidade (10 mais)
</p>
<div id="mapaNumeroJogosCidade" style="width:95%; height:250px;"></div>
	<script>
	// Create the chart
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