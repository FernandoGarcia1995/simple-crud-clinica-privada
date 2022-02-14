package com.clinicadental.clinicadental.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DiagnosticoForm {
                  
	private Integer id;
	
	@NotBlank(message = "El campo no puede estar vacio")
	private String codDiagnostico;
	
	@Size(min = 2, message = "el nombre no puede ser demasiado corto")
	@Size(max = 45, message = "el nombre no puede ser demasiado largo")
	@NotBlank(message = "El campo no puede estar vacio")
	private String nombre;
	
	@Size(min = 2, message = "La descripcion no puede ser demasiado corta")
	@Size(max = 400, message = "El descripcion no puede ser demasiado larga")
	@NotBlank(message = "El campo no puede estar vacio")
	private String descripcion;
	
	@NotNull(message = "El campo no puede estar vacio")
	private BigDecimal precio;
	
	public DiagnosticoForm(String codDiagnostico, String nombre, String descripcion,BigDecimal precio) {
		this.codDiagnostico = codDiagnostico;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCodDiagnostico() {
		return codDiagnostico;
	}
	public void setCodDiagnostico(String codDiagnostico) {
		this.codDiagnostico = codDiagnostico;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public BigDecimal getPrecio() {
		return precio;
	}
	public void setPrecio(BigDecimal precio) {
		this.precio = precio;
	}
	
}
