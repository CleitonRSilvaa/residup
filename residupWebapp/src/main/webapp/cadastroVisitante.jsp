<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/styleVisit_Ocorr.css">
    <title>Header Visitantes</title>
</head>
<body>
    <div class="header" id="header">
        <button onclick="toggleSidebar()" class="btn_icon_header">
            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-list" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M2.5 12a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5zm0-4a.5.5 0 0 1 .5-.5h10a.5.5 0 0 1 0 1H3a.5.5 0 0 1-.5-.5z"/>
            </svg>
        </button>
        <div class="logo_header">
            <img src="imagens/LogoHeader.png" alt="Logo ResidUP" class="img_logo_header">
        </div>
        <div class="navigation_header" id="navigation_header">
            <button onclick="toggleSidebar()" class="btn_icon_header">
                <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
                    <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
                </svg>
            </button>
            <a href="">RESERVAR ÁREA</a>
            <a href="#" class="active">CONTROLE DE VISITANTES</a>
            <a href="ocorrencia.jsp">REGISTRO DE OCORRÊNCIAS</a>
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

    <section class="container">
        <div class="wrapper">
            <div class="text">Adicionar novo visitante</div>
            <div class="buttons">
                <input type="text" placeholder="Nome do visitante">
                <input type="text" placeholder="Sobrenome do visitante">
                <input type="text" placeholder="Documento do visitante">
                <input type="text" placeholder="Contato do visitante">
                <button type="button">Salvar visitante</button>
            </div>
        </div>
    </section>
    <section class="container">
        <div class="wrapper">
            <div class="text">Visitantes permitidos em minha residência</div>
            <div class="classList">
                <div class="itemsList"></div>
                <div class="itemsList"></div>
                <div class="itemsList"></div>
                <div class="itemsList"></div>
                <div class="itemsList"></div>
            </div>
        </div>
    </section>
</body>

</html>