<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="br.com.residup.models.Ocorrencia"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>


<%
     Ocorrencia ocorrenciaGet = (Ocorrencia) request.getAttribute("ocorrenciaUnica");
 %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="css/ocorrenciadetalhada.css" rel="stylesheet" type="text/css"/>
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
            <a href="#">ÁREA DE RESERVAS</a>
            <a href="#">CONTROLE DE VISITANTES</a>
            <a href="HeaderVisitantes.html" class="active">OCORRÊNCIAS DE MORADORES</a>
            <a href="HeaderOcorrencias.html">REGISTRR NOVO MORADOR</a>
        </div>
        <nav>
            <button id="openModal">Meu Perfil</button>

        <div id="modalOverlay" class="modal-overlay">
    <div class="modal">
            <h2>Seu Perfil</h2>
            <form action="#">
              <div class="perfil">
                <input type="text" id="nome" placeholder="Nome " name="Nome" disabled>

                <input type="text" id="nome" placeholder="Sobrenome" name="Sobrenome"  disabled>
                <label class="picture" for="picture__input" tabIndex="0">
                  <span class="picture__image"></span>
                </label>
                <label></label>
   
                <input type="file" name="picture__input" id="picture__input">
              </div>
              <div class="input-group2">
                <input type="rg" id="rg" placeholder="RG" maxlength="9" name="RG"  disabled>
                <input class="cpff" type="cpf" id="cpf" placeholder="CPF" maxlength="14" name="CPF"  disabled>
              </div>
   
              <div class="input-group2">
                <input type="rg" id="rg" placeholder="apto" maxlength="9" name="Apto"  disabled>
                <input class="cpff" type="cpf" id="cpf" placeholder="Bloco" maxlength="14" name="Bloco"  disabled>
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
    <div class="form">
        <div class="title">
            <h3>OCORRENCIA DOS MORADORES</h3>
        </div>
        <form action="carregarOcorrencia" method="GET">
        <div class="group">
            <label class="morador">${ocorrenciaGet.getNome()}</label>
            <label class="apto">${ocorrenciaGet.getNumeroApartamento()}</label>
            <label>${ocorrenciaGet.getBloco()}</label>
                <select class="sts" id="form-horario" required>
                  <option value="Todos" ${ocorrenciaGet.getStatus().equals("Todos") ? 'selected' : ''}>Todos</option>
                  <option value="Em aberto" ${ocorrenciaGet.getStatus().equals("Em aberto") ? 'selected' : ''}>Em aberto</option>
                  <option value="Em análise" ${ocorrenciaGet.getStatus().equals("Em análise") ? 'selected' : ''}>Em análise</option>
                  <option value="Em andamento" ${ocorrenciaGet.getStatus().equals("Em andamento") ? 'selected' : ''}>Em andamento</option>
                  <option value="Resolvido" ${ocorrenciaGet.getStatus().equals("Resolvido") ? 'selected' : ''}>Resolvido</option>
                </select>
        </div>
        <div class="ocorrencia">
            <label for="nome"> Detalhes da Ocorrencia</label>
            <textarea placeholder="Descrição da ocorrencia">${ocorrenciaGet.getTexto()}</textarea>
        </div>
        <div class="resolv">
            <a class="resolv" href="#"><button type:"submit" class="resolvido" >Salvar</button></a>
        </div>

        </form>
        <div class="volt">
            <a href="#"><button class="voltar">Voltar</button></a>
        </div>
          
    </div> 
    <script src="scripts/ocorrenciadetalhada.js"></script>
</body>
</html>
