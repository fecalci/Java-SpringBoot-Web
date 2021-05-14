package com.sistemasactivos.pam.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
import com.sistemasactivos.pam.models.service.ITransaccionesService;

import com.sistemasactivos.pam.models.entity.Transacciones;

@Controller
@RequestMapping("views/Transacciones")
public class TransaccionesController {
	
	@Autowired
	private IPersonaService personaService;
	
	@Autowired
	private ITransaccionesService TransaccionesService;

	@GetMapping("/")
	public String listarTransacciones(Model model) {
		
		/*
		List<Transacciones> listadoTransacciones = TransaccionesService.listarTodos();	
		model.addAttribute("titulo","Listado de transacciones");
		model.addAttribute("Transacciones",listadoTransacciones);
		*/
		
		return paginacion(1,model);
		
		
				
	}	
	
	@GetMapping("/{paginaNum}")
	public String paginacion(@PathVariable("paginaNum") Integer paginaNum, Model model) {
		
		int paginaTot = 5;
		
		Page<Transacciones> page = TransaccionesService.paginacion(paginaNum, paginaTot);
		List<Transacciones> listaTransacciones = page.getContent();


		model.addAttribute("paginaActual",paginaNum);
		model.addAttribute("totalPaginas",page.getTotalPages());	
		model.addAttribute("totalItems",page.getTotalElements());
		model.addAttribute("Transacciones",listaTransacciones);
		
		return "/views/Transacciones/listar";
		
		
	}
	
	
	@GetMapping("/nuevo")
	public String crear(Model model) {
				
		Transacciones Transacciones = new Transacciones();
		List<Persona> listaPersonas = personaService.listarTodos();
		
		model.addAttribute("titulo","Formulario: Nueva Transaccion");
		model.addAttribute("Transacciones",Transacciones);
		model.addAttribute("personas", listaPersonas);
		
		
		return "/views/Transacciones/nuevo";
	}
	
	@PostMapping("/guardar")
	public String guardar(@ModelAttribute Transacciones Transacciones, Model model, RedirectAttributes attribute, BindingResult result){
		
		List<Persona> listaPersonas = personaService.listarTodos();
		
		if(result.hasErrors()) {
			model.addAttribute("titulo","Formulario: Nueva Transaccion");
			model.addAttribute("Transacciones",Transacciones);
			model.addAttribute("personas", listaPersonas);
			return "redirect:/views/Transacciones/nuevo";
		}
		
		TransaccionesService.guardar(Transacciones);
		attribute.addFlashAttribute("success","Transacción agregada con exito!!");		
		return "redirect:/views/Transacciones/";

	}
	

	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") Integer id, Model model,RedirectAttributes attribute ){
		
		Transacciones Transacciones = null;
		
		if(id> 0) {
			Transacciones = TransaccionesService.buscarPorId(id);
			
			if(Transacciones == null) {
				attribute.addFlashAttribute("error","Atención: error con el id de transacción");
				return "redirect:/views/Transacciones";
			}			
		}
		else {
			attribute.addFlashAttribute("error","Atención: error con el id de transacción");
			return "redirect:/views/Transacciones";
		}
		
		List<Persona> listaPersonas = personaService.listarTodos();
		
		model.addAttribute("titulo","Formulario: Editar transacción");
		model.addAttribute("Transacciones", Transacciones);
		model.addAttribute("personas", listaPersonas);
		attribute.addFlashAttribute("success","Transacción fue editada con éxito!!");
		
		
		return "/views/Transacciones/nuevo";
		
		
	}
	@GetMapping("/eliminar/{id}")	
	public String eliminar(@PathVariable("id") Integer id ,RedirectAttributes attribute) {
		
		Transacciones Transacciones = null;
		
		if(id> 0) {
			Transacciones = TransaccionesService.buscarPorId(id);
			
			if(Transacciones == null) {
				attribute.addFlashAttribute("error","Atención: la transacción indicada no existe!");
				return "redirect:/views/Transacciones/";
			}
		}
		else {
			attribute.addFlashAttribute("error","Atención: error con el id de transacción");
			return "redirect:/views/Transacciones/";
		}
		
		TransaccionesService.eliminar(id);
		attribute.addFlashAttribute("warning","La transacción fue eliminada de la base de datos.");
		return "redirect:/views/Transacciones/";		
		
	}
	
	
	
	
	
	
	
}

