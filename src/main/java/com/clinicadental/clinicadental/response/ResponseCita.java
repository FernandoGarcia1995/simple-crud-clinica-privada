package com.clinicadental.clinicadental.response;

import java.util.List;

import com.clinicadental.clinicadental.entity.Cita;

public class ResponseCita {
		
	private List<Cita> cita;
	
	private String message;

	public ResponseCita(List<Cita> cita, String message) {
		this.cita = cita;
		this.message = message;
	}

	public List<Cita> getCita() {
		return cita;
	}

	public void setCita(List<Cita> cita) {
		this.cita = cita;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


}
