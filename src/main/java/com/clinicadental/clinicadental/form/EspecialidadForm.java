package com.clinicadental.clinicadental.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class EspecialidadForm {

	private Integer id;
	
	@NotBlank(message = "El campo no puede estar vacio")
	private String codEspecialidad;
	
	@Size(min = 2, message = "El nombre no puede ser demasiado corto")
	@Size(max = 45, message = "El nombre no puede ser demasiado largo")
	@NotBlank(message = "El campo no puede estar vacio")
	private String nombre;

	private Double salario;
	
	@Size(min = 2, message = "La longitud no puede ser demasiado corta")
	@Size(max = 400, message = "La longitud no puede ser demasiado larga")
	@NotBlank(message = "El campo no puede estar vacio")
	private String descripcion;

	public EspecialidadForm(String codEspecialidad, String nombre, Double salario, String descripcion) {
		this.codEspecialidad = codEspecialidad;
		this.nombre = nombre;
		this.salario = salario;
		this.descripcion = descripcion;
	}

	public String getCodEspecialidad() {
		return codEspecialidad;
	}
	public void setCodEspecialidad(String codEspecialidad) {
		this.codEspecialidad = codEspecialidad;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getSalario() {
		return salario;
	}
	public void setSalario(Double salario) {
		this.salario = salario;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
