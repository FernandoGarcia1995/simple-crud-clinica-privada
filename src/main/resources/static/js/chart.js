$(document).ready(function() {
	loadFacturacion();
	loadChart();
});



const meses = [
	'Enero',
	'Febrero',
	'Marzo',
	'Abril',
	'Mayo',
	'Junio',
	'Julio',
	'Agosto',
	'Septiembre',
	'Octubre',
	'Noviembre',
	'Diciembre',
];

const nombreEmpleados = [
	'empleado1',
	'empleado1',
	'empleado1',
];

var dataIngresos = {
	labels: meses,
	datasets: [{
		label: 'Ingresos',
		backgroundColor: 'rgb(255, 99, 132)',
		borderColor: 'rgb(255, 99, 132)'
		//data: [0, 1000, 2000, 3000, 4000, 5000, 6000, 0, 1000, 2000, 3000, 4000, 5000, 6000],
	},
	{
		label: 'Gastos',
		backgroundColor: 'rgb(51, 116, 255)',
		borderColor: 'rgb(51, 116, 255)',
		//mdata: [0, 2000, 2000, 3000, 4000, 5000, 6000, 0, 1000, 2000, 3000, 4000, 5000, 6000],
	}]
};


var dataCitas = {
	labels: nombreEmpleados,
	datasets: [{
		label: 'Nº de citas',
		backgroundColor: 'rgb(51, 116, 255)',
		borderColor: 'rgb(51, 116, 255)',
		data: [2, 4, 5],
	}]
};



const configIG = {
	type: 'line',
	data: dataIngresos,
	options: {
		responsive: true,
		plugins: {
			tooltip: {
				mode: 'index'
			},
		},
		interaction: {
			mode: 'nearest',
			axis: 'x',
			intersect: false
		},
		scales: {
			x: {
				title: {
					display: true,
					text: 'Month'
				}
			},
			y: {
				stacked: true,
				title: {
					display: true,
					text: 'Value'
				}
			}
		}
	}
};


const configNC = {
	type: 'bar',
	data: dataCitas,
	options: {
		responsive: true,
		plugins: {
			legend: {
				position: 'top',
			},
		}
	},
};

  
  
function loadFacturacion() {
	
	$.ajax({
        url: "/facturacion/devolver-presupuesto",
        timeout:500,
        type: "post",
        dataType: "json",
        success: function (data) {
			var ingresosData = JSON.parse(JSON.stringify(data));		
			console.log(ingresosData);

        },
        error: function (xhr, status) {
          	console.log("Error al realizar la solicitud : " + status);
        }
    });
}

function loadChart() {
	
	const ingresos = new Chart(
		$('#ingresos').attr('id'),
		configIG
	);


	const numeroCitas = new Chart(
		$('#n_facturas').attr('id'),
		configNC
	);

	ingresos.data.datasets[0].data = [0, 1000, 2000, 3000, 4000, 5000, 6000, 0, 1000, 2000, 3000, 4000, 5000, 6000];
	ingresos.update();
}


