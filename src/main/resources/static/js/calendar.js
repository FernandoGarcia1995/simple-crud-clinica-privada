$(document).ready(function() {
	loadCalendario();
});

function loadCalendario() {
	
	var ArrayData = [];
	var startDateTime = 0;

	$.ajax({
		url: "/cita/devolver-citas",
		timeout: 500,
		type: "post",
		dataType: "json",
		success: function(data) {
			
			var newData = JSON.parse(JSON.stringify(data));
			
			for (var i = 0; i < newData.cita.length; ++i) {
						
				var dataObject = new Object();		
				startDateTime = newData.cita[i].fecha;
				dataObject.start = moment(startDateTime, "YYYY-MM-DD HH:mm:ss").format('YYYY-MM-DD HH:mm');	
    			dataObject.end = moment(startDateTime, "YYYY-MM-DD HH:mm:ss").add(30, 'minutes').format('YYYY-MM-DD HH:mm');	
				dataObject.title = newData.cita[i].codCita;
				ArrayData.push(dataObject);
				
            }
			
			pushCalendario(ArrayData);

		},
		error: function(xhr, status) {
			console.log("Error al realizar la solicitud : " + status);
		}
	});
}


function pushCalendario(eventData) {

	var eventSources = [];
	var data = new Object();	
	var calendarEl = $('#calendar')[0];
	var todayDate = moment().startOf("day");
	var TODAY = todayDate.format("YYYY-MM-DD");
	
	data.events = eventData;
	data.color = 'red';
	data.textColor = 'red';

	calendar = new FullCalendar.Calendar(calendarEl, {
		timeZone: 'UTC',
		initialDate: TODAY,
		nowIndicator: true,
		editable: false,
		locale: 'es',
		headerToolbar: {
        left: "prev,next today",
        center: "title",
        right: "dayGridMonth,listMonth"
   		},
		events: data
	});

	calendar.render();

}


