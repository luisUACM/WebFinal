package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uacm.proyecto.ecommerce.models.entity.Vendedor;

public interface IVendedorService {
	public List<Vendedor> obtenerTodos();
	public Vendedor guardar(Vendedor vendedor);
	public Vendedor buscar(Long id);
	public Page<Vendedor> obtenerTodosPagina(Pageable pageable);
	public void eliminar(Vendedor vendedor);
}
