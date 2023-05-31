<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="br.com.residup.models.Ocorrencia"%>
<%@page import="br.com.residup.daos.OcorrenciaDao"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>

<%
    ArrayList<Ocorrencia> lista = (ArrayList<Ocorrencia>) request.getAttribute("ocorrencias");
%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/ocorrenciassind.css" rel="stylesheet" type="text/css" >
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

    <title>Ocorrência Sindico</title>
</head>
<body>
    <div class="header" id="header">
        <button onclick="toggleSidebar()" class="btn_icon_header">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </button>
        <div class="logo_header">
            <img src="imagens/img/LogoHeader.png" alt="Logo ResidUP" class="img_logo_header">
        </div>
        <div class="navigation_header" id="navigation_header">
            <button onclick="toggleSidebar()" class="btn_icon_header">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            </button>
               <a href="/reservasAdmin" >ÁREA DE RESERVAS</a>
               <a href="/visitantesAdm">CONTROLE DE VISITANTES</a>
               <a href="/OcorrenciaAdm" class="active">OCORRÊNCIAS DE MORADORES</a>
               <a href="/cadastro_morador">REGISTRAR NOVO MORADOR</a>
        </div>
        <nav>
            <ul>
                <li class="dropdown">
                    <a href="">Logout</a>
                </li>
            </ul>
        </nav>
    </div>
    <!--Fim da header-->

    <form class="filtro" action="OcorrenciaAdm" method="GET">
        <button type="submit" class="filtrar">Filtrar</button>
      <select class="form-status" id="form-status"  name="status-filter"required>
        <option value="Em aberto" <c:if test="${filtroOcorrencias.equalsIgnoreCase('Em aberto')}">selected</c:if>>Em aberto</option>
        <option value="todos"<c:if test="${filtroOcorrencias.equalsIgnoreCase('Todos')}">selected</c:if>>Todos</option>
        <option value="Em analise" <c:if test="${filtroOcorrencias.equalsIgnoreCase('Em analise')}">selected</c:if>>Em análise</option>
        <option value="Em andamento" <c:if test="${filtroOcorrencias.equalsIgnoreCase('Em andamento')}">selected</c:if>>Em andamento</option>
        <option value="Resolvido" <c:if test="${filtroOcorrencias.equalsIgnoreCase('Resolvido')}">selected</c:if>>Resolvido</option>
        </select>
    </form>
    <div class="form">
        <div class="title">
            <h3>OCORRÊNCIA DOS MORADORES</h3>
        </div>
        <c:forEach var="ocorrencia" items="${ocorrencias}">
        <div class="group">
            <label>${ocorrencia.getNome()}</label>
            <label class="x">${ocorrencia.getNumeroApartamento()}</label>
            <label class="x">${ocorrencia.getBloco()}</label>
            <label class="oc">${ocorrencia.getTitulo()}</label>
            <label class="sts">${ocorrencia.getStatus()}</label>

          <form action="carregarOcorrencia" method="POST">
              <input type="hidden" name="idOcorrenciaSindico" value="${ocorrencia.getId_ocorrencia()}">
              <button class="ver" type="submit">Ver</button>
          </form>


        </div>
        <hr>
        </c:forEach>
    </div>
    <script src="../scripts/ocorrenciassind.js"></script>
    <script src="../scripts/bootstrap.bundle.min.js"></script>

</body>
</html>