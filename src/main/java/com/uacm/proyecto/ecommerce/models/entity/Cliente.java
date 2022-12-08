package com.uacm.proyecto.ecommerce.models.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.uacm.proyecto.ecommerce.models.entity.util.Usuario;

@Entity
@Table(name="clientes")
public class Cliente extends Usuario implements Serializable{
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos;
	
	@PrePersist
	public void prePersist() {
		this.setFechaRegistro(new Date());
	}
	
	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos) {
		this.pedidos = pedidos;
	}
	
	@Override
	public String toString() {
		return "Cliente [pedidos=" + pedidos + "]";
	}

	private static final long serialVersionUID = 8032272682310237547L;
}
