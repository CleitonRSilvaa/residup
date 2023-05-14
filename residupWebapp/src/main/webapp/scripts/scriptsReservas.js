
function cancelarReserva(id) {
    Swal.fire({
        title: 'Deletar Visitante?',
        text: "Você não será capaz de reverter isso!",
        timer: 10000,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sim, deletar reserva!'
    }).then((result) => {
        if (result.isConfirmed) {
            window.location.href = "/deleteReserva?idReserva=" + id;
        }
    });
}
