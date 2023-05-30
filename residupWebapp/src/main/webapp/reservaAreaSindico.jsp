<%@page import="br.com.residup.models.Convidado"%>
<%@page import="br.com.residup.models.Reserva"%>
<%--<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>--%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%
    ArrayList<Reserva> allReservas = (ArrayList<Reserva>) request.getAttribute("allReservas");
    ArrayList<Convidado> convidados = (ArrayList<Convidado>) request.getAttribute("listaConvidados");
%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link href="../css/rsv.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

        <title>Reservas de Área</title>
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
                <a href="/reservasAdmin" class="active">ÁREA DE RESERVAS</a>
                <a href="/visitantesAdm">CONTROLE DE VISITANTES</a>
                <a href="/OcorrenciaAdm">OCORRÊNCIAS DE MORADORES</a>
                <a href="/cadastro_morador">REGISTRAR NOVO MORADOR</a>
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

        <div class="form">
            <form class="filtro" action="reservasAdmin" method="GET">
                <div class="text">
                    <h3>Reservas dos Moradores</h3>
                </div>
                <a >
                    <button type="submit"class="filtrar">Filtrar</button>
                </a>
                <input type="date" class="data" id="form-status" name="dataFiltro" />
            </form>
            <div class="input-group">
                <%for (Reserva reserva : allReservas) {%>
                <label><%=reserva.getMorador().getNome() + " " + reserva.getMorador().getSobrenome()%></label>
                <label class="apt"><%=reserva.getMorador().getNumeroApartamento()%></label>
                <label class="apt"><%=reserva.getMorador().getBloco()%></label>
                <label class="area"><%=reserva.getNomeArea()%></label>
                <label class="data"><%=reserva.getDateReserva()%></label>
                <label  class="hora"><%=reserva.getHoraReserva()%></label>
                <form class="convid" action="/convidosReservaAdm" method="post">
                    <input  type="hidden" name="idReservaAdm" id="idReservaAdm" value="<%=reserva.getIdReserva()%>">
                    <button  type="submit" class="abrir" id="3openModalBtn"> Convidados</button>

                </form>
                <%}%>


                <div id="modal" class="modal">
                    <div class="modal-content">
                        <span class="close">&times;</span>
                        <div class="convidados">
                            <h2>Lista de Convidados</h2>
                            <%
                                if (convidados != null) {
                                    if (!convidados.isEmpty()) {
                                        for (Convidado objConvidado : convidados) {
                            %>
                            <label class="nome"><%= objConvidado.getNome()%></label>
                            <label class="doc"><%= objConvidado.getIndentidade()%></label>
                            <hr>
                            <%
                                        }
                                    }
                                }
                            %>
                        </div>
                    </div>
                </div>   

            </div>





            <c:if test="${not empty mgsmodalAdm}">
                <script>
                   var modal = document.getElementById('modal');
                      modal.style.display = 'block';
                      function closeModal() {
                          modal.style.display = 'none';
                      }
                      window.addEventListener('click', function (event) {
                          if (event.target == modal) {
                              closeModal();
                          }
                      });
                      var closeModalButton = document.getElementsByClassName('close')[0];
                      closeModalButton.addEventListener('click', function () {
                          closeModal();
                      });
                </script>
            </c:if>

            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
            <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">
            </body>
            </html>