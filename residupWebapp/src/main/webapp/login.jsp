<%@page import="br.com.residup.models.Morador"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>RESIDUP</title>

    <link href="Telas/login.css" rel="stylesheet" type="text/css"/>
    <script src="index.js" defer></script>

    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=BhuTuka+Expanded+One&family=Poppins:wght@100;400&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/7903bfed90.js" crossorigin="anonymous" defer></script>
</head>
<body>
    <header>
        <h1><a href="#">RESIDUP</a></h1>
    </header>
    <main>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <section class="makelogin">
                <h2>Login</h2>
            </section>
            <section class="inputs-container">
                <input type="text" name="cpf" placeholder="123.456.789-10">
                <div class="password-container">
                    <input type="password" id="field-password" class="field-password" name="senha" placeholder="******">
                    <i class="fa-solid fa-eye" id="eye" onclick="showPassword()"></i>
                    <i class="fa-solid fa-eye-slash" id="eye-slash" onclick="showPassword()"></i>
                </div>
            </section>

            <section class="password-infos">
                <div>
                    <input type="checkbox">
                    <Span>Lembrar senha</Span>
                </div>

                <a href="#">Esqueceu sua senha?</a>
            </section>

            <button type="submit" id="btn-login">Entrar</button>
        </form>
    </main>
    <footer>
        <div class="end">

        </div>
    </footer>
</body>
</html>
