<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="br.com.residup.daos.OcorrenciaDao" %>


<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="Telas/moradorocor.css">
  <title>Ocorrências Morador</title>
</head>
<body>
  <div class="header" id="header">
    <button onclick="toggleSidebar()" class="btn_icon_header">
      <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z" />
      </svg>
    </button>
    <div class="logo_header">
      <img src="Telas/img/LogoHeader.png" alt="Logo ResidUP" class="img_logo_header">
    </div>
    <div class="navigation_header" id="navigation_header">
      <button onclick="toggleSidebar()" class="btn_icon_header">
        <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
          <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z" />
        </svg>
      </button>
      <a class="">PAGINA INICIAL</a>
      <a class="">RESERVAR ÁREA</a>
      <a href="cadastroVisitante.jsp">CONTROLE DE VISITANTES</a>
      <a href="#" class="active">REGISTRO DE OCORRÊNCIAS</a>
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
  <form name="ocorrencias" action="occurrenceInsert" method="post">
    <section class="container">
      <div class="NovaOcor">
        <div class="text">Registrar nova ocorrência</div>
        <div class="buttons">
          <input type="text" placeholder="Título da ocorrência" name="titulo">
          <button type="submit">
            <h4>Salvar ocorrência</h4>
          </button>
        </div>
        <div class="character">
          <textarea name="texto" id="txtcharacter" cols="30" rows="10"></textarea>
        </div>
      </div>
    </section>
  </form>

  <section class="container">
    <div class="wrapper">
      <div class="text">Minhas ocorrências</div>
      <div class="group">
        <input type="text" placeholder="Titulo da Ocorrência" id="nome" name="Title Ocorrência" required>
        <input class="x" type="text" placeholder="Descrição da Ocorrência" id="nome" name="Desc" required>
        <input class="edta" type="text" placeholder="Editar Ocorrência" id="nome" name="Editar" required>
        <input class="oc" type="text" placeholder="Status" id="nome" name="Status" required>
      </div>
      <hr>
      <div class="group-2">
        <input type="text" placeholder="Titulo da Ocorrência" id="nome" name="Title Ocorrência" required>
        <input class="x" type="text" placeholder="Descrição da Ocorrência" id="nome" name="Desc" required>
        <input class="edta" type="text" placeholder="Editar Ocorrência" id="nome" name="Editar" required>
        <input class="oc" type="text" placeholder="Status" id="nome" name="Status" required>
      </div>
      <hr>
      <div class="group-3">
        <input type="text" placeholder="Titulo da Ocorrência" id="nome" name="Title Ocorrência" required>
        <input class="x" type="text" placeholder="Descrição da Ocorrência" id="nome" name="Desc" required>
        <input class="edta" type="text" placeholder="Editar Ocorrência" id="nome" name="Editar" required>
        <input class="oc" type="text" placeholder="Status" id="nome" name="Status" required>
      </div>
    </div>
  </section>
  <script src="Telas/script.js"></script>
  <script src="https://replit.com/public/js/replit-badge-v2.js" theme="dark" position="bottom-right"></script>
</body>
</html>