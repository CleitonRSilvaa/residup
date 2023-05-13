<%@page import="br.com.residup.models.Visitante"%>
<%@page import="br.com.residup.models.Reserva"%>

<%--<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>--%>
<%@page import="java.util.ArrayList"%>
<%
    @SuppressWarnings(
            
    
    "unchecked")
    ArrayList<Visitante> lista = (ArrayList<Visitante>) request.getAttribute("visitantes");
    ArrayList<Reserva> reservas = (ArrayList<Reserva>) request.getAttribute("revervas");
    ArrayList<String> horarios = new ArrayList<String>();

    horarios.add("07:00 - 12:00");
    horarios.add("07:00 - 22:00");
    horarios.add("13:00 - 18:00");
    horarios.add("13:00 - 22:00");

%>

<!DOCTYPE html>
<html lang="PT-BR">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Visitantes</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/style.css">
    </head>

    <body>
        <form action="insertReserva" method="post" >
            <span class="titulo-pagina">Reserva Area</span>
            <div class="container">
                <div class="row">
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="dpChurrasqueira">CHURRASQUEIRA</label>
                            <select class="form-control" id="areaSelect">
                                <option></option>
                                <%for (int i = 1; i < 6; i++) {%>
                                    <option>Área <%=i%></option>
                                <%}%>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="form-group">
                            <label for="dataInput">DATA DA RESERVA</label>
                            <input type="date" class="form-control" id="dataInput">
                        </div>
                    </div>

                    <div class="col-md-4">
                        <div class="form-group">

                            <label for="horarioSelect">HORÒRIO</label>
                            <select class="form-control" id="horarioSelect">
                                <option></option>
                                <%
                                    for (int i = 0; i < horarios.size(); i++) {
                                %>
                                <option><%=horarios.get(i)%></option>

                                <%
                                    }
                                %>
                            </select>
                        </div>
                    </div>
                    <div class="col-md-12" >
                        <br><div class="float-right"><button type="submit" class="btn btn-primary">Fazer Reserva</button></div><br>
                    </div>
                </div>
            </div>
        </form>

        <form>
            <span class="titulo-pagina">Minhas Reservas</span>
            <div class="container-solicitacao">
                <div class="container00">
                    <table class="table table-striped">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">First</th>
                                <th scope="col">Last</th>
                                <th scope="col">Handle</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <th scope="row">1</th>
                                <td>Mark</td>
                                <td>Otto</td>
                                <td>@mdo</td>
                            </tr>
                            <tr>
                                <th scope="row">2</th>
                                <td>Jacob</td>
                                <td>Thornton</td>
                                <td>@fat</td>
                            </tr>
                            <tr>
                                <th scope="row">3</th>
                                <td>Larry</td>
                                <td>the Bird</td>
                                <td>@twitter</td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </form>


        <!-- JavaScript Link -->

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <!--        <script src="scripts/scripts.js"></script>-->
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">

    </body>
</html>