

var notyf = new Notyf({
	duration: 2000,
	position: {
		x: 'center',
		y: 'top',
	}
});

$( document ).ready(function() {
	alertify.defaults.theme.ok = "btn btn-primary";
	$(".cod-postal").inputmask();
});

function searchClienteList(data) {
	
	var search = $('#search-clientes-list').val();
	
	$.ajax({
        url: "/cliente/listado-clientes?page=1",
        data: {
            search
        },
        type: "post",
        dataType: "html",
        success: function (data) {
			if(data !== '') {
            	$('#client-list-table').html(data);
            	$('#search-clientes-list').val(search);
            }
        },
        error: function (xhr, status) {
          	console.log("Error al realizar la solicitud : " + status);
        }
    });
}

function eliminarCliente(data) {

	$.ajax({
		url: "/cliente/eliminar-cliente",
		timeout: 500,
		data: { "idCliente": data },
		type: "post",
		dataType: "html",
		success: function(data) {
			window.location.href = "/cliente/listado-clientes?page=1";	
		},
		error: function(xhr, status) {
			notyf.error(xhr.responseText + " -  " + "codigo de error - " + xhr.status);
		}
	});
}


$(document).on('click', '.delete-cliente', function() {
   var idCliente = $(this).attr("data-id")
   alertify.confirm('Eliminar cliente', '¿Deseas eliminar a este cliente?', ()=> { eliminarCliente(idCliente) }, null);
});


$(document).on('keyup', '#search-clientes-list', function(e) {
	if(e.keyCode == 13) {
		let data = $(this).val();
		searchClienteList(data);
	}
});
