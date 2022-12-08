package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;

public interface IPedidoProductoService {
	public List<PedidoProducto> obtenerTodos();
	public PedidoProducto guardar(PedidoProducto pedidoProducto);
	public PedidoProducto buscar(Long id);
	//public List<PedidoProducto> obtenerTodos(Long id);
}
