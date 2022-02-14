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

import com.clinicadental.clinicadental.entity.Especialidad;
import com.clinicadental.clinicadental.entity.Medico;
import com.clinicadental.clinicadental.form.MedicoForm;
import com.clinicadental.clinicadental.service.EspecialidadService;
import com.clinicadental.clinicadental.service.MedicoService;

@Controller
@RequestMapping("/medico")
public class MedicoController {
	
	
	private EspecialidadService especialidadService;
	private MedicoService medicoService;
		
	@Autowired
	public MedicoController(EspecialidadService especialidadService, MedicoService medicoService) {
		this.especialidadService = especialidadService;
		this.medicoService = medicoService;
	}

	@GetMapping("/listado-medicos")
	public String listadoClientes(@RequestParam("page") int numPagina,Model model) {

		Page<Medico> medicosPage = medicoService.listarMedicos(numPagina);
				
		List<Medico> medicos = medicosPage.getContent();
		model.addAttribute("medicos",medicos);
		model.addAttribute("numberPages",medicosPage.getTotalPages());
		model.addAttribute("currentPage",numPagina);
		
		return "/medico/listado-medicos";

	}
	
	@PostMapping("/listado-medicos")
	public String listadoClientes(@RequestParam("page") int numPagina,@RequestParam("search") String searchString,Model model) {
		
		Page<Medico> medicosPage = medicoService.listarPorDni(numPagina, searchString);		
		List<Medico> medicos = medicosPage.getContent();

		
		model.addAttribute("medicos",medicos);
		model.addAttribute("numberPages",medicosPage.getTotalPages());
		model.addAttribute("currentPage",numPagina);
		
		return "/medico/listado-medicos :: medico-list-table";

	}


	@GetMapping("/alta-medico")
	public String altaMedico(MedicoForm medico,Model model) {
		
		List<Especialidad> especialidades = especialidadService.buscarEspecialidades();
		
		model.addAttribute("medico",medico);
		model.addAttribute("especialidades",especialidades);
		return "/medico/alta-medico";
	}
	
	@PostMapping("/alta-medico")
	public String altaMedico(@ModelAttribute("medico") @Valid MedicoForm medicoForm ,BindingResult result , Model model) {
		
		if(medicoService.comprobarDni(medicoForm.getDni())) {
			result.addError( new FieldError("medico", "dni", "Ya existe este medico en la BBDD."));		
		}
			
		if(result.hasErrors()) {
			
			List<Especialidad> especialidades = especialidadService.buscarEspecialidades();
			
			model.addAttribute("especialidades",especialidades);
			model.addAttribute("medico",medicoForm);
			
			return "/medico/alta-medico"; 
		}
		
		medicoService.guardarMedico(medicoForm);
		
		return "redirect:/medico/alta-medico?success";
	}
	
	@PostMapping("/eliminar-medico")
	public ResponseEntity<String> borrarCliente(@RequestParam("idMedico") int medico,Model model) {
		
		if(medicoService.borrarMedico(medico)) 
			return new ResponseEntity<String>("Medico eliminado correctamente: ", HttpStatus.OK);
		else
			return new ResponseEntity<String>("No se ha podido realizar la operacion correctamente: ", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/editar-medico")
	public String editarMedico(@RequestParam("id") int idMedico,Model model) {
		Optional<Medico> medico = medicoService.buscarMedico(idMedico);
		List<Especialidad> especialidades = especialidadService.buscarEspecialidades();
		
		model.addAttribute("medico",medico.get());				
		model.addAttribute("especialidades",especialidades);
		
		return "/medico/editar-medico";
	}
	
	@PostMapping("/editar-medico")
	public String editarMedico(@ModelAttribute("idMedico") @Valid MedicoForm medico ,BindingResult result , Model model) {
		
		model.addAttribute("medico",medico);
		
		if(result.hasErrors()) {
			return "/medico/editar-medico"; 
		}	
		
		medicoService.editarMedico(medico);
		
		return "redirect:/medico/editar-medico?id=" + medico.getId() +"&success";
	}
	

}
