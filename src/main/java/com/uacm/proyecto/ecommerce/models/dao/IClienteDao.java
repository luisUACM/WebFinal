package com.uacm.proyecto.ecommerce.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.uacm.proyecto.ecommerce.models.entity.Cliente;

public interface IClienteDao extends CrudRepository<Cliente, Long> {

}
