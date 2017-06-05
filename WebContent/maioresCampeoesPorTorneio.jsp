<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>ATP</title>
	<link href="<c:url value="/css/estilo.css" />" rel="stylesheet" type="text/css" />
	<script src="<c:url value="/js/jquery-3.2.1.min.js" />"></script>
	<script src="<c:url value="/js/highcharts.src.js" />"></script>
	<script src="<c:url value="/js/data.js" />"></script>
	<script src="<c:url value="/js/jquery.easyPaginate.js" />"></script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<div class="container">
		<h2>Campeões por Torneio</h2>
	
		<img src="<c:url value="/css/default.svg" />" class="loader"/>
		<div class="quadras" style="display:none;">
			<c:forEach var="mapa" items="${requestScope.mapaMaioresCampeoesPorTorneio}">
				<c:set var="table">${mapa.key.replaceAll(' ','_')}</c:set>
			
		        <div class="quadra">
		            <a href="javascript://" class="${mapa.key}" onclick="abreGrafico('chart-${table}')">${mapa.key}</a>
		            <div class="chart-${table}" style="display:none;">
			            <div id="chart-${table}" class="plot-area"></div>
					</div>
		        </div>
		        
		        <script>
		        Highcharts.chart('chart-${table}', {
				    chart: {
				        type: 'pie'
				    },
				    title: {
				        text: 'Campeões ${mapa.key}'
				    },
				    subtitle: {
				        text: ''
				    },
				    plotOptions: {
				        series: {
				            dataLabels: {
				                enabled: true,
				                format: ''
				            }
				        }
				    },
				
				    tooltip: {
				        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
				        pointFormat: '<span style="color:{point.color}">{point.name}</span><br/>'
				    },
				    series: [{
				        name: 'Brands',
				        colorByPoint: true,
				        data: [
				        	<c:forEach  var="campeao" items="${mapa.value}">
						    	{
						        	name: '${campeao.vencedorTorneio} - ${campeao.qtdTitulo} título(s)',
						            y: ${campeao.qtdTitulo},
						            drilldown: '${campeao.vencedorTorneio}'
						        },
					        </c:forEach>
				        ]
				    }]
				    
				});
		        </script>
			</c:forEach>
	    </div>
	</div>
	
	<script>
	$(document).ready(function() {
		$('.quadras').show();
		$('.loader').hide();
	});
	
	function abreGrafico(ul, table){
		$('.' + ul).slideToggle('slow');
	}
	</script>
</body>
</html>
