package com.clinicadental.clinicadental.service;

import java.util.List;

import com.clinicadental.clinicadental.entity.Diagnostico;
import com.clinicadental.clinicadental.form.DiagnosticoForm;

public interface DiagnosticoService {
	
	public List<Diagnostico> buscarDiagnosticos();
	
	public void guardarDiagnostico(DiagnosticoForm diagnostico);
	
	public boolean comprobarCodigo(String codDiagnostico);

}
