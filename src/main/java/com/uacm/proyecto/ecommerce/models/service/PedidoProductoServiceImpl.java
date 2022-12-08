package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uacm.proyecto.ecommerce.models.dao.IPedidoProductoDao;
import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;

@Service
public class PedidoProductoServiceImpl implements IPedidoProductoService {

	@Autowired
	IPedidoProductoDao pedidoProductoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<PedidoProducto> obtenerTodos() {
		return (List<PedidoProducto>)pedidoProductoDao.findAll();
	}

	@Transactional
	@Override
	public PedidoProducto guardar(PedidoProducto pedidoProducto) {
		return pedidoProductoDao.save(pedidoProducto);
	}

	@Transactional(readOnly=true)
	@Override
	public PedidoProducto buscar(Long id) {
		return pedidoProductoDao.findById(id).orElse(null);
	}

	/*@Transactional(readOnly=true)
	@Override
	public List<PedidoProducto> obtenerTodos(Long id){
		return pedidoProductoDao.obtenerTodos(id);
	}*/
}
