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

import com.clinicadental.clinicadental.entity.Cliente;
import com.clinicadental.clinicadental.form.ClienteForm;
import com.clinicadental.clinicadental.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService{
	
	private static final Logger log = LogManager.getLogger(ClienteServiceImpl.class);
	
	private ClienteRepository clienteRepository;
	
	private final int pageSize = 10;
	
	
	@Autowired
	public ClienteServiceImpl(ClienteRepository clienteRepository) {
		this.clienteRepository = clienteRepository;
	}

	@Override
	@Transactional
	public Page<Cliente> listarClientes(int pagina) {		
		
        try {
        	Pageable pagination = PageRequest.of(pagina - 1, pageSize);
        	log.info("Devolviendo lista de clientes...");
        
        	return clienteRepository.findAll(pagination);
        	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
		
	}

	@Override
	@Transactional
	public void guardarCliente(ClienteForm clienteForm) {
		
		Cliente cliente = new Cliente(clienteForm.getNombre(), 
				clienteForm.getApellidos(), clienteForm.getDni(), clienteForm.getCodPostal(), clienteForm.getCiudad());

        try {
        	log.info("Guardando Cliente...");
        	clienteRepository.save(cliente);
        	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras guardando el cliente: " + ex.getMessage());
            ex.printStackTrace();

        }
		
	}
	
	@Override
	@Transactional
	public Page<Cliente> listarPorDni(int pagina,String searchString) {
		Pageable pagination = PageRequest.of(pagina - 1, pageSize);      
		
        try {
        	log.info("Devolviendo lista de clientes...");
        	if(searchString.isEmpty()) {
        		System.out.println("test");
        		return clienteRepository.findAll(pagination);
        	} else {
        		System.out.println("test2");
        		return clienteRepository.findAllByDni(pagination, searchString);
        	}
        	    	
        } catch (Exception ex) {
            log.error("Un error ha ocurrido mientras se realiza la operacion: " + ex.getMessage());
            ex.printStackTrace();
            
            return null;
        }
		
	}

	@Override
	@Transactional
	public boolean comprobarDni(String dni) {
		try {
			log.info("Comprobando Dni del cliente..");
			return clienteRepository.existsByDni(dni);
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras al comprobar el dni del cliente: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
		
	}

	@Override
	@Transactional
	public List<Cliente> buscarClientes() {
		try {
			log.info("buscando clientes..");		
			return clienteRepository.findAll();
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se buscaban las especialidades: " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	@Transactional
	public boolean borrarCliente(int idCliente) {
		try {
			log.info("Borrando cliente");
			
			clienteRepository.deleteById(idCliente);
			
			return true;
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se borra el cliente: " + ex.getMessage());
			ex.printStackTrace();
			
			return false;
		}
	}

	@Override
	@Transactional
	public Optional<Cliente> buscarCliente(int idCliente) {
		try {
			log.info("buscando cliente");
			
			return clienteRepository.findById(idCliente);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se busca un cliente: " + ex.getMessage());
			ex.printStackTrace();
			
			return null;
		}
	}

	@Override
	@Transactional
	public void editarCliente(ClienteForm clienteForm) {
		try {
			
			Cliente cliente = new Cliente(clienteForm.getId(),clienteForm.getNombre(), 
					clienteForm.getApellidos(), clienteForm.getDni(), clienteForm.getCodPostal(), clienteForm.getCiudad());
			
			log.info("editando cliente");
			
			clienteRepository.save(cliente);
			
		} catch (Exception ex) {
			log.error("Un error ha ocurrido mientras se edita el cliente: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
	
	
}
