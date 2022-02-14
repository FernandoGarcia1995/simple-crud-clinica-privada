package com.clinicadental.clinicadental.form;
import java.math.BigDecimal;
import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;


public class CitaForm {
	
	private Integer id;
	
	@DateTimeFormat (pattern="dd/MM/yy hh:mm")
	@NotNull(message = "El campo no puede estar vacio")
	private Date fecha;
	
	@NotBlank(message = "El campo no puede estar vacio")
	private String codCita;
	
	private BigDecimal precio;
	
	private int clienteOptionId;
	
	private int diagnosticoOptionId;
	
	private int personaAsignadaOptionId;
	
	public CitaForm() {
	}
	
	public CitaForm(Date fecha, int clienteOptionId, int diagnosticoOptionId, int personaAsignadaOptionId) {
		this.fecha = fecha;
		this.clienteOptionId = clienteOptionId;
		this.diagnosticoOptionId = diagnosticoOptionId;
		this.personaAsignadaOptionId = personaAsignadaOptionId;
	}


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getClienteOptionId() {
		return clienteOptionId;
	}
	public void setClienteOptionId(int clienteOptionId) {
		this.clienteOptionId = clienteOptionId;
	}
	public int getDiagnosticoOptionId() {
		return diagnosticoOptionId;
	}
	public void setDiagnosticoOptionId(int diagnosticoOptionId) {
		this.diagnosticoOptionId = diagnosticoOptionId;
	}
	public int getPersonaAsignadaOptionId() {
		return personaAsignadaOptionId;
	}
	public void setPersonaAsignadaOptionId(int personaAsignadaOptionId) {
		this.personaAsignadaOptionId = personaAsignadaOptionId;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public String getCodCita() {
		return codCita;
	}
	public void setCodCita(String codCita) {
		this.codCita = codCita;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
}
