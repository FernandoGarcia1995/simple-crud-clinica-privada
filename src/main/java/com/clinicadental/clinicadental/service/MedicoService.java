package com.clinicadental.clinicadental.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.clinicadental.clinicadental.entity.Medico;
import com.clinicadental.clinicadental.form.MedicoForm;

public interface MedicoService {
	
	public Page<Medico> listarMedicos(int pagina);
	
	public List<Medico> buscarMedicos();
	
	public boolean borrarMedico(int idMedico);
	
	public Optional<Medico> buscarMedico(int idMedico);
	
	public void guardarMedico(MedicoForm medico);
	
	public boolean comprobarDni(String dni);
	
	public void editarMedico(MedicoForm medicoForm);
	
	public Page<Medico> listarPorDni(int pagina,String searchString);

	
}
