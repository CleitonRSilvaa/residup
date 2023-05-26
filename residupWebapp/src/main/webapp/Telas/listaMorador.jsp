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
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="../css/lsita.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
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
            <a href="#">ÁREA DE RESERVAS</a>
            <a href="#">CONTROLE DE VISITANTES</a>
            <a href="#" >OCORRÊNCIAS DE MORADORES</a>
            <a href="#" class="active">REGISTRAR NOVO MORADOR</a>
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
    <!--Fim da header-->
<form class="filtro">
  <button class="cadastrar" >Cadastrar Morador</button>
  <button class="listar">Lista de Moradores</button>

  <form class="filtro">
  <button class="busc" action="/listarMorador" method="get" >Buscar</button>
  <input type="text" id="txtBsca" placeholder="Buscar Morador..."/>  
</form>
    <div class="form">
        <div class="title">
            <h3>Lista de Moradores</h3>
        </div>
        <%for (Morador morador : listaMorador) {%>
          <div class="group">
          <label><%=morador.getNome() + ' ' + morador.getSobrenome()%></label>
          <label class="oc"> <%=morador.getCpf()%></label>
          <label class="x"><%=morador.getNumeroApartamento()%></label>
          <label class="x"><%=morador.getBloco()%></label>
          <form action="/updateMorador" method="post">
              <a  href="#"><button class="editar" type="submit">Excluir</button></a>
          </form>
          <form action="/updateMorador" method="pull">
              <input type="hidden" name="cpfMorador" id="cpfMorador" value="<%=morador.getCpf()%>">
              <a  href="#"><button class="edit" type="submit">Editar</button></a>
          </form>
          </div>
          <hr>
        <%}%>
    </div>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
</body>
</html>