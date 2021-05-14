package com.sistemasactivos.pam.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sistemasactivos.pam.models.entity.Persona;
import com.sistemasactivos.pam.models.service.IPersonaService;

@Controller
@RequestMapping("views/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService personaService;

	@GetMapping("/")
	public String listarPersonas(Model model) {
		
		List<Persona> listadoPersonas = personaService.listarTodos();	
		model.addAttribute("titulo","Listado de personas");
		model.addAttribute("personas",listadoPersonas);
		
		return "/views/personas/listar";
				
	}	
	
	@GetMapping("/nuevo")
	public String crear(Model model) {
				
		Persona persona = new Persona();
		model.addAttribute("titulo","Formulario: Nueva Persona");
		model.addAttribute("persona",persona);
		
		
		return "/views/personas/nuevo";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Persona persona, Model model, RedirectAttributes attribute, BindingResult result){
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario: Nueva Persona");
			model.addAttribute("persona",persona);
			return "redirect:/views/personas/nuevo";
		}
		
		personaService.guardar(persona);
		attribute.addFlashAttribute("success","Persona agregada con exito!!");
		
		return "redirect:/views/personas/";

	}
	

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model,RedirectAttributes attribute ){
		
		Persona persona = null;
		
		if(id> 0) {
			persona = personaService.buscarPorId(id);
			
			if(persona == null) {
				attribute.addFlashAttribute("error","Atención: error con el id de persona");
				return "redirect:/views/personas";
			}
		}
		else {
			attribute.addFlashAttribute("error","Atención: error con el id de persona");
			return "redirect:/views/personas";
		}
		
		model.addAttribute("titulo","Formulario: Editar persona");
		model.addAttribute("persona", persona);
		attribute.addFlashAttribute("success","Persona fue editada con éxito!!");
		
		
		return "/views/personas/nuevo";
		
		
	}
	@GetMapping("/eliminar/{id}")	
	public String eliminar(@PathVariable("id") Integer id ,RedirectAttributes attribute) {
		
		Persona persona = null;
		
		if(id> 0) {
			persona = personaService.buscarPorId(id);
			
			if(persona == null) {
				attribute.addFlashAttribute("error","Atención: la persona indicada no existe!");
				return "redirect:/views/personas/";
			}
		}
		else {
			attribute.addFlashAttribute("error","Atención: error con el id de persona");
			return "redirect:/views/personas/";
		}
		
		personaService.eliminar(id);
		attribute.addFlashAttribute("warning","La persona fue eliminada de la base de datos.");
		return "redirect:/views/personas/";		
		
	}
	
	
	
	
	
	
	
}
