package com.clinicadental.clinicadental.response;

import com.clinicadental.clinicadental.response.object.Facturacion;

public class ResponseFacturacion {
	
	private Facturacion facturacion;
	
	private String message;

	public ResponseFacturacion(Facturacion facturacion, String message) {
		this.facturacion = facturacion;
		this.message = message;
	}

	public Facturacion getFacturacion() {
		return facturacion;
	}

	public void setFacturacion(Facturacion facturacion) {
		this.facturacion = facturacion;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}
