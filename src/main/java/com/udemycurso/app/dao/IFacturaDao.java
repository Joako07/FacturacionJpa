package com.udemycurso.app.dao;

import org.springframework.data.repository.CrudRepository;

import com.udemycurso.app.entities.Factura;

public interface IFacturaDao extends CrudRepository<Factura, Long>{

}
