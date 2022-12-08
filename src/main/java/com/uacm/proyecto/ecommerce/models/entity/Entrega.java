package com.uacm.proyecto.ecommerce.models.entity;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "entregas")
public class Entrega implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_entrega")
	private Date fechaEntrega;
	@Column(name = "hora_entrega")
	private LocalTime horaEntrega;
	@Column(name = "punto_entrega")
	private String puntoEntrega;
	private Integer intentos;
	@Column(name = "notas_adicionales")
	private String notasAdicionales;
	private Integer estado;
	@OneToOne
	@JoinColumn(name = "pedido_id")
	private Pedido pedido;
	@Transient
	private String hora;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Pedido pedido2;

	public Entrega() {
		intentos = 3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFechaEntrega() {
		return fechaEntrega;
	}

	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}

	public LocalTime getHoraEntrega() {
		return horaEntrega;
	}

	public void setHoraEntrega(LocalTime horaEntrega) {
		this.horaEntrega = horaEntrega;
	}

	public String getPuntoEntrega() {
		return puntoEntrega;
	}

	public void setPuntoEntrega(String puntoEntrega) {
		this.puntoEntrega = puntoEntrega;
	}

	public Integer getIntentos() {
		return intentos;
	}

	public void setIntentos(Integer intentos) {
		this.intentos = intentos;
	}

	public String getNotasAdicionales() {
		return notasAdicionales;
	}

	public void setNotasAdicionales(String notasAdicionales) {
		this.notasAdicionales = notasAdicionales;
	}

	public Integer getEstado() {
		return estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Pedido getPedido() {
		return pedido;
	}

	public void setPedido(Pedido pedido) {
		this.pedido = pedido;
	}

	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Pedido getPedido2() {
		return pedido2;
	}

	public void setPedido2(Pedido pedido2) {
		this.pedido2 = pedido2;
	}

	@Override
	public String toString() {
		return "Entrega [id=" + id + ", fechaEntrega=" + fechaEntrega + ", horaEntrega=" + horaEntrega
				+ ", puntoEntrega=" + puntoEntrega + ", intentos=" + intentos + ", notasAdicionales=" + notasAdicionales
				+ ", estado=" + estado + ", pedido=" + pedido + "]";
	}

	private static final long serialVersionUID = 5659753591460487071L;
}
