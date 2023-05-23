<%@page import="br.com.residup.models.Morador"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Tela principal</title>
        <link href="../css/inicial.css" rel="stylesheet" type="text/css"/>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
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
                        <h2>Login</h2>
                    </section>
                    <section class="inputs-container">
                        <input type="text" name="cpf" placeholder="123.456.789-10"> <!-- Corrigido o tipo do input para "text" -->
                        <div class="password-container">
                            <input type="password" name="senha" id="field-password" class="field-password" placeholder="******">
                            <i class="fa-solid fa-eye" id="eye" onclick="showPassword()"></i>
                            <i class="fa-solid fa-eye-slash" id="eye-slash" onclick="showPassword()"></i>
                        </div>
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

            <div class="cadastro">
                <hr>
                <span>Ainda não possui uma conta? <a href="#">Cadastre-se</a></span>
            </div>



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
                <a href="#"><img src="imagens/mini.png" alt=""/></a>

            </ul>
        </div>
    </div>
    <!-- Fim do Rodapé -->

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-w76AqPfDkMBDXo30jS1Sgez6pr3x5MlQ1ZAGC+nuZB+EYdgRZgiwxhTBTkF7CXvN" crossorigin="anonymous"></script>
</body>
</html>
