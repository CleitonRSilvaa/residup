window.addEventListener('DOMContentLoaded', function() {
    var labels = document.getElementsByClassName('oc');
    for (var i = 0; i < labels.length; i++) {
      var label = labels[i];
      var maxLength = 40; // Limite de caracteres desejado
      
      if (label.textContent.length > maxLength) {
        label.textContent = label.textContent.substring(0, maxLength) + '...';
      }
    }
  });