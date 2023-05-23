function deletarOcorrencia(id) {
Swal.fire({
  title: 'Deletar ocorrência?',
  text: 'Você não será capaz de reverter isso!',
  timer: 10000,
  icon: 'warning',
  showCancelButton: true,
  confirmButtonColor: '#d33',
  cancelButtonColor: '#3085d6',
  confirmButtonText: 'Sim, deletar ocorrência!'
}).then((result) => {
  if (result.isConfirmed) {
    fetch(`/occurrenceDelete?id=${id}`, {
      method: 'DELETE'
    })
    .then(response => {
      if (response.ok) {
        Swal.fire({
          title: 'Sucesso',
          text: 'Ocorrência excluída com sucesso!',
          icon: 'success'
        }).then(() => {
          window.location.href = '/Ocorrencia';
        });
      } else {
        Swal.fire({
          title: 'Erro',
          text: 'Não foi possível excluir a ocorrência.',
          icon: 'error'
        });
      }
    })
    .catch(error => {
      console.log(error);
      alert('Erro ao excluir a ocorrência. Tente novamente mais tarde.');
    });
  }
});
}
