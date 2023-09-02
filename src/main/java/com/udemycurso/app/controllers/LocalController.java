package com.udemycurso.app.controllers;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LocalController {
	
	//Este controlador lo creamos para que no se pierda el parametro de la URL cuando cambiamos de idioma
	
	@GetMapping("locale")
	public String local(HttpServletRequest request) {
		//Obtengo la ùltima URL y la guardo en un String
		String ultimaUrl = request.getHeader("referer");
		
		return "redirect:".concat(ultimaUrl);
	}
	
}
