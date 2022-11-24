package com.udemycurso.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.udemycurso.app.entities.Cliente;
import com.udemycurso.app.services.IClienteService;
import com.udemycurso.app.util.paginaitor.PageRender;


@Controller
public class ClienteController {
	
	@Autowired
	private IClienteService clienteService;
	
	//Ver detalle de cliente
	@GetMapping(value="/ver/{id}")
	public String ver(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		
		Cliente cliente = clienteService.findOne(id);
		if(cliente ==null) {
		flash.addFlashAttribute("error", "El cliente no existe en la base de datos");	
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());
		
		return "ver";
	}
	
	//Paginador
	@GetMapping("/listar")
	public String listar(@RequestParam(name="page", defaultValue="0")int page,Model model){
		
		Pageable pageRequest = PageRequest.of(page, 5);
		
		Page<Cliente> clientes = clienteService.findAll(pageRequest);
		
		//Creo el paginador
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);
		
		//Paso todo a las vista
		model.addAttribute("titulo", "listado de clientes");
		model.addAttribute("clientes", clientes); 
		model.addAttribute("page", pageRender);
		return "listar";
	}
	
	//Formulario
	@RequestMapping(value="/form")
	//Paso los datos usando Map en vez de Model
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@RequestMapping(value="/form/{id}")
	//@PathVariable inyecta  un valor
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;
		
		if(id>0){
			cliente= clienteService.findOne(id);
			if(cliente == null) {
				flash.addFlashAttribute("error", "El Id del cliente no existe en la base de datos");
				return "redirect:/listar";
			}
		}else {
			flash.addFlashAttribute("error", "El Id del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}
	
	@RequestMapping(value="/form", method=RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash) {
		if(result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}
		
		String mensajeFlas= (cliente.getId() != null)? "¡Cliente editado con éxito!" : "¡Cliente creado con éxito!";
		
		clienteService.saved(cliente);
		flash.addFlashAttribute("success", mensajeFlas);
		return "redirect:listar";
	}
	
	@RequestMapping(value="/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, RedirectAttributes flash) {
		if(id>0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente creado con éxito!!");
		}
		return "redirect:/listar";
	}
}

