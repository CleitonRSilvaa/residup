<%@page import="br.com.residup.models.Reserva"%>
<%@ page contentType="text/html; charset=UTF-8" %>
<%--<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>--%>
<%@page import="java.util.ArrayList"%>
<%
    ArrayList<Reserva> reservas = (ArrayList<Reserva>) request.getAttribute("revervas");
    ArrayList<Reserva> areas = (ArrayList<Reserva>) request.getAttribute("areas");
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
        <title>HEADER ResidUP</title>
        <link rel="stylesheet" href="../css/rsvmorador.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">

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
                <a href="#">PAGINA INICIAL</a>
                <a href="#" class="active">RESERVAR ÁREA</a>
                <a href="#">CONTROLE DE VISITANTES</a>
                <a href="#">REGISTRO DE OCORRENCIAS</a>
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
                    <button type="button" class="convidados" data-bs-toggle="modal" data-bs-target="#staticBackdrop">
                        Listar Convidados
                    </button>
                    <form  class="editar" action="/deleteReserva" method="delete">
                        <input class="editar" type="hidden" name="idReserva" id="idReserva" value="<%=reserva.getIdReserva()%>">
                        <button class="editar" type='submit'>Excluir</button>
                    </form>
                </div>
                <hr>
                <%}%>


                <div class="all-products">

                    <div class="product">
                        <img src="../imagens/img/Salao.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Salão de festas</h5>
                            <p class="card-text">Amplo espaço para realização de festas e eventos, contendo tudo que é necessário para seu evento perfeito. </p>
                            <a href="#" class="btn btn-primary">Saber Mais</a>
                        </div>
                    </div>
                    <div class="product">
                        <img src="../imagens/img/reuniao.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Sala de reuniao</h5>
                            <p class="card-text">Precisa de um espaço para trabalhar ou fazer uma reunião? Nesse espaço voce encontra conforto e muito mais.</p>
                            <a href="#" class="btn btn-primary">Saber Mais</a>
                        </div>
                    </div>
                    <div class="product">
                        <img src="../imagens/img/churrasqueira.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Churrasqueira</h5>
                            <p class="card-text">Está planejando fazer aquele churrasco com a familia ou com seus amigos? Clique em saiba mais e conheça o espaço perfeito!</p>
                            <a href="#" class="btn btn-primary">Saber Mais</a>
                        </div>
                    </div>
                    <div class="product">
                        <img src="../imagens/img/quadra.jpg" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">Quadra de tenis</h5>
                            <p class="card-text">Pensando em praticar algum esporte? Clique em saiba mais e conheça todos os beneficios que a quadra te tenis te oeferece.</p>
                            <a href="#" class="btn btn-primary">Saber Mais</a>
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
                        <div class="modal-body">
                            <input class="nomecomp" placeholder="Digite o nome"  type="text" name="nome" required>
                            <input class="doc" placeholder="Digite o Documento" type="text" required>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Voltar</button>
                            <button type="button" class="btn btn-primary">Salvar</button>
                        </div>
                    </div>
                </div>
            </div>
        </section>


        <script src="../scripts/scriptReserva.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
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