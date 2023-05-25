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
  <link rel="stylesheet" href="../css/controlevisitantes.css">
  <link rel="stylesheet" href="css/visitanteStyle.css">

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
            <a href="RsvSind.html" >PAGINA INICIAL</a>
            <a href="RsvSind.html" >RESERVAR ÁREA</a>
            <a href="#" class="active">CONTROLE DE VISITANTES</a>
            <a href="HeaderOcorrencias.html">REGISTRO DE OCORRÊNCIAS</a>
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
    <button type="button" onclick="cadastrarVisitante()" class="cadastrarvisitante">Novo Visitante</button>
  <button type="button" class="cadastrarvisitante">Relátorio</button>

  <form class="filtro">
  <button class="busc">Buscar</button>
  <input type="date" id="txtBusca" name="" id="">
  <input type="text" id="txtBsca" placeholder="Buscar Visitante..."/>
</form>


  <!-- Modal de Cadastro -->
  <div id="modal" class="modal">
  </div>
  <div class="form">
    <div class="title">
        <h2>Seus Visitantes</h2>
    </div>
    <div class="group">
     <%
       for (int i = 0; i < lista.size(); i++) {
     %>
            <input placeholder="ID" name="ID" value="<%= lista.get(i).getId() %>" disabled></input>
            <input class="nm"  placeholder="Nome" name="nome" value="<%=lista.get(i).getNome()%> <%=lista.get(i).getSobrenome()%>"disabled></input>
            <input class="x" placeholder="Documento" name="doc" value="<%= lista.get(i).getDocumento() %>" disabled></input>
            <input class="oc" placeholder="Fone" name="fone" value="<%= lista.get(i).getFone() %>" disabled></input>
            <button type="submit" onclick= "editarVisitante()" class="editvisitante">Editar</button>
            <a  href="javascript: confirmar(<%=lista.get(i).getId()%>)"><button class="editarcad"  onclick="removerVisitante(${lista.get(i).getId()})">Excluir</button></a>

     <%
     }
     %>

    </div>
</div>



                        <form name="frmContato" action="insert" method="post">
                            <div class="form-group">
                                <label for="nome">Nome:</label>
                                <input type="text" class="form-control" id="nome" name="nome" required>
                            </div>
                            <div class="form-group">
                                <label for="sobrenome">Sobrenome:</label>
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



<script>
  async function cadastrarVisitante() {
    const { value: nome } = await Swal.fire({
      title: 'Novo Visitante',
      html:
        '<input id="swal-nome" class="swal2-input" placeholder="Nome" required>' +
        '<input id="swal-sobrenome" class="swal2-input" placeholder="Sobrenome" required>' +
        '<input id="swal-documento" class="swal2-input" placeholder="Documento" required>' +
        '<input id="swal-telefone" class="swal2-input" placeholder="Telefone">',
      focusConfirm: false,
      preConfirm: () => {
        return {
          nome: document.getElementById('swal-nome').value,
          sobrenome: document.getElementById('swal-sobrenome').value,
          documento: document.getElementById('swal-documento').value,
          telefone: document.getElementById('swal-telefone').value
        };
      }
    });

    if (nome) {
      document.getElementById('nome').value = nome.nome;
      document.getElementById('sobrenome').value = nome.sobrenome;
      document.getElementById('documento').value = nome.documento;
      document.getElementById('fone').value = nome.telefone;

      // Submeta o formulário se necessário
       document.forms["frmContato"].submit();
    }
  }
</script>

<script>
  function editarVisitante() {
    const id = document.querySelector('input[name="ID"]').value;
    const nome = document.querySelector('input[name="nome"]').value;
    const documento = document.querySelector('input[name="doc"]').value;
    const fone = document.querySelector('input[name="fone"]').value;

    Swal.fire({
      title: 'Editar Visitante',
      html:
        '<input id="swal-nome" class="swal2-input" placeholder="Nome" value="' + nome + '" required>' +
        '<input id="swal-documento" class="swal2-input" placeholder="Documento" value="' + documento + '" required>' +
        '<input id="swal-fone" class="swal2-input" placeholder="Telefone" value="' + fone + '">',
      focusConfirm: false,
      preConfirm: () => {
        return {
          nome: document.getElementById('swal-nome').value,
          documento: document.getElementById('swal-documento').value,
          fone: document.getElementById('swal-fone').value
        };
      }
    }).then((result) => {
      if (result.isConfirmed) {
        const editedNome = result.value.nome;
        const editedDocumento = result.value.documento;
        const editedFone = result.value.fone;

        // Atualize os campos do formulário original com os novos valores editados
        document.querySelector('input[name="nome"]').value = editedNome;
        document.querySelector('input[name="doc"]').value = editedDocumento;
        document.querySelector('input[name="fone"]').value = editedFone;

        // Crie um novo formulário e envie as informações para onde desejar
        const form = document.createElement('form');
        form.method = 'post';
        form.action = 'update';

        const hiddenId = document.createElement('input');
        hiddenId.type = 'hidden';
        hiddenId.name = 'ID';
        hiddenId.value = id;

        const hiddenNome = document.createElement('input');
        hiddenNome.type = 'hidden';
        hiddenNome.name = 'nome';
        hiddenNome.value = editedNome;

        const hiddenDocumento = document.createElement('input');
        hiddenDocumento.type = 'hidden';
        hiddenDocumento.name = 'doc';
        hiddenDocumento.value = editedDocumento;

        const hiddenFone = document.createElement('input');
        hiddenFone.type = 'hidden';
        hiddenFone.name = 'fone';
        hiddenFone.value = editedFone;

        form.appendChild(hiddenId);
        form.appendChild(hiddenNome);
        form.appendChild(hiddenDocumento);
        form.appendChild(hiddenFone);

        document.body.appendChild(form);
        form.submit();
      }
    });
  }
</script>

<button class="editvisitante" onclick="editarVisitante()">Editar</button>




        <script src="scripts/controle.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="scripts/scripts.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">
        <c:if test="${requestScope.validator eq true}">
        <script>
             Swal.fire(
                        'Sucesso!',
                        'Visitante cadastrado.',
                        'success'
                        );
            </script>

        </c:if>
</body>

</html>