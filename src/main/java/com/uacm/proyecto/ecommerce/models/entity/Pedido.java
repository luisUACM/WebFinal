package com.uacm.proyecto.ecommerce.models.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name = "pedidos")
public class Pedido implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fecha_pedido")
	private Date fechaPedido;
	@Column(name = "hora_pedido")
	private Time horaPedido;
	private Integer estado;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Cliente cliente;
	@Column(name = "total_pagar")
	private Double totalPagar;
	@Column(name = "info_adicional")
	private String infoAdicional;
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Pago pago;
	@OneToOne(mappedBy = "pedido", cascade = CascadeType.ALL)
	private Entrega entrega;
	@OneToMany(mappedBy = "pedido", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<PedidoProducto> pedidosProductos;
	@OneToMany(mappedBy = "pedido2")
	private List<Entrega> historialEntregas;

	@PrePersist
	public void prePersist() {
		this.setFechaPedido(new Date());
		this.setHoraPedido(Time.valueOf(LocalTime.now()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPedido() {
		return fechaPedido;
	}

	public void setFechaPedido(Date fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

	public Time getHoraPedido() {
		return horaPedido;
	}

	public void setHoraPedido(Time horaPedido) {
		this.horaPedido = horaPedido;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Double getTotalPagar() {
		return totalPagar;
	}

	public void setTotalPagar(Double totalPagar) {
		this.totalPagar = totalPagar;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public Pago getPago() {
		return pago;
	}

	public void setPago(Pago pago) {
		this.pago = pago;
	}

	public Entrega getEntrega() {
		return entrega;
	}

	public void setEntrega(Entrega entrega) {
		this.entrega = entrega;
	}

	public List<PedidoProducto> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(List<PedidoProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	public List<Entrega> getHistorialEntregas() {
		return historialEntregas;
	}

	public void setHistorialEntregas(List<Entrega> historialEntregas) {
		this.historialEntregas = historialEntregas;
	}
	
	public void addHistorialEntregas(Entrega entrega) {
		this.historialEntregas.add(entrega);
	}

	@Override
	public String toString() {
		return "Pedido [id=" + id + ", fechaPedido=" + fechaPedido + ", horaPedido=" + horaPedido + ", estado=" + estado
				+ ", cliente=" + cliente.getNombre() + ", totalPagar=" + totalPagar + ", infoAdicional=" + infoAdicional
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
		Pedido other = (Pedido) obj;
		return Objects.equals(id, other.id);
	}

	private static final long serialVersionUID = 1686859583247783964L;
}
