<%@page import="br.com.residup.models.Morador"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tela principal</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <link href="../css/inicial.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="imagens/LogoHeader.png" type="image/x-icon">

    </head>
    <body>
        <header class="menu">
            <div>
                <a class="logo" href="#"><img src="../imagens/img/LogoHeader.png" alt=""></a>
            </div>


            <nav class="menu-cloud">

            </nav>

        </header>
        <!-- inicio da imagem após o menu -->
        <section class="imagem">
            <div class="lg">

            </div>

            <form action="${pageContext.request.contextPath}/index" method="post">
                <section class="makelogin">
                    <h2 class="textLogin">Login</h2>
                </section>
                <section class="inputs-container">
                    <input type="number" name="cpfLogin" placeholder="123.456.789-10" min="11" maxlength="11" required> <!-- Corrigido o tipo do input para "text" -->
                    <div class="password-container">
                        <input type="password" name="senhaLogin" id="field-password" class="field-password" placeholder="******" required >
                        <i class="fa-solid fa-eye" id="eye" onclick="showPassword()"></i>
                        <i class="fa-solid fa-eye-slash" id="eye-slash" onclick="showPassword()"></i>
                    </div>
                    <spam id ="erro">${erro}</spam>
                </section>

                <section class="password-infos">
                    <div>
                        <input type="checkbox">
                        <span>Lembrar senha</span>
                    </div>

                    <a href="#">Esqueceu sua senha?</a>
                </section>

                <button type="submit id="btn-login">Entrar</button>
            </form>


        </section>
        <!-- Final da imagem após menu -->

        <section id="details">
            <h2>Conheça as funcionalidades do nosso sistema</h2>

            <div class="detail" id="detail-1">
                <img src="../imagens/img/cell.png" alt="Imagem de celular" />
                <div class="detail-description">
                    <h3>Faça sua reserva <br> da tela do seu celular.</h3>
                    <p>Sabe aquela  parte chata quando a gente<br> quer reservar aquele espaço pra festa?<br>
                        com o RESIDUP você consegue<br> pular está etapa e fazer tudo por aqui! </p>
                </div>
            </div>
            <div class="detail" id="detail-2">
                <div class="detail-description-1">
                    <h3>Libere sua visita da tela do seu celular.</h3>
                    <p>Chega de burocracia na hora de                      familiar para sua casa,
                        com o RESIDUP você <br>faz a liberação diretamente
                        da tela <br>do seu celular.</p>
                </div>
                <img src="../imagens/img/visit.png" alt="imagem sobre visita" />
            </div>
            <div class="detail" id="detail-3">
                <img src="../imagens/img/chamaddo.png" alt="Imagem sobre chamado" />
                <div class="detail-description">
                    <h3>Quebrou? <br>Chame o síndico em instantes! </h3>
                    <p>sabe quando da aquele problema no seu ap?<br>
                        chega de ligações.<br>
                        Agora você resolve em um clique!</p>
                </div>
            </div>
        </section>


        <!-- Início do Rodapé -->
        <div class="rodape">

            <div class="fileira1">
                <h2>EMPRESA</h2>
                <ul>
                    <li><a href="#">Sobre Nós</a></li>
                    <li><a href="#">Trabalhe Conosco</a></li>
                    <li><a href="#">Dúvidas Frequentes</a></li>
                </ul>
            </div>

            <div class="fileira2">
                <h2>CONTATO</h2>
                <ul>
                    <li><a href="#">SAC Digital</a></li>
                    <li><a href="#">0800-777-777</a></li>
                    <li><a href="#">4002-8922</a></li>
                </ul>
            </div>

            <div class="fileira3">
                <h2>DESENVOLVEDORES</h2>
                <ul>
                    <li><a href="#">Daniel Rodrigues</a></li>
                    <li><a href="#">Anderson Rodrigues</a></li>
                    <li><a href="#">Cleiton</a></li>
                    <li><a href="#">Ana</a></li>
                    <li><a href="#">Barbara</a></li>
                    <li><a href="#">Thiago</a></li>

                </ul>
            </div>

            <div class="fileira4">

                <ul>
                    <li><a href="#"><img src="../imagens/mini.png" alt=""/></a></li> 

                </ul>
            </div>
        </div>


        <!-- Modal -->

        <div id="modal" class="modal">
            <div >
            <div  id="ModalSenha" >
                <form action="UpdatePassword" onsubmit="return validateForm()" method="post">
                    <section class="makelogin">
                        <h2 class="textLogin">Nova Senha:</h2>
                    </section>
                    <section class="inputs-container">
                        <input type="password" name="senha" id="newPassword" class="field-password" placeholder="******">
                        <div class="password-container">
                        <input type="password" name="senha" id="field-confirm-password" class="field-password" placeholder="******">
                            <i class="fa-solid fa-eye" id="eye" onclick="showPassword()"></i>
                            <i class="fa-solid fa-eye-slash" id="eye-slash" onclick="showPassword()"></i>
                        </div>
                    </section>
                    <section class="password-infos">
                        <div>
                            <input type="checkbox">
                            <span>Lembrar senha</span>
                        </div>
                    </section>
                    <button type="submit" id="btn-login">Salvar</button>
                </form>
                </div>
            </div>
        </div>




 <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
         <link href="https://cdn.jsdelivr.net/npm/sweetalert2@11.7.3/dist/sweetalert2.min.css "rel="stylesheet">
         <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>

<script>
    function validateForm() {
        var password = document.getElementById("newPassword").value;
        var confirmPassword = document.getElementById("field-confirm-password").value;

        if (password !== confirmPassword) {
            alert("As senhas digitadas não são iguais. Por favor, verifique novamente.");
            return false; // Impede o envio do formulário
        }

        return true; // Permite o envio do formulário
    }
</script>


         <c:if test="${not empty mensagem}">
            <%-- Exibe o alerta somente se a mensagem não for nula --%>
            <script>
                ${mensagem};
                var modal = document.getElementById('modal');
                modal.style.display = 'block';
            </script>
         </c:if>

        <c:if test="${not empty primeiroAcesso}">
            <script>
                // Função para abrir o modal
                function openModal() {
                    var modal = document.getElementById('modal');
                    modal.style.display = 'block';
                }
            </script>
        </c:if>



        <!-- Fim do Rodapé -->
                 <h1 id ="erro">${mensagemAlert}</h1>

    </body>
</html>

