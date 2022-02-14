package com.clinicadental.clinicadental.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clinicadental.clinicadental.response.ResponseFacturacion;
import com.clinicadental.clinicadental.response.object.Facturacion;
import com.clinicadental.clinicadental.service.FacturacionService;

@Controller
@RequestMapping("/facturacion")
public class FacturacionController {
	
	private FacturacionService facturacionService;
	
	@Autowired
	public FacturacionController(FacturacionService facturacionService) {
		this.facturacionService = facturacionService;
	}

	@GetMapping("/presupuesto")
	public String presupuesto() {
		return "/facturacion/presupuesto";
	}
	
	@PostMapping("/devolver-presupuesto")
	@ResponseBody
	public ResponseFacturacion devolverPresupuesto() {
		Facturacion facturacion = facturacionService.calcularFacturacion();
		return new ResponseFacturacion(facturacion, "Operacion realizada correctamente");
	}
}
