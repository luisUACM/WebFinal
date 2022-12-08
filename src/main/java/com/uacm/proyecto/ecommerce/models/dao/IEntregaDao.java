package com.uacm.proyecto.ecommerce.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.uacm.proyecto.ecommerce.models.entity.Entrega;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;

public interface IEntregaDao extends CrudRepository<Entrega, Long> {
	//@Query("SELECT * FROM entregas e, pedidos p WHERE p.id=?1 AND e.id=?1")
	public List<Entrega> findByPedido(Pedido pedido);
	public List<Entrega> findByPedido2(Pedido pedido);
}
