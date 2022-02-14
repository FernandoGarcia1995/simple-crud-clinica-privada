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

import com.clinicadental.clinicadental.entity.Cita;
import com.clinicadental.clinicadental.entity.Cliente;
import com.clinicadental.clinicadental.entity.Diagnostico;
import com.clinicadental.clinicadental.entity.Medico;
import com.clinicadental.clinicadental.form.CitaForm;
import com.clinicadental.clinicadental.repository.CitaRepository;
import com.clinicadental.clinicadental.repository.ClienteRepository;
import com.clinicadental.clinicadental.repository.DiagnosticoRepository;
import com.clinicadental.clinicadental.repository.MedicoRepository;

@Service
public class CitaServiceImpl implements CitaService{
	
	private static final Logger log = LogManager.getLogger(CitaServiceImpl.class);
	
	private CitaRepository citaRepository;
	private ClienteRepository clienteRepository;
	private MedicoRepository medicoRepository;
	private DiagnosticoRepository diagnosticoRepository;

	private final int pageSize = 10;
	
	
	@Autowired
	public CitaServiceImpl(CitaRepository citaRepository, ClienteRepository clienteRepository,
			MedicoRepository medicoRepository, DiagnosticoRepository diagnosticoRepository) {
		this.citaRepository = citaRepository;
		this.clienteRepository = clienteRepository;
		this.medicoRepository = medicoRepository;
		this.diagnosticoRepository = diagnosticoRepository;
	}

	@Override
	@Transactional
	public void guardarCita(CitaForm citaForm) {
		
		try {
			 
		Optional<Cliente> cliente = clienteRepository.findById(citaForm.getClienteOptionId());
		Optional<Medico> medico = medicoRepository.findById(citaForm.getPersonaAsignadaOptionId());
		Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(citaForm.getDiagnosticoOptionId());
	
		Cita cita = new Cita(citaForm.getCodCita(),cliente.get(), citaForm.getFecha(), medico.get(), diagnostico.get());
	      
        log.info("Guardando Cita...");
        citaRepository.save(cita);
        	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras guardando la cita: " + ex.getMessage());
            ex.printStackTrace();
        }
		
	}


	@Override
	@Transactional
	public Page<Cita> listarCitaPorCodigo(int pagina, String searchCodigo) {
		Pageable pagination = PageRequest.of(pagina - 1, pageSize);      
		
        try {
        	log.info("Devolviendo listado de citas...");
        	if(searchCodigo == null || searchCodigo.isEmpty())  {  
        		return citaRepository.findAll(pagination);
        	}
        	return citaRepository.findAllByCodCita(pagination, searchCodigo);
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
	}

	@Override
	@Transactional
	public boolean borrarCita(int idCita) {
		try {
			log.info("Borrando cita");
			
			citaRepository.deleteById(idCita);
			
			return true;
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se borra la cita: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public Optional<Cita> buscarCita(int idCita) {
		
		try {
			log.info("buscando cita");
			
			return citaRepository.findById(idCita);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se busca una cita: " + ex.getMessage());
			ex.printStackTrace();
			
			return null;

		}
	}
	
	@Override
	@Transactional
	public void editarCita(CitaForm citaForm) {
		try {
			
			Optional<Cliente> cliente = clienteRepository.findById(citaForm.getClienteOptionId());
			Optional<Medico> medico = medicoRepository.findById(citaForm.getPersonaAsignadaOptionId());
			Optional<Diagnostico> diagnostico = diagnosticoRepository.findById(citaForm.getDiagnosticoOptionId());
			
			Cita cita = new Cita(citaForm.getId(),citaForm.getCodCita(),cliente.get(), 
					citaForm.getFecha(),medico.get(), diagnostico.get());
			
			log.info("editando cita");
			
			citaRepository.save(cita);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se edita el cliente: " + ex.getMessage());
			ex.printStackTrace();
		}
		
	}

	@Override
	@Transactional
	public Page<Cita> listarCita(int pagina) {
		Pageable pagination = PageRequest.of(pagina - 1, pageSize);      
		
        try {
        	log.info("Devolviendo listado de citas...");
        	return citaRepository.findAll(pagination);

        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
	}

	@Override
	@Transactional
	public List<Cita> buscarCitas() {
        try {
        	log.info("Devolviendo listado de citas...");
        	return citaRepository.findAll();

        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
	}



}
