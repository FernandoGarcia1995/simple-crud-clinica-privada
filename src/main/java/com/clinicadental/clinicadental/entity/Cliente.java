package com.clinicadental.clinicadental.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "clientes")
public class Cliente {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")                   
	private Integer id;
    @Column
	private String nombre;
    @Column
	private String apellidos;
    @Column
	private String dni;
    @Column(name="cod_postal")
	private String codPostal;
    @Column
	private String ciudad;
    
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonBackReference
    private Set<Cita> citas;
    
	public Cliente() {
	}
	
	
	public Cliente(String nombre, String apellidos, String dni, String codPostal, String ciudad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
	}
	

	public Cliente(Integer id, String nombre, String apellidos, String dni, String codPostal, String ciudad) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
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


	public Set<Cita> getCitas() {
		return citas;
	}


	public void setCitas(Set<Cita> citas) {
		this.citas = citas;
	}
	
	
	
	


}
