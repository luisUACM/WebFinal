package com.uacm.proyecto.ecommerce.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uacm.proyecto.ecommerce.models.dao.IPagoDao;
import com.uacm.proyecto.ecommerce.models.entity.Pago;

@Service
public class PagoServiceImpl implements IPagoService {

	@Autowired
	IPagoDao pagoDao;
	
	@Transactional(readOnly=true)
	@Override
	public List<Pago> obtenerTodos() {
		return (List<Pago>)pagoDao.findAll();
	}

	@Transactional
	@Override
	public Pago guardar(Pago pago) {
		return pagoDao.save(pago);
	}

	@Transactional(readOnly=true)
	@Override
	public Pago buscar(Long id) {
		return pagoDao.findById(id).orElse(null);
	}

}
