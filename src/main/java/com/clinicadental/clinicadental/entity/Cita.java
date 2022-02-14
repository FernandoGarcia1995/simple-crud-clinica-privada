package com.clinicadental.clinicadental.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "citas")
public class Cita {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")    
	private Integer id;
    
    @Column(name="cod_cita")
	private String codCita;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_cliente")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Cliente cliente;
    
    @DateTimeFormat (pattern="dd/MM/yy hh:mm")
    @Column(name="fecha")
	private Date fecha;
	
    @ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_medico")
    @JsonIgnore
	private Medico personaAsignada;
    
    @ManyToOne(fetch = FetchType.LAZY,cascade= {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn(name = "id_diagnostico")
    @JsonIgnore
	private Diagnostico diagnostico;
    
    @Transient
    @JsonIgnore
	private int clienteOptionId;
    @Transient
    @JsonIgnore
    private int diagnosticoOptionId;
    @Transient
    @JsonIgnore
	private int personaAsignadaOptionId;
    
	public Cita() {
	}

	public Cita(String codCita, Cliente cliente, Date fecha, Medico personaAsignada, Diagnostico diagnostico) {
		this.codCita = codCita;
		this.cliente = cliente;
		this.fecha = fecha;
		this.personaAsignada = personaAsignada;
		this.diagnostico = diagnostico;
	}
	
	public Cita(Integer id, String codCita, Cliente cliente, Date fecha, Medico personaAsignada,
			Diagnostico diagnostico) {
		this.id = id;
		this.codCita = codCita;
		this.cliente = cliente;
		this.fecha = fecha;
		this.personaAsignada = personaAsignada;
		this.diagnostico = diagnostico;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Medico getPersonaAsignada() {
		return personaAsignada;
	}

	public void setPersonaAsignada(Medico personaAsignada) {
		this.personaAsignada = personaAsignada;
	}

	public Diagnostico getDiagnostico() {
		return diagnostico;
	}

	public void setDiagnostico(Diagnostico diagnostico) {
		this.diagnostico = diagnostico;
	}

	public String getCodCita() {
		return codCita;
	}

	public void setCodCita(String codCita) {
		this.codCita = codCita;
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
	


}
