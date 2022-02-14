

$( document ).ready(function() {
	alertify.defaults.theme.ok = "btn btn-primary";
	$(".decimal-mask").inputmask('decimal',{min:1, max:10000});
	$(".cod-postal").inputmask();
});

function searchMedicoList(data) {
	
	var search = $('#search-medico-list').val();
	
	
	$.ajax({
        url: "/medico/listado-medicos?page=1",
        timeout:500,
        data: {
            search
        },
        type: "post",
        dataType: "html",
        success: function (data) {
			if(data !== '') {
				$('#medico-list-table').html(data);
				$('#search-medico-list').val(search);
			}    
        },
        error: function (xhr, status) {
          	console.log("Error al realizar la solicitud : " + status);
        }
    });
}

function eliminarMedico(data) {

	$.ajax({
		url: "/medico/eliminar-medico",
		timeout: 500,
		data: { "idMedico": data },
		type: "post",
		dataType: "html",
		success: function(data) {
			window.location.href = "/medico/listado-medicos?page=1";	
		},
		error: function(xhr, status) {
			notyf.error(xhr.responseText + " -  " + "codigo de error - " + xhr.status);
		}
	});
}


$(document).on('click', '.delete-medico', function(e) {
	var idMedico = $(this).attr("data-id")
	alertify.confirm('Eliminar medico', '¿Deseas eliminar a este medico?', ()=> { eliminarMedico(idMedico) }, null);
});


$(document).on('keyup', '#search-medico-list', function(e) {
	if(e.keyCode == 13) {
		let data = $(this).val();
		searchMedicoList(data);
	}
});
