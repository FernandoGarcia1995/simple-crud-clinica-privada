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

import com.clinicadental.clinicadental.form.DiagnosticoForm;
import com.clinicadental.clinicadental.service.DiagnosticoService;

@Controller
@RequestMapping("/diagnostico")
public class DiagnosticoController {
	
	
	private DiagnosticoService diagnosticoService;
	
	@Autowired
	public DiagnosticoController(DiagnosticoService diagnosticoService) {
		this.diagnosticoService = diagnosticoService;
	}

	@GetMapping("/alta-diagnostico")
	public String altaDiagnostico(@ModelAttribute("diagnostico") DiagnosticoForm diagnostico,Model model) {
		model.addAttribute("diagnostico",diagnostico);
		return "/diagnostico/alta-diagnostico";
	}
	
	@PostMapping("/alta-diagnostico")
	public String altaDiagnostico(@ModelAttribute("diagnostico") @Valid DiagnosticoForm diagnostico ,BindingResult result , Model model) {
		
		if(diagnosticoService.comprobarCodigo(diagnostico.getCodDiagnostico())) {
			result.addError( new FieldError("diagnostico", "codDiagnostico", "Ya existe este codigo en la BBDD."));		
		}
		
		if(result.hasErrors()) {
			model.addAttribute("diagnostico",diagnostico);
			return "/diagnostico/alta-diagnostico"; 
		}
		
		diagnosticoService.guardarDiagnostico(diagnostico);
		
		return "redirect:/diagnostico/alta-diagnostico?success";
	}


}
