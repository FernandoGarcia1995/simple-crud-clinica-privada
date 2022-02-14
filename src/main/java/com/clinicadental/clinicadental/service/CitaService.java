package com.clinicadental.clinicadental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.clinicadental.clinicadental.entity.Cita;
import com.clinicadental.clinicadental.form.CitaForm;

public interface CitaService {
	
	public Optional<Cita> buscarCita(int idCita);
	
	public List<Cita> buscarCitas();
	
	public void guardarCita(CitaForm cita);
	
	public void editarCita(CitaForm cita);
	
	public Page<Cita> listarCita(int pagina);
	
	public Page<Cita> listarCitaPorCodigo(int pagina,String searchCodigo);
	
	public boolean borrarCita(int idCita);

}
