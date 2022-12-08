package com.uacm.proyecto.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uacm.proyecto.ecommerce.models.entity.Pago;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;
import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;
import com.uacm.proyecto.ecommerce.models.entity.Producto;
import com.uacm.proyecto.ecommerce.models.service.IPagoService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoProductoService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoService;
import com.uacm.proyecto.ecommerce.models.service.IProductoService;

@Controller
@RequestMapping("/pago")
public class PagoController {
	@Autowired
	IPagoService servicio;
	@Autowired
	IPedidoService servicioPedido;
	@Autowired
	IPedidoProductoService pedidoProductoService;
	@Autowired
	IProductoService servicioProducto;
	
	@GetMapping("/listar")
	public String listar(Model model) {	
		List<Pago> lista = servicio.obtenerTodos();
		String nTarjeta;
		for (Pago p: lista) {
			nTarjeta = p.getNumTarjeta();
			p.setTarjeta("****" + nTarjeta.substring(nTarjeta.length() - 4, nTarjeta.length()));
		}
		model.addAttribute("listaPagos", lista);
		return "pago/pagos";
	}
	
	@GetMapping("/crear/{id}")
	public String crear(@PathVariable(value="id") Long id, Model model) {
		Pedido pe = servicioPedido.buscar(id);
		List<PedidoProducto> lista = pe.getPedidosProductos();
		Pago p = new Pago();
		model.addAttribute("pago", p);
		model.addAttribute("lista", lista);
		model.addAttribute("pedido", pe);
		return "pago/pagoForm";
	}
	
	@PostMapping("/crear/{id}")
	public String guardar(@PathVariable(value="id") Long id, Pago pago, Model model) {
		Pedido pedido = servicioPedido.buscar(id);
		pedido.setEstado(1);
		pago.setPedido(pedido);
		Producto p;
		for (PedidoProducto pp: pedido.getPedidosProductos()) {
			p = pp.getProducto();
			p.setNumPiezas(p.getNumPiezas() - pp.getCantidadPiezas());
			if (p.getNumPiezas() <= 0) {
				p.setEstado(2);
			}
			servicioProducto.guardar(p);
		}
		servicioPedido.guardar(pedido);
		servicio.guardar(pago);
		return "redirect:/cliente/pedidos/" + pedido.getCliente().getId();
	}
}
