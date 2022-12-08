package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uacm.proyecto.ecommerce.models.dao.IVendedorDao;
import com.uacm.proyecto.ecommerce.models.entity.Vendedor;

@Service
public class VendedorServiceImpl implements IVendedorService{

	@Autowired
	IVendedorDao vendedorDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Vendedor> obtenerTodos() {
		return (List<Vendedor>)vendedorDao.findAll();
	}

	@Transactional
	@Override
	public Vendedor guardar(Vendedor vendedor) {
		return vendedorDao.save(vendedor);
	}

	@Transactional(readOnly=true)
	@Override
	public Vendedor buscar(Long id) {
		return vendedorDao.findById(id).orElse(null);
	}

	@Override
	public Page<Vendedor> obtenerTodosPagina(Pageable pageable) {
		return vendedorDao.findAll(pageable);
	}

	@Transactional
	@Override
	public void eliminar(Vendedor vendedor) {
		vendedorDao.delete(vendedor);
	}
	
	

}
