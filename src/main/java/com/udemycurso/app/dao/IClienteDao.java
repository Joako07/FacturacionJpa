package com.udemycurso.app.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.udemycurso.app.eptity.Cliente;

public interface IClienteDao extends PagingAndSortingRepository<Cliente, Long> {

}
