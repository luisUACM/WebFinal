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
@Table(name = "vendedores")
public class Vendedor extends Usuario implements Serializable {

	@OneToMany(mappedBy = "vendedor")
	private List<Producto> productos;

	public Vendedor() {
		super();
	}

	@PrePersist
	public void prePersist() {
		this.setFechaRegistro(new Date());
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public void addProducto(Producto producto){
		productos.add(producto);
	}
	@Override
	public String toString() {
		return "Vendedor [productos=" + productos + ", toString()=" + super.toString() + "]";
	}

	private static final long serialVersionUID = 4690608903214977015L;
}
