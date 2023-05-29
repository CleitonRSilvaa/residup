<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page import="br.com.residup.models.Morador"%>
<%
    Morador morador = (Morador) request.getAttribute("moradorEditar");

%>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="css/cadastro.css">

  <title>cadastro</title>
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
        <a href="RsvSind.html" >ÁREA DE RESERVAS</a>
        <a href="visitasmorador.html">CONTROLE DE VISITANTES</a>
        <a href="HeaderOcorrencias.html">OCORRÊNCIAS DE MORADORES</a>
        <a href="HeaderRegistro.html"class="active">REGISTRAR NOVO MORADOR</a>
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
  <section>
  <form action="/" method="get" >
    <a href="#"><button class="buttonCadastroMorador">Cadastro de  morador</button></a>
    <a href="cpf.html"><button class="edtcad">Editar Cadastro</button></a>
    <a href="excluirmorador.html"><button class="exccad">Excluir Morador</button></a>
    <div class="box">
    </form>
    <div class="form-box">
      <h2>Cadastrar de Morador</h2>

      <form action="/updateMorador" method="post" enctype="multipart/form-data">
           <div class="perfil">
              <label class="nome" for="nome" > Nome</label>
              <input type="text" id="nomeMorador" placeholder="Digite o nome do morador" name="nomeMorador" value="<%= morador.getNome()%>" required>
              <label class="sobrenome" for="nome"> Sobrenome</label>
              <input type="text" id="sobrenomeMorador" placeholder="Digite o sobrenome" name="sobrenomeMorador" value="<%= morador.getSobrenome()%>" required>
              <label class="picture" for="picture__input" tabIndex="0">
                <span class="picture__image"></span>
              </label>
              <label></label>

              <input type="file" name="file" id="picture__input">
            </div>
            <div class="input-group">
              <label for="cpf">CPF</label>
              <input type="cpf" id="cpfMorador" placeholder="Digite o CPF do Morador" maxlength="11" name="cpfMorador" value="<%= morador.getCpf()%>" required class="input-cpf">
            </div>
            <div class="input-group">
              <label for="rg">RG</label>
              <input type="rg" id="rgMorador" placeholder="Digite o  RG" maxlength="9" name="rgMorador" value="<%= morador.getRg()%>" required class="input-rg">
            </div>

            <div class="input-group">
              <label for="numero_apartamento">Apartamento</label>
              <input type="text" id="numero_apartamentoMorador" placeholder="Digite o numero do apartamento" maxlength="4" name="numero_apartamentoMorador" value="<%= morador.getNumeroApartamento()%>" required>
            </div>

            <div class="input-group">
              <label for="Bloco">Bloco</label>
              <input type="text" id="blocoMorador" placeholder="Digite o bloco" maxlength="2" name="blocoMorador" value="<%= morador.getBloco()%>" required>
            </div>

            <div class="input-group w50">
              <label for="senha">Senha</label>
              <input type="password" id="senhaMorador" placeholder="Digite a senha" name="senhaMorador" required>
            </div>

            <div class="input-group w50">
              <label for="Confirmarsenha">Confirmar Senha</label>
              <input type="password" id="Confirmarsenha" placeholder="Confirme a senha" name="Confirmar" required>
            </div>

            <div class="input-group">
              <button class="cadastrar" type="submit">Editar Morador</button>
            </div>

          </form>
        </div>
      </div>

  </section>
  <script src="scripts/cadastro.js"></script>

          <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
          <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">

      <c:if test="${not empty mensagem}">
          <%-- Exibe o alerta somente se a mensagem não for nula --%>
          <script>

              <%= request.getAttribute("mensagem")%>

          </script>
      </c:if>

    <script>
     const inputFile = document.querySelector("#picture__input");
     const pictureImage = document.querySelector(".picture__image");
     const pictureImageTxt = "Selecione uma foto";
     pictureImage.innerHTML = pictureImageTxt;

     inputFile.addEventListener("change", function(e) {
       const inputTarget = e.target;
       const file = inputTarget.files[0];

       if (file) {
         const reader = new FileReader();

         reader.addEventListener("load", function(e) {
           const readerTarget = e.target;

           const img = document.createElement("img");
           img.src = readerTarget.result;
           img.classList.add("picture__img");

           pictureImage.innerHTML = "";
           pictureImage.appendChild(img);
         });

         reader.readAsDataURL(file);
       } else {
         pictureImage.innerHTML = pictureImageTxt;
       }


     });

     function validarFormulario() {
       // Obtendo referências para os elementos do formulário
       const nomeInput = document.getElementById('nomeMorador');
       const sobrenomeInput = document.getElementById('sobrenomeMorador');
       const cpfInput = document.getElementById('cpfMorador');
       const rgInput = document.getElementById('rgMorador');
       const senhaInput = document.getElementById('senhaMorador');
       const confirmarSenhaInput = document.getElementById('Confirmarsenha');

       // Função de validação para o campo nome e sobrenome
       function validarNomeSobrenome(event) {
         const input = event.target;
         const regex = /^[a-zA-Z ]*$/; // Permite apenas letras e espaços

         if (!regex.test(input.value)) {
           input.setCustomValidity('Por favor, insira apenas letras e espaços');
         } else {
           input.setCustomValidity('');
         }
       }

       // Função de validação para os campos CPF e RG
       function validarCPF_RG(event) {
         const input = event.target;
         const regex = /^[0-9]*$/; // Permite apenas números

         if (!regex.test(input.value)) {
           input.setCustomValidity('Por favor, insira apenas números');
         } else {
           input.setCustomValidity('');
         }
       }

       // Função de validação para os campos de senha
       function validarSenha(event) {
         const senha = senhaInput.value;
         const confirmarSenha = confirmarSenhaInput.value;

         if (senha !== confirmarSenha) {
           confirmarSenhaInput.setCustomValidity('As senhas não coincidem');
         } else {
           confirmarSenhaInput.setCustomValidity('');
         }
       }

       // Adicionando os ouvintes de eventos para os campos
       nomeInput.addEventListener('input', validarNomeSobrenome);
       sobrenomeInput.addEventListener('input', validarNomeSobrenome);
       cpfInput.addEventListener('input', validarCPF_RG);
       rgInput.addEventListener('input', validarCPF_RG);
       senhaInput.addEventListener('input', validarSenha);
       confirmarSenhaInput.addEventListener('input', validarSenha);
     }

     // Chamando a função para iniciar a validação do formulário
     validarFormulario();

     </script>

</body>

</html>