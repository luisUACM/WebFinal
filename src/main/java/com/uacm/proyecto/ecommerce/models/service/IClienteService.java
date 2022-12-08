package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import com.uacm.proyecto.ecommerce.models.entity.Cliente;

public interface IClienteService {
	public List<Cliente> obtenerTodos();
	public Cliente guardar(Cliente cliente);
	public Cliente buscar(Long id);
	public void eliminar(Cliente cliente);
}
