package com.clinicadental.clinicadental.form;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class MedicoForm {
	
	private Integer id;
	
	@Size(min = 2, message = "el nombre no puede ser demasiado corto")
	@Size(max = 45, message = "el nombre no puede ser demasiado largo")
	@NotBlank(message = "El campo no puede estar vacio")
	private String nombre;
	
	@Size(min = 2, message = "La longitud de los apellidos no puede ser demasiado corta")
	@Size(max = 45, message = "La longitud de los apellidos no puede ser demasiado larga")
	@NotBlank(message = "El campo no puede estar vacio")
	private String apellidos;
	
	@Pattern(regexp = "\\d{8}[a-z A-Z]", message = "El formato del dni es incorrecto")
	private String dni;
	
	@Pattern(regexp = "\\d{5}", message = "El formato del codigo postal es incorrecto")
	private String codPostal;
	
	@Size(min = 2, message = "La longitud de la ciudad no puede ser demasiado corta")
	@Size(max = 45, message = "La longitud de la ciudad no puede ser demasiado larga")
	@NotBlank(message = "El campo no puede estar vacio")
	private String ciudad;

	@NotNull(message = "El campo no puede estar vacio")
	private BigDecimal salario;
	
	private int especialidadOption;
	
	public MedicoForm(String nombre, String apellidos, String dni, String codPostal, String ciudad, BigDecimal salario) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
		this.salario = salario;
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
	public String getApellidos() {
		return apellidos;
	}
	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getCodPostal() {
		return codPostal;
	}
	public void setCodPostal(String codPostal) {
		this.codPostal = codPostal;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public int getEspecialidadOption() {
		return especialidadOption;
	}
	public void setEspecialidadOption(int especialidadOption) {
		this.especialidadOption = especialidadOption;
	}
	public BigDecimal getSalario() {
		return salario;
	}
	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}
	

}
