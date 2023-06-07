<%@page import="br.com.residup.models.Morador"%>

<%--<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>--%>
<%@page import="java.util.ArrayList"%>
<%
    @SuppressWarnings(
            "unchecked")
    ArrayList<Morador> listaMorador = (ArrayList<Morador>) request.getAttribute("moradores");


%>


<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/lsita.css" rel="stylesheet" type="text/css">
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
    <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

    <title>Lista Morador</title>
</head>
<body class="fundoBody">
    <div class="header" id="header">
        <button onclick="toggleSidebar()" class="btn_icon_header">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </button>
        <div class="logo_header">
            <img src="../imagens/img/LogoHeader.png" alt="Logo ResidUP" class="img_logo_header">
        </div>
        <div class="navigation_header" id="navigation_header">
            <button onclick="toggleSidebar()" class="btn_icon_header">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            </button>
            <a href="/reservasAdmin">ÁREA DE RESERVAS</a>
            <a href="/visitantesAdm">CONTROLE DE VISITANTES</a>
            <a href="/OcorrenciaAdm">OCORRÊNCIAS DE MORADORES</a>
            <a href="/cadastro_morador" class="active">REGISTRAR NOVO MORADOR</a>
        </div>
        <nav>
            <ul>
                <li class="dropdown">
                    <a href="/logout">Logout</a>

                </li>
            </ul>
        </nav>
    </div>  
    <!--Fim da header-->
<form class="filtro" action="/listarMorador" method="post">
  <button class="cadastrar"><a href="/cadastro_morador" style="color: white; text-decoration: none; font-size: 16px;">Cadastrar Morador</a></button>

  <button class="busc" type="submit">Buscar</button>
  <input type="text" id="txtBsca" name="txtBsca" placeholder="Buscar Morador..."/>
</form>
    <div class="form">
        <div class="title">
            <h3>Lista de Moradores</h3>
        </div>
        <% for (Morador morador : listaMorador) { %>
            <div class="group">
                <label><%= morador.getNome() + ' ' + morador.getSobrenome() %></label>
                <label class="oc"><%= morador.getCpf() %></label>
                <label class="x"><%= morador.getNumeroApartamento() %></label>
                <label class="x"><%= morador.getBloco() %></label>

                <% if (morador.getStatus().equals("1")) { %>
                            <form id="op" action="/deleteMorador" method="get">
                                <input type="hidden" name="cpfMorador" id="cpfMorador" value="<%= morador.getCpf() %>">
                                <button class="editar" type="submit">Excluir</button>
                            </form>
                        <% } %>

                <% if (morador.getStatus().equals("0")) { %>
                            <form id="op" action="/ativarMorador" method="get">
                                <input type="hidden" name="cpfMorador" id="cpfMorador" value="<%= morador.getCpf() %>">
                                <button class="editAtivar" type="submit">Ativar Morador</button>
                            </form>
                        <% } %>

                <form id="op" action="/carregarMorador" method="get">
                                                <input type="hidden" name="cpfMorador" id="cpfMorador" value="<%= morador.getCpf() %>">
                                                <button class="edit" type="submit">Editar</button>
                                            </form>

            </div>
            <hr>
        <% } %>
    </div>
    
    <script src="../scripts/bootstrap.bundle.min.js"></script>
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