package com.clinicadental.clinicadental.service;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.clinicadental.clinicadental.entity.Cliente;
import com.clinicadental.clinicadental.form.ClienteForm;

public interface ClienteService {
	
	public Page<Cliente> listarClientes(int pagina);
	
	public Page<Cliente> listarPorDni(int pagina,String searchString);
	
	public Optional<Cliente> buscarCliente(int idCliente);
	
	public List<Cliente> buscarClientes();
	
	public boolean borrarCliente(int idCliente);
	
	public void guardarCliente(ClienteForm cliente);
	
	public void editarCliente(ClienteForm cliente);
	
	public boolean comprobarDni(String dni);
	
}
