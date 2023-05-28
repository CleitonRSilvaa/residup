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
            <a href="/listarResumo">PAGINA INICIAL</a>
            <a href="/reservas" >RESERVAR ÁREA</a>
            <a href="/visitantes" class="active">CONTROLE DE VISITANTES</a>
            <a href="/Ocorrencia">REGISTRO DE OCORRENCIAS</a>
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


    <!-- Botão para abrir o modal de criação -->
    <button onclick="openNovoVisitantesModal()">Abrir Modal de Criação</button>

    <!-- Botão para abrir o modal de edição -->
    <button onclick="openEditarVisitantesModal()">Abrir Modal de Edição</button>



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
                                <label for="sobrenome">Sobrenome:</label>
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


<script>
    // Função para abrir o modal de criação
    function openNovoVisitantesModal() {
        var modal = document.getElementById('novoVisitantesModal');
        modal.style.display = 'block';
    }

    // Função para abrir o modal de edição
    function openEditarVisitantesModal() {
        var modal = document.getElementById('editarVisitantesModal');
        modal.style.display = 'block';
    }
</script>



</body>

</html>