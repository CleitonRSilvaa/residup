<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="pt-BR">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <link rel="stylesheet" href="../cadastro.css">

  <title>cadastro</title>
</head>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@300;400;500&display=swap');
    *{
        padding: 0;
        margin: 0;
        box-sizing: border-box;
        font-family: 'Poppins', sans-serif;
    }
    body{
        background-color: white;
        background-position: center;
        background-repeat: no-repeat;
        background-size: cover;
        display: block;
        align-items: center;
        justify-content: center;
        padding: 20px;
        min-height: 100vh;
    }
    .header,
    .navigation_header{
        display: flex;
        flex-direction: row;
        align-items: center;
    }
    .header{
        background-color: white;
        justify-content: space-between;
        padding: 0 10px;
        height: 50PX;
        width: 100%;

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
        background: #49ae96;
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
       background-color: #fff;
    }

    nav li{
       display: inline-block;
    }

    nav li a{
       display: inline-block;
       color: black;
       text-decoration: none;
       padding: 10px;
    }

    nav li a:hover{
        background-color: #545454;
        border-radius: 10px;
    }

    .dropdown-menu{
        background-color: #c4c4c4;
        position: absolute;
        box-shadow: 0 0 2px black;
        display: none;
        border-radius: 10px;
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
            height: 100%;
            width: 35vw;
            padding: 1em;
            animation-duration: 1s;
            margin-left: -5vw;
        }
        .btn_icon_header{
            display: block;
        }
    }
    @keyframes showSidebar {
        from {margin-left: -100vw;}
        to {margin-left: -10vw;}
    }
    section{
        width: 100%;
        height: 1000px;
    }
    .w50{
        width: 100%;
        float: left;

    }
    .box{
        display: flex;
        width: 100%;
    }
    button{
        margin-top: 25px;
        border-radius: 15px;
        width: 20%;
        height: 40px;
        background-color: #c4c4c4;
        margin-left: 10%;
        opacity: 50%;
    }
    .edtcad{
        background-color: #c4c4c4;
        opacity: 100%;
    }
    .exccad{
        background-color:#c4c4c4;
        opacity: 100%;
    }
    .form-box{
        margin-top: 25px;
        background-color: #c4c4c4;
        backdrop-filter: blur(40px);
        padding: 30px 40px;
        display: block;
        width: 100%;
        height: 900px;
        border-radius: 20px 20px 20px 20px;
        align-items: center;
    }
    .form-box h2{
        font-size: 20px;
    }
    .form-box p{
        font-weight: bold;
        color: #3D3D3D;
    }
    .form-box p a{
        color: #49ae96;
        text-decoration: none;
    }
    .form-box form{
        margin: 20px 0;
    }
    form .input-group{
        margin-bottom: 15px;
    }
    form .input-group label{
        color: #3D3D3D;
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }
    form .input-group input{
        width: 100%;
        height: 47px;
        background-color: rgba(255, 255, 255, 0.32);
        border-radius: 20px;
        outline: none;
        border: 2px solid transparent;
        padding: 15px;
        font-size: 15px;
        color: #616161;
        transition: all 0.4s ease;
    }
    form .input-group input:focus{
        border-color: #49ae96;
    }
    form .input-group button{
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
        width: 15%;
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
        align-items: center;
        width: 100%;
        background-color: #c4c4c4;
        border-radius: 15px;

      }
    .perfil .nome{
        margin-right: 75%;
        float: right;
        color: #3D3D3D;
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }
    .perfil .sobrenome{
        margin-right: 72%;
        float: right;
        color: #3D3D3D;
        font-weight: bold;
        display: block;
        margin-bottom: 5px;
    }
    .perfil input{
        width: 80%;
        height: 47px;
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
    .perfil input:focus{
        border-color: #49ae96;
    }
    .input-cpf {
      text-transform: 999.999.999-99;
    }

    .input-rg {
      text-transform: 99.999.999;
    }
</style>
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
    <a href="#"><button>Cadastro de  morador</button></a>
    <a href="cpf.html"><button class="edtcad">Editar Cadastro</button></a>
    <a href="excluirmorador.html"><button class="exccad">Excluir Morador</button></a>
    <div class="box">
    </form>
    <div class="form-box">
      <h2>Cadastrar de Morador</h2>

      <form action="/create_morador" method="post" enctype="multipart/form-data">
           <div class="perfil">
              <label class="nome" for="nome"> Nome</label>
              <input type="text" id="nomeMorador" placeholder="Digite o nome do morador" name="nomeMorador" required>
              <label class="sobrenome" for="nome"> Sobrenome</label>
              <input type="text" id="sobrenomeMorador" placeholder="Digite o sobrenome" name="sobrenomeMorador" required>
              <label class="picture" for="picture__input" tabIndex="0">
                <span class="picture__image"></span>
              </label>
              <label></label>

              <input type="file" name="file" id="picture__input">
            </div>
            <div class="input-group">
              <label for="cpf">CPF</label>
              <input type="cpf" id="cpfMorador" placeholder="Digite o CPF do Morador" maxlength="11" name="cpfMorador" required class="input-cpf">
            </div>
            <div class="input-group">
              <label for="rg">RG</label>
              <input type="rg" id="rgMorador" placeholder="digite o  RG" maxlength="9" name="rgMorador" required class="input-rg">
            </div>

            <div class="input-group">
              <label for="numero_apartamento">Apartamento</label>
              <input type="text" id="numero_apartamentoMorador" placeholder="Digite o numero do apartamento" maxlength="4" name="numero_apartamentoMorador" required>
            </div>

            <div class="input-group">
              <label for="Bloco">Bloco</label>
              <input type="text" id="blocoMorador" placeholder="Digite o bloco" maxlength="2" name="blocoMorador" required>
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
              <button class="cadastrar" type="submit">Cadastrar Morador</button>
            </div>

          </form>
        </div>
      </div>

  </section>
  <script src="cadastro.js"></script>

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