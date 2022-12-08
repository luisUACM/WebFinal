package com.uacm.proyecto.ecommerce.models.entity;

import java.io.Serializable;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "pagos")
public class Pago implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "fecha_pago")
	private Date fechaPago;
	@Column(name = "hora_pago")
	private Time horaPago;
	@Column(name = "info_adicional")
	private String infoAdicional;
	@OneToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	private String numTarjeta;
	@Transient
	private Integer cvv;
	private String nombre;
	@Transient
	private String tarjeta;

	@PrePersist
	public void prePersist() {
		this.setFechaPago(new Date());
		this.setHoraPago(Time.valueOf(LocalTime.now()));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaPago() {
		return fechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		this.fechaPago = fechaPago;
	}

	public Time getHoraPago() {
		return horaPago;
	}

	public void setHoraPago(Time horaPago) {
		this.horaPago = horaPago;
	}

	public String getInfoAdicional() {
		return infoAdicional;
	}

	public void setInfoAdicional(String infoAdicional) {
		this.infoAdicional = infoAdicional;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getNumTarjeta() {
		return numTarjeta;
	}

	public void setNumTarjeta(String numTarjeta) {
		this.numTarjeta = numTarjeta;
	}

	public Integer getCvv() {
		return cvv;
	}

	public void setCvv(Integer cvv) {
		this.cvv = cvv;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTarjeta() {
		return tarjeta;
	}

	public void setTarjeta(String tarjeta) {
		this.tarjeta = tarjeta;
	}

	@Override
	public String toString() {
		return "Pago [id=" + id + ", fechaPago=" + fechaPago + ", horaPago=" + horaPago + ", infoAdicional="
				+ infoAdicional + ", pedido=" + pedido + "]";
	}

	private static final long serialVersionUID = 5041500652065164763L;

}
