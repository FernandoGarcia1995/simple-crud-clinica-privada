package com.clinicadental.clinicadental.entity;


import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

//test
@Entity
@Table(name = "medicos")
public class Medico {
	
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
    @Column
    private BigDecimal salario;
    
    @Transient
    private int especialidadOption;
    
    @ManyToOne(cascade= {CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "id_especialidad")
	private Especialidad especialidad;
    
    @OneToMany(mappedBy = "personaAsignada", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<Cita> citas;
    
	public Medico() {
	}

	
	public Medico(String nombre, String apellidos, String dni, String codPostal, String ciudad, BigDecimal salario,
			Especialidad especialidad) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
		this.salario = salario;
		this.especialidad = especialidad;
	}
	
	public Medico(Integer id, String nombre, String apellidos, String dni, String codPostal, String ciudad,
			BigDecimal salario, Especialidad especialidad) {
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.dni = dni;
		this.codPostal = codPostal;
		this.ciudad = ciudad;
		this.salario = salario;
		this.especialidadOption = especialidadOption;
		this.especialidad = especialidad;
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

	public BigDecimal getSalario() {
		return salario;
	}

	public void setSalario(BigDecimal salario) {
		this.salario = salario;
	}

	public Especialidad getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(Especialidad especialidad) {
		this.especialidad = especialidad;
	}

	public Set<Cita> getCitas() {
		return citas;
	}
	
	public void setCitas(Set<Cita> citas) {
		this.citas = citas;
	}
	
	public int getEspecialidadOption() {
		return especialidadOption;
	}
	
	public void setEspecialidadOption(int especialidadOption) {
		this.especialidadOption = especialidadOption;
	}

}
