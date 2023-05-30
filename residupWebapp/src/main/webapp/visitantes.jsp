<%@page import="br.com.residup.models.Visitante"%>
<%@page import="br.com.residup.models.Morador"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Morador morador = (Morador) request.getAttribute("morador");
    ArrayList<Visitante> lista = (ArrayList<Visitante>) request.getAttribute("listaVisitantes");
%>

<!DOCTYPE html>
<html lang="pt-BR">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="css/controlevisitantes.css" rel="stylesheet" type="text/css">
        <link href="css/perfil.css" rel="stylesheet" type="text/css">
  <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

  <title>Controle de Visitantes</title>

</head>

<body>
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

            <a href="/listarResumo" >PAGINA INICIAL</a>
            <a href="/reservas" >RESERVAR ÁREA</a>
            <a href="/visitantes" class="active">CONTROLE DE VISITANTES</a>
            <a href="/Ocorrencia">REGISTRO DE OCORRÊNCIAS</a>
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
  <button type="button" onclick="cadastrarVisitante()" class="cadastrarvisitante">Novo Visitante</button>
  <button type="button" class="cadastrarvisitante"><a class="cadastrarvisitante" href="report">Relátorio</a></button>


  <form class="filtro" action="visitantes" method = "GET" >
  <button type='submit' class="busc">Buscar</button>
  <input type="text" id="txtBsca" name= "txtBsca" placeholder="Buscar Visitante..."/>
    </form>


  <!-- Modal de Cadastro -->
  <div id="modal" class="modal">
  </div>
  <div class="form">
    <div class="title">
        <h2>Seus Visitantes</h2>
    </div>
    <div class="group">
     <%
       for (int i = 0; i < lista.size(); i++) {
     %>
            <input placeholder="ID" name="ID" value="<%= lista.get(i).getId() %>" disabled></input>
            <input class="nm"  placeholder="Nome" name="nome" value="<%=lista.get(i).getNome()%> <%=lista.get(i).getSobrenome()%>"disabled></input>
            <input class="x" placeholder="Documento" name="doc" value="<%= lista.get(i).getDocumento() %>" disabled></input>
            <input class="oc" placeholder="Fone" name="fone" value="<%= lista.get(i).getFone() %>" disabled></input>
            <button type="submit" onclick= "editarVisitante('<%= lista.get(i).getId() %>',' <%=lista.get(i).getNome()%>' ,'<%=lista.get(i).getSobrenome()%>', '<%= lista.get(i).getDocumento() %>' ,'<%= lista.get(i).getFone() %>' )" class="editvisitante">Editar</button>
            <a  href="javascript: confirmar(<%=lista.get(i).getId()%>)"><button class="editarcad"  onclick="removerVisitante(${lista.get(i).getId()})">Excluir</button></a>

     <%
     }
     %>

    </div>
</div>

        <form  type="hidden" name="frmContato" action="insert" method="post">
            <input type="hidden" class="form-control" id="nome" name="nome" required>
            <input type="hidden" class="form-control" id="sobrenome" name="sobrenome" required>
            <input type="hidden" class="form-control" id="documento" name="documento" required>
            <input type="hidden" class="form-control" id="fone" name="fone">
        </form>





        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="../scripts/scripts.js"></script>
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
