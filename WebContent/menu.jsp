<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<nav class="menu">
    <img src="<c:url value="/css/atp-logo.svg" />" class="logo" />
    <ul class="menu-itens">
        <li><a href="<c:url value="/jogos/cidade" />">Jogos Por Cidade</a></li>
        <li><a href="<c:url value="/campeoes" />">Campeões</a></li>
        <li><a href="<c:url value="/desempenho" />">Desempenho por Superfície</a></li>
        <li><a href="<c:url value="/torneios/cidade" />">Torneio por Cidades</a></li>
        <li><a href="<c:url value="/ascencao" />">Ascenção Ano</a></li>
        <li><a href="<c:url value="/confronto" />">Confronto</a></li>
        <li><a href="<c:url value="/desempenho/jogador" />">Desempenho por Sets</a></li>
        <li><a href="">Metrica 01</a></li>
    </ul>
</nav>