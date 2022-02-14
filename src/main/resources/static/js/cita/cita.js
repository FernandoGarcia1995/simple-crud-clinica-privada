

$( document ).ready(function() {
	
	alertify.defaults.theme.ok = "btn btn-primary";
	
	 $('.datepicker').datetimepicker({
		format: 'd/m/y h:i',
    	autoclose: true
	});
	
	$(".cod-mask").inputmask();

});


function searchCitaList(data) {
	
	var search = $('#cita-search-list').val();
	
	$.ajax({
        url: "/cita/listado-citas?page=1",
        timeout:500,
        data: {
            search
        },
        type: "post",
        dataType: "html",
        success: function (data) {
			if(data !== '') {
				$('#cita-list-table').html(data);
				$('#cita-search-list').val(search);
			}    
        },
        error: function (xhr, status) {
          	console.log("Error al realizar la solicitud : " + status);
        }
    });
}

function eliminarCita(data) {

	$.ajax({
		url: "/cita/eliminar-cita",
		timeout: 500,
		data: { "idCita": data },
		type: "post",
		dataType: "html",
		success: function(data) {
			window.location.href = "/cita/listado-citas?page=1";	
		},
		error: function(xhr, status) {
			notyf.error(xhr.responseText + " -  " + "codigo de error - " + xhr.status);
		}
	});
}



$(".delete-cita").on( "click", function() {
	let idCita = $(this).attr("data-id")
	alertify.confirm('Eliminar cita', '¿Deseas eliminar la cita?', ()=> { eliminarCita(idCita) }, null);
});


$(document).on('keyup', '#cita-search-list', function(e) {
	if(e.keyCode == 13){		
		let data = $(this).val();
		searchCitaList(data);
   }
});
