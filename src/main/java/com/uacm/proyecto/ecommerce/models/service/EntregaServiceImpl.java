package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uacm.proyecto.ecommerce.models.dao.IEntregaDao;
import com.uacm.proyecto.ecommerce.models.entity.Entrega;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;

@Service
public class EntregaServiceImpl implements IEntregaService {

	@Autowired
	IEntregaDao entregaDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Entrega> obtenerTodos() {
		return (List<Entrega>) entregaDao.findAll();
	}

	@Transactional
	@Override
	public Entrega guardar(Entrega entrega) {
		return entregaDao.save(entrega);
	}

	@Transactional(readOnly=true)
	@Override
	public Entrega buscar(Long id) {
		return entregaDao.findById(id).orElse(null);
	}

	@Transactional
	@Override
	public void eliminar(Entrega entrega) {
		entregaDao.delete(entrega);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Entrega> obtenerTodosPedido(Pedido pedido) {
		return entregaDao.findByPedido(pedido);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Entrega> obtenerHistorialPedido(Pedido pedido) {
		return entregaDao.findByPedido2(pedido);
	}
	
	
}
