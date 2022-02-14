package com.clinicadental.clinicadental.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.clinicadental.clinicadental.entity.Cliente;
import com.clinicadental.clinicadental.form.ClienteForm;
import com.clinicadental.clinicadental.service.ClienteService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {
	
	private ClienteService clienteService;
	
	@Autowired
	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}

	@GetMapping("/listado-clientes")
	public String listadoClientes(@RequestParam("page") int numPagina,Model model) {
		
		Page<Cliente> clientesPage = clienteService.listarClientes(numPagina);
		List<Cliente> clientes = clientesPage.getContent();

		model.addAttribute("clientes",clientes);
		model.addAttribute("numberPages",clientesPage.getTotalPages());
		model.addAttribute("currentPage",numPagina);
		
		return "/cliente/listado-clientes";

	}
	
	@PostMapping("/listado-clientes")
	public String listadoClientes(@RequestParam("page") int numPagina,@RequestParam("search") String searchString,Model model) {
		
		System.out.println("test");
	
		Page<Cliente> clientesPage = clienteService.listarPorDni(numPagina,searchString);
		List<Cliente> clientes = clientesPage.getContent();

		model.addAttribute("clientes",clientes);
		model.addAttribute("numberPages",clientesPage.getTotalPages());
		model.addAttribute("currentPage",numPagina);

		return "/cliente/listado-clientes :: client-list-table";
	}

	
	@GetMapping("/alta-cliente")
	public String altaCliente(ClienteForm cliente,Model model) {
		model.addAttribute("cliente",cliente);
		return "/cliente/alta-cliente";
	}
	
	@PostMapping("/alta-cliente")
	public String altaCliente(@ModelAttribute("cliente") @Valid ClienteForm cliente ,BindingResult result , Model model) {
		
		if(clienteService.comprobarDni(cliente.getDni())) {
			result.addError( new FieldError("cliente", "dni", "Ya existe este cliente en la BBDD."));		
		}
		
		if(result.hasErrors()) {
			model.addAttribute("cliente",cliente);
			return "/cliente/alta-cliente"; 
		}
		
		clienteService.guardarCliente(cliente);
		
		return "redirect:/cliente/alta-cliente?success";
	}
	
	
	@PostMapping("/eliminar-cliente")
	public ResponseEntity<String> borrarCliente(@RequestParam("idCliente") int cliente,Model model) {
		
		if(clienteService.borrarCliente(cliente)) 
			return new ResponseEntity<String>("Cliente eliminado correctamente: ", HttpStatus.OK);
		else
			return new ResponseEntity<String>("No se ha podido realizar la operacion correctamente: ", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/editar-cliente")
	public String editarCliente(@RequestParam("id") int idCliente,Model model) {
		Optional<Cliente> cliente = clienteService.buscarCliente(idCliente);
		model.addAttribute("cliente",cliente.get());
		return "/cliente/editar-cliente";
	}
	
	@PostMapping("/editar-cliente")
	public String editarCliente(@ModelAttribute("cliente") @Valid ClienteForm cliente ,BindingResult result , Model model) {
		
		model.addAttribute("cliente",cliente);
		
		if(result.hasErrors()) {
			return "/cliente/alta-cliente"; 
		}	
		
		clienteService.editarCliente(cliente);
		
		return "redirect:/cliente/editar-cliente?id="+cliente.getId() +"&success";
	}
}
