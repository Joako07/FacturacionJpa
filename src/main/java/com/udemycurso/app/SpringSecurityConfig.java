package com.udemycurso.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.udemycurso.app.auth.handler.LoginSuccesHandler;

@Configuration
public class SpringSecurityConfig  {
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
 
		//Dando seguridad a las rutas. Le asignamos permisos a los diferentes usuarios.
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/images/**", "/listar").permitAll()
				//Estos permisos los podes dar con etiquetas en los controladores agregando el beans @EnabledMethodSecurity en esta clase
				.antMatchers("/ver/**").hasAnyRole("USER")
				.antMatchers("/uploads/**").hasAnyRole("USER")
				.antMatchers("/form/**").hasAnyRole("ADMIN")
				.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
				.antMatchers("/factura/**").hasAnyRole("ADMIN")
				.anyRequest().authenticated()
				.and()
					.formLogin()
						.successHandler(successHandler)
						.loginPage("/login")
					.permitAll()
				.and()
				.logout().permitAll()
				.and()
				.exceptionHandling().accessDeniedPage("/error_403");
 
		return http.build();
	}

	//codigficar la password
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService userDetailsService() throws Exception {

		//Crear usuarios
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(
				User.withUsername("joaquin").password(passwordEncoder().encode("3493")).roles("USER").build());
		manager.createUser(
				User.withUsername("admin").password(passwordEncoder().encode("123456"))
				.roles("ADMIN", "USER").build());

		return manager;
	}

}