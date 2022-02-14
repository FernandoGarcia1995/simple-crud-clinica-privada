package com.clinicadental.clinicadental.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.clinicadental.clinicadental.entity.Especialidad;
import com.clinicadental.clinicadental.entity.Medico;
import com.clinicadental.clinicadental.form.MedicoForm;
import com.clinicadental.clinicadental.repository.EspecialidadRepository;
import com.clinicadental.clinicadental.repository.MedicoRepository;

@Service
public class MedicoServiceImpl implements MedicoService{
	
	private static final Logger log = LogManager.getLogger(EspecialidadServiceImpl.class);
	
	private MedicoRepository medicoRepository;
	private EspecialidadRepository especialidadRepository;

	private final int pageSize = 10;
	
	@Autowired
	public MedicoServiceImpl(MedicoRepository medicoRepository, EspecialidadRepository especialidadRepository) {
		this.medicoRepository = medicoRepository;
		this.especialidadRepository = especialidadRepository;
	}

	@Override
	@Transactional
	public void guardarMedico(MedicoForm medicoForm) {
		
		try {
			
			log.info("Guardando medico...");
			
			Optional<Especialidad> especialidad = especialidadRepository.findById(medicoForm.getEspecialidadOption());
		
			Medico medico = new Medico(medicoForm.getNombre(), medicoForm.getApellidos()
				, medicoForm.getDni(), medicoForm.getCodPostal(), medicoForm.getCiudad(), medicoForm.getSalario(),especialidad.get());
        
        	medicoRepository.save(medico);
        	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
        }
		
	}

	@Override
	@Transactional
	public boolean comprobarDni(String dni) {
		try {
			log.info("Comprobando Dni del cliente..");
			return medicoRepository.existsByDni(dni);
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public Page<Medico> listarMedicos(int pagina) {
        try {
        	Pageable pagination = PageRequest.of(pagina - 1, pageSize);
        	log.info("Devolviendo lista de medico...");
        
        	return medicoRepository.findAll(pagination);
        	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
	}
	

	@Override
	@Transactional
	public Page<Medico> listarPorDni(int pagina, String searchString) {
		Pageable pagination = PageRequest.of(pagina - 1, pageSize);      
		
        try {
        	log.info("Devolviendo lista de clientes...");
        	if(searchString.isEmpty()){   		
        		return medicoRepository.findAll(pagination);
        	} else {
        		return medicoRepository.findAllByDni(pagination, searchString);
        	}
        	    	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
	}

	@Override
	@Transactional
	public List<Medico> buscarMedicos() {
		try {
			log.info("buscando medicos..");		
			return medicoRepository.findAll();
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se buscaban medicos " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	@Transactional
	public boolean borrarMedico(int idMedico) {
		try {
			log.info("Borrando cliente");
			
			medicoRepository.deleteById(idMedico);
			
			return true;
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se borra el medico: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public Optional<Medico> buscarMedico(int idMedico) {
		try {
			log.info("buscando medico");
			
			return medicoRepository.findById(idMedico);
				
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se buscaba medico: " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	@Transactional
	public void editarMedico(MedicoForm medicoForm) {
		
		try {
		
			log.info("editando medico...");
			
			Optional<Especialidad> especialidad = especialidadRepository.findById(medicoForm.getEspecialidadOption());
		
			Medico medico = new Medico(medicoForm.getId(),medicoForm.getNombre(), medicoForm.getApellidos()
				, medicoForm.getDni(), medicoForm.getCodPostal(), medicoForm.getCiudad(), medicoForm.getSalario(),especialidad.get());
			
			medicoRepository.save(medico);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se edita el medico: " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}

}
