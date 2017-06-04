<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


		<script>
			function abreGrafico(ul){
				$('.'+ul).toggle('slow');
			}
		</script>
		<p class="tituloCampeoes"> 
			Campeões por Torneio
		</p>
			 
		
		<c:forEach var="mapa" items="${requestScope.mapaMaioresCampeoesPorTorneio}">
		 <a href="javascript://" class="${mapa.key}" onclick="abreGrafico('ul-${mapa.key.replaceAll(' ','_')}')" > >> ${mapa.key} </a>
			<ul class="ul-${mapa.key.replaceAll(' ','_')}" style="display:none;">
				<div id="chart-${mapa.key.replaceAll(' ','_')}" style="width:95%; height:250px;"></div>
				<script>
					// Create the chart
					Highcharts.chart('chart-${mapa.key.replaceAll(' ','_')}', {
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
				
			</ul>	
			<br/><br/><br/>
		</c:forEach>




