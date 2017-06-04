<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DashBoard</title>
	<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/highcharts.src.js"></script>
	
	<style>
		.tituloCampeoes{
			font-size: 18px;
			font-family:"Lucida Grande", "Lucida Sans Unicode", Arial, Helvetica, sans-serif;
			text-align: center;
		}
		a{
			text-decoration: none;
			color:black;
		}
		.bloco{
			border:1px solid gray;
			float:left;
			overflow-y:auto;
			width:45%;
			height:300px;
			margin-left:5px;
			margin-bottom:5px;
		}
		.clear{
			clear: both;
		}
	</style>
</head>
<body>

	<div class="container">
	
		<div class="numeroJogosCidade bloco" >
			<jsp:include page="_numeroJogosCidade.jsp"/>
		</div>
		
		<div  class="maioresCampeoesPorTorneio bloco" >
			<jsp:include page="_maioresCampeoesPorTorneio.jsp"/>
		</div>
		
		<div  class="desempenhoPorSuperficie bloco" >
			<jsp:include page="_desempenhoPorSuperficie.jsp"/>
		</div>
		
		<div  class="numeroTorneiosCidade bloco" >
			<jsp:include page="_numeroTorneioCidade.jsp"/>
		</div>
	
	</div>
</body>
</html>