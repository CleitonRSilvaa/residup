<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page import="br.com.residup.models.Ocorrencia"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@page import="java.util.ArrayList"%>

<%
     Ocorrencia ocorrenciaGet = (Ocorrencia) request.getAttribute("ocorrenciaUnica");
 %>



<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="../css/ocorrenciadetalhada.css" rel="stylesheet" type="text/css"/>
    <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

    <title>Ocorrência Detalhada</title>
</head>
<body>
   <style>
          :root{
              --color-white: #fff;
              --color-dark1: rgb(39, 39, 39);
              --color-dark2: #2d2d2d;
              --color-dark3: #414141;
              --color-dark4: #1c1c1c;
              --color-dark5: #343434;
              --color-purple: #b040ff;
          }
          *{
              margin: 0;
              padding: 0;
          }
          body{
              font-family: Arial, Helvetica, sans-serif;
              background-color: #f1f1f1;
              color: white;
          }


          .header,
          .navigation_header{
              display: flex;
              flex-direction: row;
              align-items: center;
          }
          .header{
              background-color: #c4c4c4;
              justify-content: space-between;
              padding: 0 10px;
              height: 3.5em;
          }

          .navigation_header{
              gap: 3em;
              z-index: 2;
          }
          .content{
              padding-top: 5em;
              text-align: center;
              height: 100vh;
              transition: 1s;
          }
          .navigation_header a{
              text-decoration: none;
              color: black;
              transition: 1s;
              font-weight: bold;
              font-size: 12px;
              margin-left: 30px;
          }
          .navigation_header a:hover{
              color: red;
          }
          .active{
                  background-image: linear-gradient(to right, #006494, #1b98e0);
              padding: 10px;
              border-radius: 10px;

          }
          .btn_icon_header{
              background: transparent;
              border: none;
              color: var(--color-white);
              cursor: pointer;
              display: none;
          }

          nav{
             background-color: red;
             border-radius: 10px;
             Color: white;
             Font-weight: bold;
          }

          nav li{
             display: inline-block;
          }

          nav li a{
             display: inline-block;
                color: white;
                text-decoration: none;
                padding: 10px;
          }

          nav li a:hover{
              background-color: #545454;
              border-radius: 10px;
          }

          .dropdown-menu{
              position: absolute;
              box-shadow: 0 0 2px black;
              display: none;
          }

          .dropdown-menu a{
              display: block;
              color: black;
          }

          .dropdown:hover .dropdown-menu{
              display: block;
          }

          @media screen and (max-width: 768px) {
              .navigation_header{
                  position: absolute;
                  flex-direction: column !important;
                  top: 0;
                  background: #c4c4c4;
                  height: 70%;
                  width: 35vw;
                  padding: 1em;
                  animation-duration: 1s;
                  margin-left: -7vw;
              }
              .btn_icon_header{
                  display: block;
              }
          }
          @keyframes showSidebar {
              from {margin-left: -100vw;}
              to {margin-left: -vw;}
          }

          body {
              font-family: Arial, sans-serif;
              background-color: #f1f1f1;
              margin: 0;
              padding: 0;
            }

            .modal-overlay {
              position: fixed;
              top: 0;
              left: 0;
              right: 0;
              bottom: 0;
              background-color: rgba(0, 0, 0, 0.5);
              display: flex;
              justify-content: center;
              align-items: center;
              z-index: 9999;
              opacity: 0;
              pointer-events: none;
              transition: opacity 0.3s ease;
            }

            .modal {
              width: 60%;
              height: 400px;
              background-color: #1d3354;
              padding: 20px;
              border-radius: 8px;
              box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .modal h2 {
              color: white;
            }

            .form-box {
              margin-top: 25px;
              background-color: #c4c4c4;
              backdrop-filter: blur(40px);
              padding: 30px 40px;
              display: block;
              width: 100%;
              height: 400px;
              border-radius: 20px 20px 20px 20px;
              align-items: center;
            }

            .form-box h2 {
              font-size: 20px;
            }

            .form-box p {
              font-weight: bold;
              color: #3D3D3D;
            }

            .form-box p a {
              color: #49ae96;
              text-decoration: none;
            }

            .form-box form {
              margin: 20px 0;
            }

            form .input-group {
              margin-bottom: 15px;
            }

            form .input-group label {
              color: #3D3D3D;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
            }

            form .input-group input {
              width: 100%;
              height: 5px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: #616161;
              transition: all 0.4s ease;
            }
            form .input-group2 .cpf {
              color: black;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
              float: right;
              margin-top: 10px;
            }
            form .input-group2 input {
              width: 40%;
              height: 10px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: black;
              transition: all 0.4s ease;
              margin-top: 10px;
            }
            form .input-group2  .cpff{
              margin-top: 10px;
              width: 40%;
              height: 10px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: black;
              transition: all 0.4s ease;
              float: right;
            }


            form .input-group input:focus {
              border-color: #49ae96;
            }

            form .input-group button {
              width: 100%;
              height: 47px;
              background: #49ae96;
              border-radius: 20px;
              outline: none;
              border: none;
              margin-top: 15px;
              margin-left: 2px;
              color: white;
              cursor: pointer;
              font-size: 16px;
              opacity: 100%;
            }

            #picture__input {
              display: none;
            }

            .picture {
              border-radius: 90%;
              width: 12%;
              aspect-ratio: 4/4;
              background: #ddd;
              display: flex;
              align-items: center;
              justify-content: center;
              color: #aaa;
              border: 2px dashed currentcolor;
              cursor: pointer;
              font-family: sans-serif;
              transition: color 300ms ease-in-out, background 300ms ease-in-out;
              outline: none;
              overflow: hidden;
            }

            .picture:hover {
              color: #777;
              background: #ccc;
            }

            .picture:active {
              border-color: turquoise;
              color: turquoise;
              background: #eee;
            }

            .picture:focus {
              color: #777;
              background: #ccc;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            .picture__img {
              max-width: 100%;
            }

            .perfil {
              margin-top: 0px;
              align-items: center;
              width: 100%;
              background-color: #1d3354;
              border-radius: 15px;

            }

            .perfil .nome {
              margin-right: 75%;
              float: right;
              color: #3D3D3D;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
            }

            .perfil .sobrenome {
              margin-right: 72%;
              float: right;
              color: #3D3D3D;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
            }

            .perfil input {
              margin-top: 10px;
              width: 80%;
              height: 5px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: #616161;
              transition: all 0.4s ease;
              float: right;
            }

            .perfil input:focus {
              border-color: #49ae96;
            }
            .modal-overlay {

              position: fixed;
              top: 0;
              left: 0;
              right: 0;
              bottom: 0;
              background-color: rgba(0, 0, 0, 0.5);
              display: flex;
              justify-content: center;
              align-items: center;
              z-index: 9999;
              opacity: 0;
              pointer-events: none;
              transition: opacity 0.3s ease;
            }

            .modal {
              width: 60%;
              height: 400px;
              background-color: #1d3354;
              padding: 20px;
              border-radius: 8px;
              box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            .modal h2 {
              color: white;
            }

            .form-box {
              margin-top: 25px;
              background-color: #c4c4c4;
              backdrop-filter: blur(40px);
              padding: 30px 40px;
              display: block;
              width: 100%;
              height: 400px;
              border-radius: 20px 20px 20px 20px;
              align-items: center;
            }

            .form-box h2 {
              font-size: 20px;
            }

            .form-box p {
              font-weight: bold;
              color: #3D3D3D;
            }

            .form-box p a {
              color: #49ae96;
              text-decoration: none;
            }

            .form-box form {
              margin: 20px 0;
            }

            form .input-group {
              margin-bottom: 15px;
            }

            form .input-group label {
              color: #3D3D3D;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
            }

            form .input-group input {
              width: 100%;
              height: 5px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: #616161;
              transition: all 0.4s ease;
            }
            form .input-group2 .cpf {
              color: black;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
              float: right;
              margin-top: 10px;
            }
            form .input-group2 input {
              width: 40%;
              height: 10px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: black;
              transition: all 0.4s ease;
              margin-top: 10px;
            }
            form .input-group2  .cpff{
              margin-top: 10px;
              width: 40%;
              height: 10px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: black;
              transition: all 0.4s ease;
              float: right;
            }


            form .input-group input:focus {
              border-color: #49ae96;
            }

            form .input-group button {
              width: 100%;
              height: 47px;
              background: #49ae96;
              border-radius: 20px;
              outline: none;
              border: none;
              margin-top: 15px;
              margin-left: 2px;
              color: white;
              cursor: pointer;
              font-size: 16px;
              opacity: 100%;
            }

            #picture__input {
              display: none;
            }

            .picture {
              border-radius: 90%;
              width: 12%;
              aspect-ratio: 4/4;
              background: #ddd;
              display: flex;
              align-items: center;
              justify-content: center;
              color: #aaa;
              border: 2px dashed currentcolor;
              cursor: pointer;
              font-family: sans-serif;
              transition: color 300ms ease-in-out, background 300ms ease-in-out;
              outline: none;
              overflow: hidden;
            }

            .picture:hover {
              color: #777;
              background: #ccc;
            }

            .picture:active {
              border-color: turquoise;
              color: turquoise;
              background: #eee;
            }

            .picture:focus {
              color: #777;
              background: #ccc;
              box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }

            .picture__img {
              max-width: 100%;
            }

            .perfil {
              margin-top: 0px;
              align-items: center;
              width: 100%;
              background-color: #1d3354;
              border-radius: 15px;

            }

            .perfil .nome {
              margin-right: 75%;
              float: right;
              color: #3D3D3D;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
            }

            .perfil .sobrenome {
              margin-right: 72%;
              float: right;
              color: #3D3D3D;
              font-weight: bold;
              display: block;
              margin-bottom: 5px;
            }

            .perfil input {
              margin-top: 10px;
              width: 80%;
              height: 5px;
              background-color: rgba(255, 255, 255, 0.32);
              border-radius: 20px;
              outline: none;
              border: 2px solid transparent;
              padding: 15px;
              font-size: 15px;
              color: #616161;
              transition: all 0.4s ease;
              float: right;
            }

            .perfil input:focus {
              border-color: #49ae96;
            }
          .form{
              background-color: #fff;
              height: 650px;
              width: 97%;
              margin-left: 1.5%;
              border-radius: 10px;
              margin-top: 3%;

          }

          .title{
              margin: 0;
              padding: 10px;
              border-radius: 10px 10px 0 0;
              color: black;
              background-image: linear-gradient(to right, #006494, #1b98e0);
          }
          .title h3{
              font-size: 18px;
              color: white;
              font-weight: bold;
          }

          .form .group{
              margin-top: 20px;
              margin-bottom: 5px;
              display: inline-block;
              width: 100%;
          }
          form .group img{
              height: 70px;
              width: 5%;
          }
          form .group .resolvido{
              height: 60px;
              width: 5%;
          }

          form .group label{
              width: 5%;
              height: 28px;
              border: 2px solid #1b98e0;
              background-color: #FFF;
              color: #616161;
              border-radius: 15px;
              outline: none;
              margin-left: 5px;
              margin-right: 5px;
              font-size: 15px;
              transition: all 0.4s ease;
              display: inline-block;

          }
          .form .group .apto{
              width: 5%;
              height: 28px;
              border: 2px solid #1b98e0;
              background-color: #FFF;
              color: #616161;
              border-radius: 15px;
              outline: none;
              margin-left: 5px;
              margin-right: 5px;
              font-size: 15px;
              transition: all 0.4s ease;
              display: inline-block;
          }
          .form .group .sts{
              float: right;
              width: 10%;
              height: 28px;
              border: 20;
              border-color: rgba(129, 4, 4, 0.938);
              background-color: #FFF;
              border-radius: 15px;
              outline: none;
              margin-right: 5%;
              font-size: 15px;
              color: #616161;
              transition: all 0.4s ease;
              display: inline-block;
              text-align: center;
          }
          .form .group .morador{
              width: 25%;
              height: 28px;
              border: 2px solid #1b98e0;
              background-color: #FFF;
              color: #616161;
              border-radius: 15px;
              outline: none;
              margin-left: 3%;
              margin-right: 5px;
              font-size: 15px;
              transition: all 0.4s ease;
              display: inline-block;
              align-items: center;
          }

          .form .group input:focus{
              border-color: #49ae96;
          }
          .form .group button{
              width: 100%;
              height: 47px;
              background: #49ae96;
              border-radius: 20px;
              outline: none;
              border: none;
              margin-top: 15px;
              color: white;
              cursor: pointer;
              font-size: 16px;
          }
          .ocorrencia{
              margin-top: 30px;
              margin-left: 3%;
          }
          .ocorrencia label{
              margin-left: 2.5%;
              font-size: 17px;
              font-weight: bold;
              color: white;
          }
          textarea{
              width: 95%;
              height: 400px;
              margin-left: 2px;
              border-color: #1b98e0;
              border: 20;
              border-radius: 20px;
              margin-top: 5px;

          }
          .resolv .resolvido{
              margin-top: 10px;
              width: 12%;
              height: 40px;
              background-image: linear-gradient(to right, #006494, #561be0);
              text-decoration: none;
              float: right;
              margin-right: 4%;
              color: white;
              text-align: center;
              align-items: center;
              border-radius: 15px;
              font-weight: bold;
              border: none;

          }
          .resolv .resolvido a:hover{
              color: red;
          }
          .volt .voltar{
              margin-top: 10px;
              width: 12%;;
              height: 40px;
              background-image: linear-gradient(to right, #310909, #e01e1e);
              text-decoration: none;
              float: right;
              margin-right: 2%;
              color: white;
              text-align: center;
              align-items: center;
              border-radius: 15px;
              font-weight: bold;
              border: none;

          }
   </style>


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
                <a href="/reservasAdmin" >ÁREA DE RESERVAS</a>
                <a href="/visitantesAdm">CONTROLE DE VISITANTES</a>
                <a href="/OcorrenciaAdm" class="active">OCORRÊNCIAS DE MORADORES</a>
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





           <div class="form">
               <div class="title">
                   <h3>OCORRÊNCIA DOS MORADORES</h3>
               </div>
<form action="resolver" method="POST">
    <div class="group">
        <label class="morador"><%=ocorrenciaGet.getNome()%></label>
        <label class="apto"><%=ocorrenciaGet.getNumeroApartamento()%></label>
        <label><%=ocorrenciaGet.getBloco()%></label>
        <select class="sts" id="form-horario" name="status" required>
            <option value="Em aberto" ${ocorrenciaGet.getStatus().equals("Em aberto") ? 'selected' : ''}>Em aberto</option>
            <option value="Em analise" ${ocorrenciaGet.getStatus().equals("Em analise") ? 'selected' : ''}>Em analise</option>
            <option value="Em andamento" ${ocorrenciaGet.getStatus().equals("Em andamento") ? 'selected' : ''}>Em andamento</option>
            <option value="Resolvido" ${ocorrenciaGet.getStatus().equals("Resolvido") ? 'selected' : ''}>Resolvido</option>
        </select>
    </div>
    <div class="ocorrencia">
        <label for="nome">Detalhes da Ocorrência</label>
        <textarea placeholder="Descrição da ocorrência" name="descricao"><%=ocorrenciaGet.getTexto()%></textarea>
    </div>
    <div class="resolv">
            <button type="submit" name="idTop" value="<%=ocorrenciaGet.getId_ocorrencia()%>" class="resolvido">Salvar</button>
    </div>
</form>

               <div class="volt">
                   <a href="OcorrenciaAdm"><button class="voltar">Voltar</button></a>
               </div>
           </div>
           <script src="scripts/ocorrenciadetalhada.js"></script>
       </body>
       </html>

