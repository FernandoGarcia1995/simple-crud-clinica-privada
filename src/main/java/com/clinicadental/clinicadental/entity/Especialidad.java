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

@Entity
@Table(name = "especialidades")
public class Especialidad {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")                   
	private Integer id;
    
    @Column(name="cod_especialidad")                   
	private String codEspecialidad;
    
    @Column
    private String nombre;
    
    @Column
    private String descripcion;
    
    @OneToMany(mappedBy = "especialidad", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST })
    private Set<Medico> medicos;
    
	public Especialidad() {
	}
	
	
	public Especialidad(String codEspecialidad, String nombre, String descripcion) {
		this.codEspecialidad = codEspecialidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
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

	public Set<Medico> getMedicos() {
		return medicos;
	}
	
	public void setMedicos(Set<Medico> medicos) {
		this.medicos = medicos;
	}

	
}
