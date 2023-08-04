package com.udemycurso.app.controllers;

import java.util.Collection;
import java.util.Map;

import javax.validation.Valid;

import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
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

	protected final Log logger = (Log) LogFactory.getLog(this.getClass());

	@Autowired
	private IClienteService clienteService;

	// Ver detalle de cliente
	@GetMapping(value = "/ver/{id}")
	public String ver(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {

		Cliente cliente = clienteService.findOne(id);
		if (cliente == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Detalle cliente: " + cliente.getNombre());

		return "ver";
	}

	// Paginador
	@GetMapping(value = { "/listar", "/" })
	public String listar(@RequestParam(name = "page", defaultValue = "0") int page, Model model,
			Authentication authentication) {

		// Obtener el nombre usuario uando inyección de dependencia. Inyectando
		// authentication como argumento en el metodo "listar" de arriba

		if (authentication != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(authentication.getName()));
		}

		// Obtener el nombre usuario Utilizando la forma estática con
		// SecurityContextHolder.

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (auth != null) {
			logger.info("Hola usuario autenticado, tu username es: ".concat(auth.getName()));
		}
		
		if(hasRole("ROLE_ADMIN")) {
			logger.info("Hola ".concat(auth.getName()).concat(" tiene acceso!"));
		}else {
			logger.info("Hola ".concat(auth.getName()).concat(" NO tiene acceso!"));
		}

		Pageable pageRequest = PageRequest.of(page, 5);

		Page<Cliente> clientes = clienteService.findAll(pageRequest);

		// Creo el paginador
		PageRender<Cliente> pageRender = new PageRender<>("/listar", clientes);

		// Paso todo a las vista
		model.addAttribute("titulo", "listado de clientes");
		model.addAttribute("clientes", clientes);
		model.addAttribute("page", pageRender);
		return "listar";
	}

	// Formulario
	@RequestMapping(value = "/form")
	// Paso los datos usando Map en vez de Model
	public String crear(Map<String, Object> model) {
		Cliente cliente = new Cliente();
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form/{id}")
	// @PathVariable inyecta un valor
	public String editar(@PathVariable(value = "id") Long id, Map<String, Object> model, RedirectAttributes flash) {
		Cliente cliente = null;

		if (id > 0) {
			cliente = clienteService.findOne(id);
			if (cliente == null) {
				flash.addFlashAttribute("error", "El Id del cliente no existe en la base de datos");
				return "redirect:/listar";
			}
		} else {
			flash.addFlashAttribute("error", "El Id del cliente no puede ser cero!");
			return "redirect:/listar";
		}
		model.put("cliente", cliente);
		model.put("titulo", "Formulario de Cliente");
		return "form";
	}

	@RequestMapping(value = "/form", method = RequestMethod.POST)
	public String guardar(@Valid Cliente cliente, BindingResult result, Model model, RedirectAttributes flash) {
		if (result.hasErrors()) {
			model.addAttribute("titulo", "Formulario de Cliente");
			return "form";
		}

		String mensajeFlas = (cliente.getId() != null) ? "¡Cliente editado con éxito!" : "¡Cliente creado con éxito!";

		clienteService.saved(cliente);
		flash.addFlashAttribute("success", mensajeFlas);
		return "redirect:listar";
	}

	@RequestMapping(value = "/eliminar/{id}")
	public String eliminar(@PathVariable(value = "id") Long id, RedirectAttributes flash) {
		if (id > 0) {
			clienteService.delete(id);
			flash.addFlashAttribute("success", "Cliente creado con éxito!!");
		}
		return "redirect:/listar";
	}

	// Valido si el usuario pertenece a algún roll
	private boolean hasRole(String role) {

		SecurityContext context = SecurityContextHolder.getContext();

		if (context == null) {
			return false;
		}

		Authentication auth = context.getAuthentication();

		if (auth == null) {
			return false;
		}

		Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();

		for (GrantedAuthority authority : authorities) {
			if (role.equals(authority.getAuthority())) {
				logger.info("Hola usuario ".concat(auth.getName()).concat(" tu role es: ".concat(authority.getAuthority())));
				return true;
			}
		}
		return false;
	}

}
