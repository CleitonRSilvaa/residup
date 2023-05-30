var meuBotao = document.getElementById("openModal");

meuBotao.innerHTML = '<img src="https://media.istockphoto.com/id/1364444442/pt/foto/brazilian-carnival-group-of-friends-celebrating-carnival-party.jpg?s=612x612&w=is&k=20&c=3HrWPUauuhy8ZQLhAQIThpc1eH_itFk16UHetErn-5Q=">';


const inputFile = document.querySelector("#custom-picture__input");
const pictureImage = document.querySelector(".custom-picture__image");
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



function openModalteste() {
    const openModalButton = document.getElementById("openModal");
    const modalOverlay = document.getElementById("custom-modalOverlay");

    openModalButton.addEventListener("click", function (event) {
    event.preventDefault();
    modalOverlay.style.opacity = "1";
    modalOverlay.style.pointerEvents = "auto";
    });

    modalOverlay.addEventListener("click", function (event) {
    if (event.target === modalOverlay) {
      modalOverlay.style.opacity = "0";
      modalOverlay.style.pointerEvents = "none";
    }
    });
}
