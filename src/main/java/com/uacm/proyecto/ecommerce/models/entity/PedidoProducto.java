package com.uacm.proyecto.ecommerce.models.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos_productos")
public class PedidoProducto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "subtotal_pagar")
	private Double subtotalPagar;
	@Column(name = "cantidad_piezas")
	private Integer cantidadPiezas;
	@Column(name = "descuento_porcenteje")
	private Double descuentoPorcentaje;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pedido pedido;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Producto producto;

	public PedidoProducto() {
		descuentoPorcentaje = 0.0;
	}

	@PrePersist
	public void prePersist() {
		Double subTotal = producto.getPrecio() * cantidadPiezas;
		this.setSubtotalPagar(subTotal - (subTotal * descuentoPorcentaje));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSubtotalPagar() {
		return subtotalPagar;
	}

	public void setSubtotalPagar(Double subtotalPagar) {
		this.subtotalPagar = subtotalPagar;
	}

	public Integer getCantidadPiezas() {
		return cantidadPiezas;
	}

	public void setCantidadPiezas(Integer cantidadPiezas) {
		this.cantidadPiezas = cantidadPiezas;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public Double getDescuentoPorcentaje() {
		return descuentoPorcentaje;
	}

	public void setDescuentoPorcentaje(Double descuentoPorcentaje) {
		this.descuentoPorcentaje = descuentoPorcentaje;
	}

	@Override
	public String toString() {
		return "PedidoProducto [id=" + id + ", subtotalPagar=" + subtotalPagar + ", cantidadPiezas=" + cantidadPiezas
				+ ", descuentoPorcentaje=" + descuentoPorcentaje + ", pedido=" + pedido + ", producto=" + producto
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PedidoProducto other = (PedidoProducto) obj;
		return Objects.equals(id, other.id);
	}

	private static final long serialVersionUID = -2236747638003490730L;
}
