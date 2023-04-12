function confirmar(idcon) {
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
            Swal.fire(
                    'Deleted!',
                    'Your file has been deleted.',
                    'success'
                    );
            window.location.href = "delete?idcon=" + idcon
        }
    })

}

$(document).ready(function () {
    $("button[data-target='#editarVisitantesModal']").click(function () {
        // Obter os valores atuais do item
        var id = $(this).data("id");
        var nome = $(this).data("nome");
        var fone = $(this).data("fone");
        var email = $(this).data("email");

        $("#editarVisitantesModal #idconEdicao").val(id);
        $("#editarVisitantesModal #nomeEdicao").val(nome);
        $("#editarVisitantesModal #emailEdicao").val(email);
        $("#editarVisitantesModal #foneEdicao").val(fone);
    });
});



function LimpaModalCriacao() {
  $("#novoVisitantesModal #nome").val("");
  $("#novoVisitantesModal #email").val("");
  $("#novoVisitantesModal #fone").val("");
}

function validarEditar() {

    console.log(document.forms["frmContatoEdit"].action)

    let nome = document.querySelector("#editarVisitantesModal input[name='nome']").value;
    let fone = document.querySelector("#editarVisitantesModal input[name='fone']").value;
    let email = document.querySelector("#editarVisitantesModal input[name='email']").value;

    if (nome == "" || fone == "") {
        // Exibe mensagem de erro
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Todos os campos são obrigatórios!',
        })
        return false;
    }
    if (email.length  > 0){
        let emailformat = /^[a-z0-9.]+@[a-z0-9]+\.[a-z]+\.([a-z]+)?$/i
        if(!emailformat.test(email)){
            // Exibe mensagem de erro
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Digite um endereço de e-mail valido!',
            })
            return false;
        }
    }

    document.forms["frmContatoEdit"].submit();
}


function validarNovo() {
    console.log(document.forms["frmContato"].action)

    let nome = document.querySelector("#novoVisitantesModal input[name='nome']").value;
    let fone = document.querySelector("#novoVisitantesModal input[name='fone']").value;
    let email = document.querySelector("#novoVisitantesModal input[name='email']").value;

    if (nome == "" || fone == "") {
        // Exibe mensagem de erro
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Todos os campos são obrigatórios!',
        })
        return false;
    }

    if (email.length  > 0){
        var emailRegex =/^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
        if (!emailRegex.test(email)) {
            // Exibe mensagem de erro
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Digite um endereço de e-mail valido!',
            })
            return false;
        }
    }

    document.forms["frmContato"].submit();
}





