package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import com.uacm.proyecto.ecommerce.models.entity.Entrega;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;

public interface IEntregaService {
	public List<Entrega> obtenerTodos();
	public Entrega guardar(Entrega entrega);
	public Entrega buscar(Long id);
	public void eliminar(Entrega entrega);
	public List<Entrega> obtenerTodosPedido(Pedido pedido);
	public List<Entrega> obtenerHistorialPedido(Pedido pedido);
}
