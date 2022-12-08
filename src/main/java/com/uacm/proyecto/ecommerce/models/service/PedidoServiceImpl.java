package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uacm.proyecto.ecommerce.models.dao.IPedidoDao;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;

@Service
public class PedidoServiceImpl implements IPedidoService {

	@Autowired
	IPedidoDao pedidoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Pedido> obtenerTodos() {
		return (List<Pedido>)pedidoDao.findAll();
	}

	@Transactional
	@Override
	public Pedido guardar(Pedido pedido) {
		return pedidoDao.save(pedido);
	}

	@Transactional(readOnly=true)
	@Override
	public Pedido buscar(Long id) {
		return pedidoDao.findById(id).orElse(null);
	}
	
	@Transactional
	@Override
	public void eliminar(Pedido pedido) {
		pedidoDao.delete(pedido);
	}

	@Override
	public Page<Pedido> obtenerTodosPagina(Pageable pageable) {
		return pedidoDao.findAll(pageable);
	}
}
