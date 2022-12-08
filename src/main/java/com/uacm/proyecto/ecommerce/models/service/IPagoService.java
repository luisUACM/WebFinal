package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import com.uacm.proyecto.ecommerce.models.entity.Pago;

public interface IPagoService {
	public List<Pago> obtenerTodos();
	public Pago guardar(Pago pago);
	public Pago buscar(Long id);
}
