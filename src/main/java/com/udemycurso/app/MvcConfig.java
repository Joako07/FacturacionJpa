package com.udemycurso.app;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration 
public class MvcConfig implements WebMvcConfigurer{
	
	//Como argumento le ponemos un registry para registrar el viwcontroller el cual trae la url de la vista
	public void addViewControllers(ViewControllerRegistry registry) {
		//le damos un nombre a la url y con setViwName le paso la vista que quiero cargar
		registry.addViewController("/error_403").setViewName("error_403");
	}

}
