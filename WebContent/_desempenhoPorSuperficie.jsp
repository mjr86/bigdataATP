<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script src="${pageContext.request.contextPath}/js/data.js"></script>

		<script>
			function abreGrafico(ul){
				$('.'+ul).toggle('slow');
			}
		</script>
		<p class="tituloCampeoes clear"> 
			Desempenho por Superficie de Quadra
		</p>
			 
		
		<c:forEach var="mapa" items="${requestScope.mapaDesempenhoPorSuperficie}">
		 <a href="javascript://" class="${mapa.key}" onclick="abreGrafico('ul-${mapa.key.replaceAll(' ','_')}')" > >> ${mapa.key} </a><br/><br/>
			<ul class="ul-${mapa.key.replaceAll(' ','_')}" style="display:none;">
			
				<table id="datatable-${mapa.key.replaceAll(' ','_')}">
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
			
				<div id="chart-desempenho-${mapa.key.replaceAll(' ','_')}" style="width:95%; height: 250px;"></div>
				<script>
				Highcharts.chart('chart-desempenho-${mapa.key.replaceAll(' ','_')}', {
				    data: {
				        table: 'datatable-${mapa.key.replaceAll(' ','_')}'
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
				</script>
				
			</ul>	
			
			
			
		</c:forEach>




