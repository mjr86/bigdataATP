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
	<script src="<c:url value="/js/data.js" />"></script>
</head>
<body>
	<jsp:include page="menu.jsp" />
	
	<div class="container">
		<h2>Desempenho Por Superfície</h2>
	
		<div class="quadras">
			<c:forEach var="mapa" items="${requestScope.mapaDesempenhoPorSuperficie}">
				<c:set var="table">${mapa.key.replaceAll(' ','_')}</c:set>
			
		        <div class="quadra">
		            <a href="javascript://" class="${mapa.key}" onclick="abreGrafico('ul-${table}', '${table}')">${mapa.key}</a>
		            <div class="ul-${table}" style="display:none;">
			            <table id="datatable-${table}">
			                <thead>
			                    <tr>
			                        <th></th>
			                        <th>Vitórias</th>
			                        <th>Derrotas</th>
			                    </tr>
			                </thead>
			                <tbody>
			                    <c:forEach var="it" items="${mapa.value}">
				        			<tr>
							            <th>${it.jogador}</th>
							            <td>${it.qtdVitoria}</td>
							            <td>${it.qtdDerrota}</td>
							        </tr>
						      	 </c:forEach>
			                </tbody>
			            </table>
			            
			            <div id="chart-desempenho-${table}" class="plot-area"></div>
					</div>
		        </div>
			</c:forEach>
	    </div>		
	</div>
	
	<script>
	function abreGrafico(ul, table){
		$('.' + ul).slideToggle('slow');
		
		new Highcharts.chart('chart-desempenho-' + table, {
		    data: {
		        table: 'datatable-' + table
		    },
		    chart: {
		        type: 'column'
		    },
		    title: {
		        text: ''
		    },
		    yAxis: {
		        allowDecimals: false,
		        title: {
		            text: 'desempenho'
		        }
		    },
		    tooltip: {
		        formatter: function () {
		            return '<b>' + this.series.name + '</b><br/>' +
		                this.point.y + ' ' + this.point.name.toLowerCase();
		        }
		    }
		});
	}
	</script>
</body>
</html>