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





async function cadastrarVisitante() {
    const { value: nome } = await Swal.fire({
      title: 'Novo Visitante',
      html:
        '<input id="swal-nome" class="swal2-input" placeholder="Nome" required>' +
        '<input id="swal-sobrenome" class="swal2-input" placeholder="Sobrenome" required>' +
        '<input type="number" id="swal-documento" class="swal2-input" placeholder="Documento" required>' +
        '<input id="swal-telefone" class="swal2-input" placeholder="Telefone">',
      focusConfirm: false,
      preConfirm: () => {
        return {
          nome: document.getElementById('swal-nome').value,
          sobrenome: document.getElementById('swal-sobrenome').value,
          documento: document.getElementById('swal-documento').value,
          telefone: document.getElementById('swal-telefone').value
        };
      }
    });


     if (nome.nome ===  "" || nome.sobrenome === "" || nome.documento === "") {
            // Exibe mensagem de erro
            Swal.fire({
                icon: 'error',
                title: 'Oops...',
                text: 'Nome e Documento são campos obrigatórios!',
            })
            return false;
        }
      document.getElementById('nome').value = nome.nome;
      document.getElementById('sobrenome').value = nome.sobrenome;
      document.getElementById('documento').value = nome.documento;
      document.getElementById('fone').value = nome.telefone;



      // Submeta o formulário se necessário
       document.forms["frmContato"].submit();

  }


   function editarVisitante(id, nome, sobrenome,documento,fone) {

      Swal.fire({
        title: 'Editar Visitante',
        html:
          '<input id="swal-nome" class="swal2-input" placeholder="Nome" value="' + nome + '" required>' +
          '<input id="swal-sobrenome" class="swal2-input" placeholder="Sobrenome" value="' + sobrenome + '" required>' +
          '<input type="number" id="swal-documento" class="swal2-input" placeholder="Documento" value="' + documento + '" required>' +
          '<input id="swal-fone" class="swal2-input" placeholder="Telefone" value="' + fone + '">',
        focusConfirm: false,
        preConfirm: () => {
          return {
          id : id,
            nome: document.getElementById('swal-nome').value,
            sobrenome: document.getElementById('swal-sobrenome').value,
            documento: document.getElementById('swal-documento').value,
            fone: document.getElementById('swal-fone').value
          };
        }
      }).then((result) => {
        if (result.isConfirmed) {
          const editedId = result.value.id;
          const editedNome = result.value.nome;
          const editedSobrenome = result.value.sobrenome;
          const editedDocumento = result.value.documento;
          const editedFone = result.value.fone;
           console.log(editedId,editedNome, editedSobrenome,editedDocumento , editedFone )
          // Crie um novo formulário e envie as informações para onde desejar
          const form = document.createElement('form');
          form.method = 'post';
          form.action = 'update';

          const hiddenId = document.createElement('input');
          hiddenId.type = 'hidden';
          hiddenId.name = 'hiddenId';
          hiddenId.value = id;

          const hiddenNome = document.createElement('input');
          hiddenNome.type = 'hidden';
          hiddenNome.name = 'editedNome';
          hiddenNome.value = editedNome;

           const hiddenSobrenome = document.createElement('input');
           hiddenSobrenome.type = 'hidden';
           hiddenSobrenome.name = 'editedSobrenome';
           hiddenSobrenome.value = editedSobrenome;

          const hiddenDocumento = document.createElement('input');
          hiddenDocumento.type = 'hidden';
          hiddenDocumento.name = 'editedDocumento';
          hiddenDocumento.value = editedDocumento;

          const hiddenFone = document.createElement('input');
          hiddenFone.type = 'hidden';
          hiddenFone.name = 'editedFone';
          hiddenFone.value = editedFone;

          form.appendChild(hiddenId);
          form.appendChild(hiddenNome);
          form.appendChild(hiddenSobrenome);
          form.appendChild(hiddenDocumento);
          form.appendChild(hiddenFone);

          document.body.appendChild(form);
          form.submit();
        }
      });
    }