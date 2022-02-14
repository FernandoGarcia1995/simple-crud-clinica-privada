package com.clinicadental.clinicadental.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.clinicadental.clinicadental.entity.Especialidad;
import com.clinicadental.clinicadental.form.EspecialidadForm;
import com.clinicadental.clinicadental.repository.EspecialidadRepository;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {
	
	private static final Logger log = LogManager.getLogger(EspecialidadServiceImpl.class);
	
	private EspecialidadRepository especialidadRepository;
	
	@Autowired
	public EspecialidadServiceImpl(EspecialidadRepository especialidadRepository) {
		this.especialidadRepository = especialidadRepository;
	}

	@Override
	@Transactional
	public void guardarEspecialidad(EspecialidadForm especialidadForm) {

		Especialidad especialidad = new Especialidad(especialidadForm.getCodEspecialidad(), especialidadForm.getNombre(), especialidadForm.getDescripcion());
		
        try {
        	log.info("Guardando especialidad...");
        	especialidadRepository.save(especialidad);
        	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras guardando la especialidad: " + ex.getMessage());
            ex.printStackTrace();

        }
		
	}

	@Override
	@Transactional
	public boolean comprobarCodigo(String codEspecialidad) {
		try {
			log.info("Comprobando Dni del cliente..");		
			return especialidadRepository.existsByCodEspecialidad(codEspecialidad);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras al comprobar el dni del cliente: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public List<Especialidad> buscarEspecialidades() {
		try {
			log.info("buscando clientes..");		
			return especialidadRepository.findAll();
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se buscaban clientes " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	@Transactional
	public Optional<Especialidad> buscarEspecialidad(int idEspecialidad) {
		try {
			log.info("buscando especialidad..");		
			return especialidadRepository.findById(idEspecialidad);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se la especialidad " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}


}
