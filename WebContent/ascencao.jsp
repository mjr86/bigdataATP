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
		<h2>Ascenção de Jogador por ano</h2>
	
		<form>
			<input type="text" placeholder="Nome do jogador..." name="jogador" class="search-input" />	
		</form>	
		
		<img src="<c:url value="/css/default.svg" />" class="loader" style="display:none;"/>
		<div class="plot-area" id="container">
		</div>
	</div>
	
	<script>
	$(document).ready(function() {
		$('[name="jogador"]').on('focusout', function() {
			loadPlotByJogador($(this).val());
		});
	});
	
	var options = {
		chart: {
			renderTo: 'container',
			type: 'area'
        },
        title: {
        	text: ''
        },
        xAxis: {
        	categories: [],
            labels: {
                formatter: function() {
                    return this.value;
                }
            }
        },
	    yAxis: {
	        title: {
	            text: 'Posição'
	        },
	        labels: {
	            formatter: function () {
	            	console.log(this.value);
	                return this.value;
	            }
	        }
	    },
	    series: [{
	    	data: []
	    }]
	};
	
	function loadPlotByJogador(jogador) {
		$.ajax({
			url: '<c:url value="/ascencao" />',
			data:  {
				'exec': 'carregar',
				'nomeJogador': jogador
			},
			dataType: 'json',
			beforeSend: function() {
				$('.loader').show();
				$('#container').html('');
			},
			complete: function() {
				$('.loader').hide();
			},
			success: function(json) {
				options.title.text = 'Ascenção do Jogador "' + jogador + '"';
				options.series[0].name = jogador;
				$.each(json, function(i, obj) {
					options.series[0].data[i] = obj.ranking;
					options.xAxis.categories[i] = obj.ano;
				})
				new Highcharts.Chart(options);
			}
		})
	}
	</script>
</body>
</html>