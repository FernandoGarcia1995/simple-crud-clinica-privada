package com.clinicadental.clinicadental.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.clinicadental.clinicadental.form.EspecialidadForm;
import com.clinicadental.clinicadental.service.EspecialidadService;

@Controller
@RequestMapping("/especialidad")
public class EspecialidadController {
	
	
	private EspecialidadService especialidadService;	
	
	@Autowired
	public EspecialidadController(EspecialidadService especialidadService) {
		this.especialidadService = especialidadService;
	}

	@GetMapping("/alta-especialidad")
	public String altaEspecialidad(EspecialidadForm especialidad, Model model) {
		model.addAttribute("especialidad",especialidad);
		return "/especialidad/alta-especialidad";
	}
	
	@PostMapping("/alta-especialidad")
	public String altaEspecialidad(@ModelAttribute("especialidad") @Valid EspecialidadForm especialidad ,BindingResult result , Model model) {
		
		if(especialidadService.comprobarCodigo(especialidad.getCodEspecialidad())) {
			result.addError( new FieldError("especialidad", "codEspecialidad", "Ya existe este codigo en la BBDD."));		
		}
		
		if(result.hasErrors()) {
			model.addAttribute("especialidad",especialidad);
			return "/especialidad/alta-especialidad"; 
		}
		
		especialidadService.guardarEspecialidad(especialidad);
		
		return "redirect:/especialidad/alta-especialidad?success";
	}

}
