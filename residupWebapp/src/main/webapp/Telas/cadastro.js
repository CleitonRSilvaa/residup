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

  // Adicionando os ouvintes de eventos para os campos
  nomeInput.addEventListener('input', validarNomeSobrenome);
  sobrenomeInput.addEventListener('input', validarNomeSobrenome);
  cpfInput.addEventListener('input', validarCPF_RG);
  rgInput.addEventListener('input', validarCPF_RG);
}

// Chamando a função para iniciar a validação do formulário
validarFormulario();