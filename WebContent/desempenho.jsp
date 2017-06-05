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
		<h2>Desempenho por número de sets</h2>
	
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
	
	function loadPlotByJogador(jogador) {
		$.ajax({
			url: '<c:url value="/desempenho/jogador" />',
			data:  {
				'exec': 'carregar',
				'jogador': jogador
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
				var derrotas = '<tr>';
				$.each(json.derrotas, function(index, obj) {
					derrotas += '<td>' + obj.numSets + ' Sets: ' + obj.qtd + '</td>';
				});
				derrotas += '</tr>';
				
				$("#container").append(
					'<h3 style="text-align:center;">Derrotas</h3>' +
					'<table><tbody>' +
					derrotas +
					'</tbody></table>'
				);
				
				var vitorias = '<tr>';
				$.each(json.vitorias, function(index, obj) {
					vitorias += '<td>' + obj.numSets + ' Sets: ' + obj.qtd + '</td>';
				});
				vitorias += '</tr>';
				
				$("#container").append(
					'<h3 style="text-align:center;">Vitorias</h3>' +
					'<table><tbody>' +
					vitorias +
					'</tbody></table>'
				);
			}
		})
	}
	</script>
</body>
</html>