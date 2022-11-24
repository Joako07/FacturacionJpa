package com.udemycurso.app.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udemycurso.app.entities.Producto;

public interface IProductoDao extends CrudRepository<Producto, Long>{
	
		
	public List<Producto> findByNombreLikeIgnoreCase(String term);
}
