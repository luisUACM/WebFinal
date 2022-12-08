package com.uacm.proyecto.ecommerce.models.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nombre;
	private String descripcion;
	private Double precio;
	@Column(name = "num_piezas")
	private Integer numPiezas;
	private Integer estado;
	@Column(name = "informacion_adicional")
	private String informacionAdicional;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Vendedor vendedor;
	@OneToMany(mappedBy = "producto")
	private List<PedidoProducto> pedidosProductos;
	@Transient
	private boolean seleccionado;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Double getPrecio() {
		return precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}

	public Integer getNumPiezas() {
		return numPiezas;
	}

	public void setNumPiezas(Integer numPiezas) {
		this.numPiezas = numPiezas;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getInformacionAdicional() {
		return informacionAdicional;
	}

	public void setInformacionAdicional(String informacionAdicional) {
		this.informacionAdicional = informacionAdicional;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public List<PedidoProducto> getPedidosProductos() {
		return pedidosProductos;
	}

	public void setPedidosProductos(List<PedidoProducto> pedidosProductos) {
		this.pedidosProductos = pedidosProductos;
	}

	public boolean isSeleccionado() {
		return seleccionado;
	}

	public void setSeleccionado(boolean seleccionado) {
		this.seleccionado = seleccionado;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio
				+ ", numPiezas=" + numPiezas + ", estado=" + estado + ", informacionAdicional=" + informacionAdicional
				+ ", vendedor=" + vendedor + "]";
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
		Producto other = (Producto) obj;
		return Objects.equals(id, other.id);
	}

	private static final long serialVersionUID = -4580885981955826715L;
}
