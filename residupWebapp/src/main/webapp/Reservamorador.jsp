<%@page import="java.util.List"%>
<%@page import="br.com.residup.models.Reserva"%>
<%@page import="br.com.residup.models.Convidado"%>
<%@page import="br.com.residup.models.Morador"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    Morador morador = (Morador) request.getAttribute("morador");
    ArrayList<Reserva> reservas = (ArrayList<Reserva>) request.getAttribute("revervas");
    ArrayList<Reserva> areas = (ArrayList<Reserva>) request.getAttribute("areas");
    ArrayList<Convidado> convidados = (ArrayList<Convidado>) request.getAttribute("listaConvidados");
    ArrayList<String> horarios = new ArrayList<String>();

    horarios.add("07:00 - 12:00");
    horarios.add("07:00 - 22:00");
    horarios.add("13:00 - 18:00");
    horarios.add("13:00 - 22:00");

%>


<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Reservas</title>
        <link href="../css/rsvmorador.css" rel="stylesheet"type="text/css" >
        <link href="../css/perfil.css" rel="stylesheet" type="text/css">
        <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">
    </head>
    <body>
        <body class="bodyCorNova">
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
                <a href="/listarResumo">PAGINA INICIAL</a>
                <a href="/reservas" class="active">RESERVAR ÁREA</a>
                <a href="/visitantes">CONTROLE DE VISITANTES</a>
                <a href="/Ocorrencia">REGISTRO DE OCORRENCIAS</a>
            </div>

             <button type="button" onclick="openModalteste()" id="openModal">Meu Perfil</button>


        </div>



        <!--Fim da header-->
        <section class="reserva">
                    <form action="insertReserva" method="post" >
                        <div class="form">
                            <div class="titulo">
                                <h1>Realizar nova reserva</h1>
                            </div>
                            <a href="#"><button type="submit"
                                                class="bto">Salvar Reserva</button>
                            </a>

                            <div class="area">
                                <select class="form-area" id="form-area" name ="areaSelect" required>
                                    <option selected disabled value=""></option>
                                    <%for (Reserva areaReserva : areas) {%>
                                    <option value = <%=areaReserva.getIdArea()%> > <%=areaReserva.getNomeArea()%></option>
                                    <%}%>
                                </select>


                            </div>
                            <div class="horario">
                                <select class="form-horario" id="form-horario" name="horarioSelect" required>
                                    <option selected disabled value=""></option>
                                    <%
                                        for (int i = 0; i < horarios.size(); i++) {
                                    %>
                                    <option><%=horarios.get(i)%></option>

                                    <%
                                        }
                                    %>
                                </select>
                            </div>

                            <div class="datas">
                                <div class="formdata">
                                    <input class="date" type="date"name ="dataInput" required>
                                </div>

                            </div>
                        </div>
                        </div>
                        </div>
                    </form>

                    <div class="mr">
                        <div class="titulo">
                            <h1>Minhas Reservas</h1>
                        </div>
                        <%for (Reserva reserva : reservas) {%>
                        <div class="group">
                            <label><%=reserva.getNomeArea()%></label>
                            <label class="oc" ><%=reserva.getDateReserva()%></label>
                            <label class="dt" ><%=reserva.getHoraReserva()%></label>
                            <form class="listt" style="display: inline-block;" action="/convidosReserva" method="post">
                                <input class="editar" type="hidden" name="idReserva" id="idReserva" value="<%=reserva.getIdReserva()%>">
                                <button type="submit"  class="convidados" >
                                    Listar Convidados
                                </button>
                            </form>
                            <form class="listt" style="display: inline-block;" name="frmDelete"  action="/deleteReserva" method="post">
                                <input class="editar" type="hidden" name="idReservaDelete" id="idReservaDelete" value="<%=reserva.getIdReserva()%>">
                                <button class="editar" type='button' onclick =cancelarReserva()  >Excluir Reserva</button>
                            </form>
                        </div>

                        <%}%>

                        <div class="all-products">

                            <div class="product">
                                <img src="imagens/img/Salao.jpg" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">Salão de festas</h5>
                                    <p class="card-text">Amplo espaço para realização de festas e eventos, contendo tudo que é necessário para seu evento perfeito. </p>
                                    <a href="#" class="botao">Saber Mais</a>
                                </div>
                            </div>
                            <div class="product">
                                <img src="imagens/img/reuniao.jpg" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">Sala de reuniao</h5>
                                    <p class="card-text">Precisa de um espaço para trabalhar ou fazer uma reunião? Nesse espaço voce encontra conforto e muito mais.</p>
                                    <a href="#" class="botao">Saber Mais</a>
                                </div>
                            </div>
                            <div class="product">
                                <img src="imagens/img/churrasqueira.jpg" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">Churrasqueira</h5>
                                    <p class="card-text">Está planejando fazer aquele churrasco com a familia ou com seus amigos? Temos o espaço perfeito para você.</p>
                                    <a href="#" class="botao">Saber Mais</a>
                                </div>
                            </div>
                            <div class="product">
                                <img src="imagens/img/quadra.jpg" class="card-img-top" alt="...">
                                <div class="card-body">
                                    <h5 class="card-title">Quadra de tenis</h5>
                                    <p class="card-text">Pensando em praticar algum esporte? Chame os amigos e aproveite a quadra de tenis.</p>
                                    <a href="#" class="botao">Saber Mais</a>
                                </div>
                            </div>
                        </div>
                       </form>
                    </div>

                    <!-- Modal -->
                    <div class="modal fade" id="staticBackdrop" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1"
                         aria-labelledby="staticBackdropLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h1 class="modal-title fs-5" id="staticBackdropLabel">Digite os dados do convidados</h1>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <form  id="FormsConviados"  name="FormsConviados" action="cadastroConvidado" method="post">

                                    <div class="modal-body">
                                    <input class="nomecomp" placeholder="Digite o nome"  type="text" name="nomeConvidado" required>
                                    <input class="doc" placeholder="Digite o Documento" type="text" name="identidade" required>
                                    <input class="editar" type="hidden" name="idReservaListaConvidado" id="idReservaListaConvidado" value="<%= request.getAttribute("IdReservaConvidado")%>">
                                    <div class="modal-footer">
                                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Voltar</button>
                                        <button type="submit" class="btn btn-primary">Salvar</button>
                                    </div>
                                    <hr>
                                    <%
                                        if (convidados != null) {
                                            if (!convidados.isEmpty()) {
                                                for (Convidado objConvidado : convidados) {
                                    %>
                                    <label class="nomecomp"><%= objConvidado.getNome()%></label>
                                    <label class="doc"><%= objConvidado.getIndentidade()%> </label>
                                        <input class="editar" type="hidden" name="idConviado" id="idConviado" value="<%= objConvidado.getId()%>">
                                                                    <input class="editar" type="hidden" name="idReservaListaConvidadoExclui" id="idReservaListaConvidadoExclui" value="<%= objConvidado.getId_reserva()%>">

                                        <button class="editar" type="button" onclick="excluirConvidados()">Excluir</button>

                                    <hr>
                                    <%
                                                }
                                            }
                                        }
                                    %>

                                </div>

                                </form>

                            </div>
                        </div>
                    </div>
                </section>

        <div id="custom-modalOverlay" class="custom-modal-overlay">
                    <div class="custom-modal">
                      <h2>Seu Perfil</h2>
                        <form action="updatePerfilMorador" method="post" enctype="multipart/form-data">
                          <div class="custom-perfil">
                            <input type="text" id="custom-nome" placeholder="Nome" name="Nome" value="" disabled>

                            <input type="text" id="custom-sobrenome" placeholder="Sobrenome" name="Sobrenome" value="" disabled>
                            <label class="custom-picture" for="custom-picture__input" tabIndex="0">
                              <span class="custom-picture__image"></span>
                            </label>
                            <label></label>

                            <input type="file" name="custom-picture__input" id="custom-picture__input" >
                          </div>
                          <div class="custom-input-group2">
                            <input type="rg" id="custom-rg" placeholder="RG" maxlength="9" name="RG" value="" disabled>
                            <input class="custom-cpff" type="cpf" id="custom-cpf" placeholder="CPF" maxlength="14" name="CPF" value= disabled>
                          </div>

                          <div class="custom-input-group2">
                            <input type="rg" id="custom-apto" placeholder="apto" maxlength="9" name="Apto" value="" disabled>
                            <input class="custom-cpff" type="cpf" id="custom-bloco" placeholder="Bloco" maxlength="14" name="Bloco" value= disabled>
                          </div>
                          <div class="custom-input-group2">
                            <input type="rg" id="custom-rg-senha" placeholder="Digite a nova Senha" maxlength="9" name="Senha" required>
                            <input class="custom-cpff" type="cpf" id="custom-cpf-confirma" placeholder="Confirme a Nova senha" maxlength="14" name="Confs" required>
                          </div>
                          <div class="custom-input-group">
                            <button type="submit" class="custom-cadastrar">Alterar Senha</button>
                          </div>

                        </form>

                    </div>
                  </div>


        <script src="../scripts/scriptReserva.js"></script>
        <script src="../scripts/perfil.js"></script>
        <script src="../scripts/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">


        <c:if test="${not empty mensagem}">
            <%-- Exibe o alerta somente se a mensagem não for nula --%>
            <script>
                <%= request.getAttribute("mensagem")%>
            </script>
        </c:if>

        <c:if test="${not empty mgsmodal}">
            <script>
                let modal = new bootstrap.Modal(document.getElementById("staticBackdrop"));
                modal.show();
            </script>
        </c:if>

    </body>
</html>