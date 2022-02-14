package com.clinicadental.clinicadental.service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicadental.clinicadental.entity.Cita;
import com.clinicadental.clinicadental.repository.CitaRepository;
import com.clinicadental.clinicadental.response.object.Facturacion;

@Service
public class FacturacionServiceImpl implements FacturacionService {
	
	
	private CitaRepository citaRepository;
	private Map<String, BigDecimal> map;
	private Facturacion facturacion;
	
	@Autowired
	public FacturacionServiceImpl(CitaRepository citaRepository, Map<String, BigDecimal> map, Facturacion facturacion) {
		this.citaRepository = citaRepository;
		this.map = map;
		this.facturacion = facturacion;
	}
	
	public void loadMap() {
		map.put("enero", new BigDecimal(0));
		map.put("febrero", new BigDecimal(0));
		map.put("marzo", new BigDecimal(0));
		map.put("mayo", new BigDecimal(0));
		map.put("junio", new BigDecimal(0));
		map.put("julio", new BigDecimal(0));
		map.put("agosto", new BigDecimal(0));
		map.put("septiembre", new BigDecimal(0));
		map.put("octubre", new BigDecimal(0));
		map.put("noviembre", new BigDecimal(0));
		map.put("diciembre", new BigDecimal(0));
	}


	@Override
	public Facturacion calcularFacturacion() {
		
		loadMap();
		calcularIngresosPorMes();
		
		return facturacion;
	}
	
	@Transactional
	private void calcularIngresosPorMes() {
		Calendar calendario = Calendar.getInstance();
		BigDecimal sumIngresos = new BigDecimal(0);

		List<Cita> citas = citaRepository.findAll();
		for (Cita cita : citas) {
			calendario.setTime(cita.getFecha());
			switch (calendario.get(Calendar.MONTH)) {
			case 0:
				sumIngresos = map.get("enero").add(cita.getDiagnostico().getPrecio());
				map.put("enero", sumIngresos);
				break;
			case 1:
				sumIngresos = map.get("febrero").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 2:
				sumIngresos = map.get("marzo").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 3:
				sumIngresos = map.get("mayo").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 4:
				sumIngresos = map.get("junio").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 5:
				sumIngresos = map.get("julio").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 6:
				sumIngresos = map.get("agosto").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 7:
				sumIngresos = map.get("septiembre").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 8:
				sumIngresos = map.get("octubre").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 9:
				sumIngresos = map.get("noviembre").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			case 11:
				sumIngresos = map.get("diciembre").add(cita.getDiagnostico().getPrecio());
				map.put("febrero", sumIngresos);
				break;
			}
		}
		
		facturacion.setIngresosMensualesPorMes(map);	
	
	}
	

}
