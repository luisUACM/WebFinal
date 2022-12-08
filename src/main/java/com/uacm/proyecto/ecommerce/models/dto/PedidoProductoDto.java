package com.uacm.proyecto.ecommerce.models.dto;

import java.util.ArrayList;
import java.util.List;

import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;

public class PedidoProductoDto {
	private List<PedidoProducto> productos;
	private String info;

	public PedidoProductoDto() {
		productos = new ArrayList<>();
	}

	public void addProducto(PedidoProducto producto) {
		this.productos.add(producto);
	}

	public List<PedidoProducto> getProductos() {
		return productos;
	}

	public void setProductos(List<PedidoProducto> productos) {
		this.productos = productos;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

}
