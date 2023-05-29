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
            <img src="img/LogoHeader.png" alt="Logo ResidUP" class="img_logo_header">
        </div>
        <div class="navigation_header" id="navigation_header">
            <button onclick="toggleSidebar()" class="btn_icon_header">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            </button>
            <a href="#">AREA DE RESERVAS</a>
            <a href="#">CONTROLE DE VISITANTES</a>
            <a href="#" class="active">OCORRÊNCIAS DOS MORADORES</a>
            <a href="#">REGISTRAR NOVO MORADOR</a>
        </div>
        <nav>
            <button id="openModal">Meu Perfil</button>

        <div id="modalOverlay" class="modal-overlay">
    <div class="modal">
            <h2>Seu Perfil</h2>
            <form action="#">
              <div class="perfil">
                <input type="text" id="nomePerfil" placeholder="Nome " name="Nome" disabled>

                <input type="text" id="nomeSobrenome" placeholder="Sobrenome" name="Sobrenome"  disabled>
                <label class="picture" for="picture__input" tabIndex="0">
                  <span class="picture__image"></span>
                </label>
                <label></label>

                <input type="file" name="picture__input" id="picture__input">
              </div>
              <div class="input-group2">
                <input type="rg" id="rgPerfil" placeholder="RG" maxlength="9" name="RG"  disabled>
                <input class="cpff" type="cpf" id="cpfPerfil" placeholder="CPF" maxlength="14" name="CPF"  disabled>
              </div>

              <div class="input-group2">
                <input type="rg" id="rgId" placeholder="apto" maxlength="9" name="Apto"  disabled>
                <input class="cpff" type="cpf" id="cpff" placeholder="Bloco" maxlength="14" name="Bloco"  disabled>
              </div>
                <div class="input-group2">
                    <input type="rg" id="rg" placeholder="Digite a nova Senha" maxlength="9" name="Senha" required>
                    <input class="cpff" type="cpf" id="cpf" placeholder="Confirme a Nova senha" maxlength="14" name="Confs" required>
                </div>
              <div class="input-group">
                <button class="cadastrar">Alterar Senha</button>
              </div>

            </form>
          </div>


    </div>
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

    </div>
    <%}%>

</div>
  <script src="../scripts/perfil.js"></script>


</body>

</html>