package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uacm.proyecto.ecommerce.models.dao.IClienteDao;
import com.uacm.proyecto.ecommerce.models.entity.Cliente;

@Service
public class ClienteServiceImpl implements IClienteService {

	@Autowired
	IClienteDao clienteDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Cliente> obtenerTodos() {
		return (List<Cliente>)clienteDao.findAll();
	}

	@Transactional
	@Override
	public Cliente guardar(Cliente cliente) {
		return clienteDao.save(cliente);
	}

	@Transactional(readOnly=true)
	@Override
	public Cliente buscar(Long id) {
		return clienteDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void eliminar(Cliente cliente) {
		clienteDao.delete(cliente);
	}

}
