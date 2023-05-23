var header           = document.getElementById('header');
var navigationHeader = document.getElementById('navigation_header');
var content          = document.getElementById('content');
var showSidebar      = false;

    function toggleSidebar()
    {
        showSidebar = !showSidebar;
        if(showSidebar)
        {
            navigationHeader.style.marginLeft = '-10vw';
            navigationHeader.style.animationName = 'showSidebar';
            content.style.filter = 'blur(2px)';
        }
        else
        {
            navigationHeader.style.marginLeft = '-100vw';
            navigationHeader.style.animationName = '';
            content.style.filter = '';
        }
    }

    function closeSidebar()
    {
        if(showSidebar)
        {
            showSidebar = true;
            toggleSidebar();
        }
    }

    window.addEventListener('resize', function(event) {
        if(window.innerWidth > 768 && showSidebar) 
        {  
            showSidebar = true;
            toggleSidebar();
        }
    });


function cancelarReserva() {
    Swal.fire({
        title: 'Cancelar reserva?',
        text: "Você não será capaz de reverter isso!",
        timer: 10000,
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sim, cancelar reserva!'
    }).then((result) => {
        if (result.isConfirmed) {
            document.forms["frmDelete"].submit();
        }
    });
}




