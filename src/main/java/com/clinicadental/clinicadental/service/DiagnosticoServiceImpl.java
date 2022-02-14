package com.clinicadental.clinicadental.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicadental.clinicadental.entity.Diagnostico;
import com.clinicadental.clinicadental.form.DiagnosticoForm;
import com.clinicadental.clinicadental.repository.DiagnosticoRepository;

@Service
public class DiagnosticoServiceImpl implements DiagnosticoService{
	
	private static final Logger log = LogManager.getLogger(DiagnosticoServiceImpl.class);
	
	private DiagnosticoRepository diagnosticoRepository;
	
	
	@Autowired
	public DiagnosticoServiceImpl(DiagnosticoRepository diagnosticoRepository) {
		this.diagnosticoRepository = diagnosticoRepository;
	}

	@Override
	@Transactional
	public void guardarDiagnostico(DiagnosticoForm diagnosticoForm) {
		
		Diagnostico diagnostico = 
				new Diagnostico(diagnosticoForm.getCodDiagnostico(), diagnosticoForm.getNombre(), diagnosticoForm.getDescripcion(),diagnosticoForm.getPrecio());
		
        try {
        	log.info("Procediendo a guardar diagnostico");
        	
        	diagnosticoRepository.save(diagnostico);
        
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
        }
	}

	@Override
	@Transactional
	public boolean comprobarCodigo(String codDiagnostico) {
		
		try {
			log.info("Comprobando Dni del cliente..");		
			return diagnosticoRepository.existsByCodDiagnostico(codDiagnostico);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras al comprobar el dni del cliente: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public List<Diagnostico> buscarDiagnosticos() {
		try {
			log.info("buscando clientes..");		
			return diagnosticoRepository.findAll();
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se buscaban las especialidades: " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}
	
	

}
