<%@page import="br.com.residup.models.Visitante"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
    @SuppressWarnings(
    
    "unchecked")
    ArrayList<Visitante> lista = (ArrayList<Visitante>) request.getAttribute("visitantes");
%>
<%-- Verifica se o cadastro foi realizado com sucesso --%>


<!DOCTYPE html>
<html lang="PT-BR">

    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Visitantes</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="css/visitanteStyle.css">
    </head>

    <body>
        <h1>Visitantes</h1>
        <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#novoVisitantesModal">
            Novo Visitante
        </button>
        <a href="report" class="btn btn-info">Relat�rio</a>
        <table id="tabela" class="table table-striped">
            <thead>
                <tr>
                    <th scope="col">Id</th>
                    <th scope="col">Nome</th>
                    <th scope="col">Documento</th>
                    <th scope="col" >Fone</th>
                    <th scope="col">Opçõeses</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (int i = 0; i < lista.size(); i++) {
                %>
                <tr scope="row">
                    <td id= <%=lista.get(i).getId()%> ><%=lista.get(i).getId()%></td>
                    <td><%=lista.get(i).getNome()%> <%=lista.get(i).getSobrenome()%></td>
                    <td><%=lista.get(i).getDocumento()%></td>
                    <td><%=lista.get(i).getFone()%></td>

                    <td>
                        <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#editarVisitantesModal"
                                data-id="<%=lista.get(i).getId()%>"
                                data-nome="<%=lista.get(i).getNome()%>"
                                data-sobrenome="<%= lista.get(i).getSobrenome()%>"
                                data-documento="<%=lista.get(i).getDocumento()%>"
                                data-fone="<%=lista.get(i).getFone()%>"
                                >Editar</button>
                        <a href="javascript: confirmar(<%=lista.get(i).getId()%>)"
                           class="btn btn-danger">Excluir</a>
                    </td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>

        <!-- Modal de Cria��o -->
        <div class="modal fade" id="novoVisitantesModal" tabindex="-1" role="dialog" aria-labelledby="novoContatoModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="novoContatoModalLabel">Novo Visitante</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Fechar">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form name="frmContato" action="insert" method="post">
                            <div class="form-group">
                                <label for="nome">Nome:</label>
                                <input type="text" class="form-control" id="nome" name="nome" required>
                            </div>
                            <div class="form-group">
                                <label for="sobrenome">Sobrenome</label>
                                <input type="text" class="form-control" id="sobrenome" name="sobrenome" required>
                            </div>
                            <div class="form-group">
                                <label for="documento">Documento:</label>
                                <input type="text" class="form-control" id="documento" name="documento" required>
                            </div>
                            <div class="form-group">
                                <label for="telefone">Telefone:</label>
                                <input type="text" class="form-control" id="fone" name="fone">
                            </div>
                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal" onclick="LimpaModalCriacao()">Cancelar</button>
                        <button type="button" class="btn btn-primary" onclick="validarNovo()">Salvar</button>
                    </div>
                </div>
            </div>
        </div>

        <!-- Modal de edição -->
        <div class="modal fade" id="editarVisitantesModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title" id="myModalLabel">Editar Visitante</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form  name="frmContatoEdit" action="update" method="post">
                            <div class="form-group">
                                <label for="id" >ID: <input type="numero"id="idEdicao" name="id"  readonly
                                                            value=""></label>
                            </div>
                            <div class="form-group">
                                <label for="nome">Nome:</label>
                                <input type="text" class="form-control" id="nomeEdicao" name="nome">
                            </div>
                            <div class="form-group">
                                <label for="sobrenome">Sobrenome</label>
                                <input type="text" class="form-control" id="sobrenomeEdicao" name="sobrenome" required>
                            </div>
                            <div class="form-group">
                                <label for="Documento">Documento:</label>
                                <input type="text" class="form-control" id="documentoEdicao" name="documento">
                            </div>
                            <div class="form-group">
                                <label for="telefone">Telefone:</label>
                                <input type="text" class="form-control" id="foneEdicao" name="fone">
                            </div>

                        </form>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                        <button type="button" class="btn btn-primary" onclick="validarEditar()" >Salvar</button>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="scripts/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">
        <c:if test="${requestScope.validator eq true}">
        <script>
             Swal.fire(
                        'Deleted!',
                        'Your file has been deleted.',
                        'success'
                        );
            </script>
        </c:if>
    </body>
</html>