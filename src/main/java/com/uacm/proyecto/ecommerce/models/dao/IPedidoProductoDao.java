package com.uacm.proyecto.ecommerce.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;

public interface IPedidoProductoDao extends CrudRepository<PedidoProducto, Long> {
	
}
