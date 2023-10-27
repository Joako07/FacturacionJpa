package com.udemycurso.app.json;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.udemycurso.app.entities.Cliente;

@Component("listar.json")
@SuppressWarnings("unchecked") //Sirve para eliminar las advertencias
public class ClienteListJsonView extends MappingJackson2JsonView{

	/*Filtramos lo que queremos serializar 
	Solo queremos al cliente por eso removemos el titula y page 
	que los traemos desde clientControler "listar" Pero a cliente hay que obtenerlo con un getContent(). Por eso lo elimino y lo agrego de vuelta como lista.
	Esto es para que se vea mas prolijo en el Json*/
	@Override
	protected Object filterModel(Map<String, Object> model) {
		
		model.remove("titulo");
		model.remove("page");
		
		Page<Cliente> clientes = (Page<Cliente>) model.get("clientes");
		model.remove("clientes");
		model.put("clientes", clientes.getContent());
				
		return super.filterModel(model);
	}



	
}
