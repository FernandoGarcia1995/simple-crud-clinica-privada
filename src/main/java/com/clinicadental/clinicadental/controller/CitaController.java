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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.clinicadental.clinicadental.entity.Cita;
import com.clinicadental.clinicadental.entity.Cliente;
import com.clinicadental.clinicadental.entity.Diagnostico;
import com.clinicadental.clinicadental.entity.Medico;
import com.clinicadental.clinicadental.form.CitaForm;
import com.clinicadental.clinicadental.response.ResponseCita;
import com.clinicadental.clinicadental.service.CitaService;
import com.clinicadental.clinicadental.service.ClienteService;
import com.clinicadental.clinicadental.service.DiagnosticoService;
import com.clinicadental.clinicadental.service.MedicoService;


@Controller
@RequestMapping("/cita")
public class CitaController {
	
	private CitaService citaService;
	private ClienteService clienteService;
	private DiagnosticoService diagnosticoService;
	private MedicoService medicoService;
	
	
	@Autowired
	public CitaController(CitaService citaService, ClienteService clienteService, DiagnosticoService diagnosticoService,
			MedicoService medicoService) {
		this.citaService = citaService;
		this.clienteService = clienteService;
		this.diagnosticoService = diagnosticoService;
		this.medicoService = medicoService;
	}

	@GetMapping("/listado-citas")
	public String listadoCitas(@RequestParam("page") int numPagina,Model model) {
			
		Page<Cita> citaPage = citaService.listarCita(numPagina);
		List<Cita> citas = citaPage.getContent();

		model.addAttribute("citas",citas);
		model.addAttribute("numberPages",citaPage.getTotalPages());
		model.addAttribute("currentPage",numPagina);

		return "/cita/listado-citas";
	}
	
	@PostMapping("/listado-citas")
	public String listadoCitas(@RequestParam("page") int numPagina,@RequestParam("search") String searchString,Model model) {
		System.out.println("test");
		Page<Cita> citaPage = citaService.listarCitaPorCodigo(numPagina, searchString);
		List<Cita> citas = citaPage.getContent();

		model.addAttribute("citas",citas);
		model.addAttribute("numberPages",citaPage.getTotalPages());
		model.addAttribute("currentPage",numPagina);

		return "/cita/listado-citas :: cita-list-table";

	}
	
	
	@GetMapping("/alta-cita")
	public String altaCita(CitaForm cita,Model model) {
		List<Cliente> clientes = clienteService.buscarClientes();
		List<Diagnostico> diagnosticos = diagnosticoService.buscarDiagnosticos();
		List<Medico> medicos = medicoService.buscarMedicos();
		
		model.addAttribute("medicos",medicos);
		model.addAttribute("clientes",clientes);
		model.addAttribute("diagnosticos",diagnosticos);
		model.addAttribute("cita",cita);
		
		return "/cita/alta-cita";
	}
	
	@PostMapping("/alta-cita")
	public String altaCita(@ModelAttribute("cita") @Valid CitaForm cita ,BindingResult result ,  Model model) {
		
		if(result.hasErrors()) {
			
			List<Cliente> clientes = clienteService.buscarClientes();
			List<Diagnostico> diagnosticos = diagnosticoService.buscarDiagnosticos();
			List<Medico> medicos = medicoService.buscarMedicos();
			
			model.addAttribute("medicos",medicos);
			model.addAttribute("clientes",clientes);
			model.addAttribute("diagnosticos",diagnosticos);
			model.addAttribute("cita",cita);
			return "/cita/alta-cita"; 
		}
		
		citaService.guardarCita(cita);
	
		return "redirect:/cita/alta-cita?success";
	}
	
	@PostMapping("/eliminar-cita")
	public ResponseEntity<String> borrarCita(@RequestParam("idCita") int cita,Model model) {
		
		if(citaService.borrarCita(cita)) 
			return new ResponseEntity<String>("la cita fue eliminada correctamente: ", HttpStatus.OK);
		else
			return new ResponseEntity<String>("No se ha podido realizar la operacion correctamente: ", HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("/editar-cita")
	public String editarCita(@RequestParam("id") int idCita,Model model) {
		Optional<Cita> cita = citaService.buscarCita(idCita);
		List<Cliente> clientes = clienteService.buscarClientes();
		List<Medico> medicos = medicoService.buscarMedicos();
		List<Diagnostico> diagnosticos = diagnosticoService.buscarDiagnosticos();
		
		model.addAttribute("diagnosticos",diagnosticos);
		model.addAttribute("cita",cita.get());
		model.addAttribute("clientes",clientes);
		model.addAttribute("medicos",medicos);
		
		return "/cita/editar-cita";
	}
	
	@PostMapping("/editar-cita")
	public String editarCita(@ModelAttribute("cita") @Valid CitaForm cita ,BindingResult result , Model model) {
		
		model.addAttribute("cita",cita);
		
		if(result.hasErrors()) {
			return "/cita/editar-cita"; 
		}	
		
		citaService.editarCita(cita);
		
		return "redirect:/cita/editar-cita?id="+cita.getId() +"&success";
	}
	
	
	@PostMapping("/devolver-citas")
	@ResponseBody
	public ResponseCita devolverCitas() {
		List<Cita> citas = citaService.buscarCitas();
		return new ResponseCita(citas,"Operacion realizada correctamente");
	}
	
	@GetMapping("/calendario")
	public String mostrarCalendario() {		
		return "/cita/calendario";
	}

}
