package com.clinicadental.clinicadental.service;

import java.util.List;
import java.util.Optional;

import com.clinicadental.clinicadental.entity.Especialidad;
import com.clinicadental.clinicadental.form.EspecialidadForm;

public interface EspecialidadService {
	
	public void guardarEspecialidad(EspecialidadForm especialidad);
	
	public boolean comprobarCodigo(String codEspecialidad);
	
	public List<Especialidad> buscarEspecialidades();
	
	public Optional<Especialidad> buscarEspecialidad(int idEspecialidad);

}
