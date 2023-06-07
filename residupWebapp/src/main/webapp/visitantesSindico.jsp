<%@page import="br.com.residup.models.Visitante"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    ArrayList<Visitante> lista = (ArrayList<Visitante>) request.getAttribute("listaVisitantes");
%>
<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../css/cvisitantessind.css">
  <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

  <title>Controle de Visitantes Sindico</title>

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
               <a href="/visitantesAdm" class="active" >CONTROLE DE VISITANTES</a>
               <a href="/OcorrenciaAdm">OCORRÊNCIAS DE MORADORES</a>
               <a href="/cadastro_morador">REGISTRAR NOVO MORADOR</a>
        </div>
        <nav>
            <ul>
                <li class="dropdown">
                    <a href="/logout">Logout</a>
                </li>
            </ul>
        </nav>
    </div>
  <form class="filtro" action="visitantesAdm" method="GET">
  <button type="submit" class="busc">Buscar</button>
  <input type="date" id="txtBusca" name="dataFiltro" >
</form>

  <div class="form">
    <div class="title">
        <h2>Visitantes</h2>
    </div>
    <%for (Visitante visitante : lista) {%>
    <div class="group">
        <input class="nm"  value="<%=visitante.getNome()%>  <%=visitante.getSobrenome()%>"  visitante name="nome" disabled></input>
        <input class="x" value="<%=visitante.getDocumento()%>" name="doc" disabled></input>
        <input class="oc" value="<%=visitante.getFone()%>" name="fone" disabled></input>
        <input class="apto" value="<%=visitante.getMorador().getNumeroApartamento()%>" name="fone" disabled></input>
        <input class="apto" value="<%=visitante.getMorador().getBloco()%>" name="fone" disabled></input>

<%
        if (visitante.getCheckIn() == null) {

        %>
        <form id="ck" action="CheckInVisitantes" method="POST">
         <input class="editar" type="hidden" name="idReserva" id="idRegistro" value="<%=visitante.getIdRegistro()%>">
          <button type="submit" class="editarcad">Check-in</button>
        </form>
        <%
            }
        %>
    </div>
    <%}%>

</div>
  <script src="../scripts/perfil.js"></script>
   <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
          <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">

           <c:if test="${not empty mensagem}">
                      <%-- Exibe o alerta somente se a mensagem não for nula --%>
                      <script>
                          <%= request.getAttribute("mensagem")%>
                      </script>
                  </c:if>


</body>

</html>