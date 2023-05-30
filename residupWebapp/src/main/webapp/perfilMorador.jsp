<%@page import="br.com.residup.models.Morador"%>

<%--<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
    Morador morador = (Morador) request.getAttribute("morador");

%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/perfil.css">
    <title>Header Reserva</title>
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
            <a href="#" class="active">PAGINA INICIAL</a>
            <a href="#">RESERVAR ÁREA</a>
            <a href="HeaderVisitantes.html">CONTROLE DE VISITANTES</a>
            <a href="HeaderOcorrencias.html">REGISTRO DE OCORRENCIAS</a>
        </div>
        <nav>
        <form action="perfilMorador" method="get">
            <button type="submit" id="openModal">Meu Perfil</button>
            </form>
        <div id="modalOverlay" class="modal-overlay">
    <div class="modal">
            <h2>Seu Perfil</h2>
            <c:if test="${not empty morador}">

            <form action="updatePerfilMorador" method="post" enctype="multipart/form-data">
              <div class="perfil">
                <input type="text" id="nome" placeholder="Nome " name="Nome" value="<%= morador.getNome() + ' ' + morador.getSobrenome() %>" disabled>

                <input type="text" id="nome" placeholder="Sobrenome" name="Sobrenome" value="<%= morador.getSobrenome() %>"  disabled>
                <label class="picture" for="picture__input" tabIndex="0">
                  <span class="picture__image"></span>
                </label>
                <label></label>
    
                <input type="file" name="picture__input" id="picture__input" value="<%= morador.getEnderecoFoto() %>" >
              </div>
              <div class="input-group2">
                <input type="rg" id="rg" placeholder="RG" maxlength="9" name="RG" value= <%= morador.getRg() %> disabled>
                <input class="cpff" type="cpf" id="cpf" placeholder="CPF" maxlength="14" name="CPF" value= <%= morador.getCpf() %> disabled>
              </div>
    
              <div class="input-group2">
                <input type="rg" id="rg" placeholder="apto" maxlength="9" name="Apto" value= <%= morador.getNumeroApartamento() %> disabled>
                <input class="cpff" type="cpf" id="cpf" placeholder="Bloco" maxlength="14" name="Bloco" value= <%= morador.getBloco() %> disabled>
              </div>
                <div class="input-group2">
                    <input type="rg" id="rg" placeholder="Digite a nova Senha" maxlength="9" name="senha" required>
                    <input class="cpff" type="cpf" id="cpf" placeholder="Confirme a Nova senha" maxlength="14" name="Confs" required>
                </div>
              <div class="input-group">
                <button type="submit" class="cadastrar">Alterar Senha</button>
              </div>
    
            </form>
            </c:if>
          </div>


    </div>
        </nav>
    </div>

        <script>
          // Obtenha uma referência ao botão "Meu Perfil" e ao modal
          const openModalButton = document.getElementById('openModal');
          const modalOverlay = document.getElementById('modalOverlay');

          openModalButton.addEventListener('click', function () {
                  event.preventDefault();
                  modalOverlay.style.opacity = '1';
                  modalOverlay.style.pointerEvents = 'auto';
                });

                modalOverlay.addEventListener('click', function (event) {
                        if (event.target === modalOverlay) {
                          modalOverlay.style.opacity = '0';
                          modalOverlay.style.pointerEvents = 'none';
                        }
                      });

        </script>

        <script src="scripts/perfil.js"></script>

</body>
</html>