function confirmar(id) {
    Swal.fire({
        title: 'Deletar Visitante?',
        text: "Você não será capaz de reverter isso!",
        timer: 10000,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sim, deletar visitante!'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "delete?id=" + id
        }
    })

}

$(document).ready(function () {
    $("button[data-target='#editarVisitantesModal']").click(function () {
        // Obter os valores atuais do item
        let id = $(this).data("id");
        let nome = $(this).data("nome");
        let sobrenome = $(this).data("sobrenome");
        let documento = $(this).data("documento");
        let fone = $(this).data("fone");
        
        $("#editarVisitantesModal #idEdicao").val(id);
        $("#editarVisitantesModal #nomeEdicao").val(nome);
        $("#editarVisitantesModal #sobrenomeEdicao").val(sobrenome);
        $("#editarVisitantesModal #documentoEdicao").val(documento);
        $("#editarVisitantesModal #foneEdicao").val(fone);
    });
});



function LimpaModalCriacao() {
  $("#novoVisitantesModal #nome").val("");
  $("#novoVisitantesModal #sobrenomenome").val("");
  $("#novoVisitantesModal #documento").val("");
  $("#novoVisitantesModal #fone").val("");
}

function validarEditar() {
    console.log(document.forms["frmContatoEdit"].action);
    
    let nome = document.querySelector("#editarVisitantesModal input[name='nome']").value;
    let sobrenome = document.querySelector("#editarVisitantesModal input[name='sobrenome']").value;
    let documento = document.querySelector("#editarVisitantesModal input[name='documento']").value;
    let fone = document.querySelector("#editarVisitantesModal input[name='fone']").value;
    console.log(nome);
    if (nome === "" || documento ==="") {
        // Exibe mensagem de erro
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Nome, Sobrenome e Documento são campos obrigatórios!',
        });
        return false;
    }
    document.forms["frmContatoEdit"].submit();
}

function validarNovo() {
    console.log(document.forms["frmContato"].action)

    let nome = document.querySelector("#novoVisitantesModal input[name='nome']").value;
    let sobrenome = document.querySelector("#novoVisitantesModal input[name='sobrenome']").value;
    let documento = document.querySelector("#novoVisitantesModal input[name='documento']").value;
    let fone = document.querySelector("#novoVisitantesModal input[name='fone']").value;
    
    if (nome === "" || documento === "") {
        // Exibe mensagem de erro
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Nome e Documento são campos obrigatórios!',
        })
        return false;
    }

//    if (email.length  > 0){
//        var emailRegex =/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
//        if (!emailRegex.test(email)) {
//            // Exibe mensagem de erro
//            Swal.fire({
//                icon: 'error',
//                title: 'Oops...',
//                text: 'Digite um endereço de e-mail valido!',
//            })
//            return false;
//        }
//    }

    document.forms["frmContato"].submit();
}





