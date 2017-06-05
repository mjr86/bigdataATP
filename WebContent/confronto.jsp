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
		<h2>Vitórias por confronto</h2>
	
		<form>
			<input type="text" placeholder="Nome do jogador 1..." name="jogador1" class="search-input search-input-col-6" />
			<input type="text" placeholder="Nome do jogador 2..." name="jogador2" class="search-input search-input-col-6" />
			
			<button class="default-btn">BUSCAR</button>	
		</form>	
		
		<img src="<c:url value="/css/default.svg" />" class="loader" style="display:none;"/>
		<div class="plot-area" id="container">
		</div>
	</div>
	
	<script>
	$(document).ready(function() {
		$('.default-btn').on('click', function(e) {
			e.preventDefault();
			
			var $j1 = $("[name='jogador1']");
			var $j2 = $("[name='jogador2']");
			
			validateInput($j1);
			validateInput($j2);
			
			if (!$('.empty-input').length) {
				loadPlotByConfronto($j1.val(), $j2.val());
			}
		});
	});
	
	function validateInput($input) {
		if (!$input.val()) {
			$input.addClass('empty-input');
		} else {
			$input.removeClass('empty-input')
		}
	}
	
	var options = {
	    chart: {
	    	renderTo: 'container',
	        type: 'column'
	    },
	    title: {
	        text: ''
	    },
	    xAxis: {
	        type: 'category'
	    },
	    yAxis: {
	        title: {
	            text: 'Vitórias'
	        }
	    },
	    tooltip: {
	        pointFormat: '<span style="color:{point.color}">Vitórias</span>: <b>{point.y}</b><br/>'
	    },
	    legend: {
	        enabled: false
	    },
	    series: [{
	        colorByPoint: true,
	        data: []
	    }]
	};
	
	function loadPlotByConfronto(jogador1, jogador2) {
		$.ajax({
			url: '<c:url value="/confronto" />',
			data:  {
				'exec': 'carregar',
				'jogador1': jogador1,
				'jogador2': jogador2
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
				if (json.jogador1) {
					options.title.text = jogador1 + ' x ' + jogador2;
					
					options.series[0].data[0] = {
						name: json.jogador1,
						y: json.vitoriasJogador1
					};
					
					options.series[0].data[1] = {
						name: json.jogador2,
						y: json.vitoriasJogador2
					};
					
					new Highcharts.Chart(options);
				} else {
					$('#container').append('<h3 style="text-align:center;">Nenhum dado encontrado</h3>');
				}
			}
		})
	}
	</script>
</body>
</html>