package com.udemycurso.app;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration 
public class MvcConfig implements WebMvcConfigurer{
	
	//Como argumento le ponemos un registry para registrar el viwcontroller el cual trae la url de la vista
	public void addViewControllers(ViewControllerRegistry registry) {
		//le damos un nombre a la url y con setViwName le paso la vista que quiero cargar
		registry.addViewController("/error_403").setViewName("error_403");
	}
	
	//La configuración para poder cambiar de lenguaje la pag es los siguientes 2 beans y registrar el interceptor
	//Guarda el objeto Locale con el lenguaje que quiero en la sesión . 
	@Bean 
	public LocaleResolver localeResolver() {
		SessionLocaleResolver localeResolver = new  SessionLocaleResolver();
		localeResolver.setDefaultLocale(new Locale("es", "ES"));
		return localeResolver;
	}
	
	//Creo un interceptor para cambiar el Locale cuando deseo cambiar de lenguaje
	@Bean 
	public LocaleChangeInterceptor localeChangeInterceptor() {
		LocaleChangeInterceptor localeInterceptor = new LocaleChangeInterceptor();
		localeInterceptor.setParamName("lang");
		return localeInterceptor;
	}

	//Registro el interceptor en la aplicación
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(localeChangeInterceptor());
	}

}
