<%@page import="br.com.residup.models.Morador"%>
<%@page import="br.com.residup.models.Ocorrencia"%>
<%@page import="br.com.residup.models.Visitante"%>
<%@page import="br.com.residup.models.Reserva"%>

<%--<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>--%>
<%@page import="java.util.ArrayList"%>
<%
    @SuppressWarnings(
            "unchecked")


    ArrayList<Ocorrencia> listaOcorrencia = (ArrayList<Ocorrencia>) request.getAttribute("minhasOcorrencias");
    ArrayList<Reserva> listaReserva = (ArrayList<Reserva>) request.getAttribute("minhasReservas");
    ArrayList<Visitante> listaVisitante = (ArrayList<Visitante>) request.getAttribute("meusVisitantes");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/resumo.css" rel="stylesheet" type="text/css"/>
    <title>Document</title>
</head>
<body>
    <div class="header" id="header">
        <button onclick="toggleSidebar()" class="btn_icon_header">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </button>
        <div class="logo_header">
            <img src="img/LogoHeader.png" alt="Logo ResidUP" class="img_logo_header">
        </div>
        <div class="navigation_header" id="navigation_header">
            <button onclick="toggleSidebar()" class="btn_icon_header">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            </button>
            <a href="/listarResumo" class="active">PAGINA INICIAL</a>
            <a href="/reservas" >RESERVAR ÁREA</a>
            <a href="/visitantes">CONTROLE DE VISITANTES</a>
            <a href="/Ocorrencia">REGISTRO DE OCORRENCIAS</a>
        </div>
        <nav>
            <ul>
                <li class="dropdown">
                    <a href="">MEU PERFIL</a>
    
                    <div class="dropdown-menu">
                        <a href="">Editar perfil</a>
                        <a href="">Sair da Conta</a>
                    </div>
                </li>
            </ul>
        </nav>
    </div>
    <!-- Fim Header -->
    <c:if test="${not empty listaReserva}">
        <div class="mr">
            <div class="title">
                <h3>Minhas Reservas</h3>
            </div>
            <%for (Reserva reserva : listaReserva) {%>
            <div class="group">
                <label><%=reserva.getIdReserva()%></label>
                <label class="oc"><%=reserva.getNomeArea()%></label>
                <label class="dt"></label>
              </div>
              <hr>
              <%}%>
        </div>
    </c:if>
    <c:if test="${not empty listaVisitante}">
        <div class="mv">
                <div class="title">
                    <h3>Meus Visitantes</h3>
                </div>
                <%for ( Visitante visitante : listaVisitante) {%>
                <div class="group">
                    <input type="text" placeholder="ID"  name="ID" required><%=visitante.getId()%></input>
                    <input class="nm"  placeholder="Nome" name="nome" disabled><%=visitante.getNome() + ' ' + visitante.getSobrenome()%></input>
                    <input class="x" placeholder="Documento" name="doc" disabled><%=visitante.getDocumento()%></input>
                    <input class="oc" placeholder="Fone" name="fone" disabled><%=visitante.getFone()%></input>
                </div>
                <hr>
                <%}%>
            </div>
            </div>
        </div>
    </c:if>
    <c:if test="${not empty listaOcorrencia}">

        <div class="mo">
            <div class="title">
                <h3>Minhas Ocorrências</h3>
            </div>
            <%for (Ocorrencia ocorrencia : listaOcorrencia) {%>
             <div class="group">
                <label><%=ocorrencia.getTitulo()%></label>
                <label class="oc"><%=ocorrencia.getTexto()%></label>
                <label class="or"><%=ocorrencia.getStatus()%></label>
             </div>
             <hr>
             <%}%>
        </div>
    </c:if>
</body>
</html>