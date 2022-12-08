package com.uacm.proyecto.ecommerce.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import com.uacm.proyecto.ecommerce.models.entity.Entrega;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;
import com.uacm.proyecto.ecommerce.models.service.IEntregaService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoService;

@Controller
@RequestMapping("/entrega")
public class EntregaController {
	@Autowired
	IEntregaService servicio;
	@Autowired
	IPedidoService servicioPedido;
	
	@GetMapping("/listar")
	public String listar(Model model) {	
		model.addAttribute("listaEntregas", servicio.obtenerTodos());
		return "entrega/entregas";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		Entrega entrega = servicio.buscar(id);
		if(entrega != null) {
			servicio.eliminar(entrega);
		}
		return "redirect:/entrega/listar";
	}
	
	@GetMapping("/crear/{id}")
	public String crear(@PathVariable(value="id") Long id, Model model) {
		Pedido pe = servicioPedido.buscar(id);
		Entrega e = new Entrega();
		e.setEstado(1);
		model.addAttribute("nuevo", true);
		model.addAttribute("titulo", "Detalles de entrega");
		model.addAttribute("pedido", pe);
		model.addAttribute("entrega", e);
		model.addAttribute("fecha", LocalDate.now().plusDays(1));
		return "entrega/entregaForm";
	}
	
	@PostMapping("/crear/{id}")
	public String guardar(@PathVariable(value="id") Long id, Entrega entrega, SessionStatus status) {
		Pedido p = servicioPedido.buscar(id);
		entrega.setHoraEntrega(LocalTime.parse(entrega.getHora(), DateTimeFormatter.ofPattern("HH:mm")));
		entrega.setPedido(p);
		servicio.guardar(entrega);
		status.setComplete();
		return "redirect:/pago/crear/" + id;
	}
	
	@GetMapping("/reemplazar/{id}")
	public String crearReemplazo(@PathVariable(value="id") Long id, Model model) {
		Pedido pe = servicioPedido.buscar(id);
		Entrega e = pe.getEntrega();
		Entrega e2 = new Entrega();
		e2.setEstado(3);
		e2.setFechaEntrega(e.getFechaEntrega());
		e2.setHoraEntrega(e.getHoraEntrega());
		e2.setIntentos(e.getIntentos());
		e2.setNotasAdicionales(e.getNotasAdicionales());
		e2.setPuntoEntrega(e.getPuntoEntrega());
		e2.setPedido2(pe);
		pe.addHistorialEntregas(e2);
		e.setIntentos(e.getIntentos() - 1 );
		servicioPedido.guardar(pe);
		servicio.guardar(e2);
		model.addAttribute("nuevo", false);
		model.addAttribute("titulo", "Nuevo intento de entrega");
		model.addAttribute("entrega", e);
		model.addAttribute("pedido", pe);
		model.addAttribute("fecha", LocalDate.now().plusDays(1));
		return "entrega/entregaForm";
	}
	
	@PostMapping("/reemplazar/{id}")
	public String reemplazar(@PathVariable(value="id") Long id, Entrega entrega, SessionStatus status) {
		Pedido p = servicioPedido.buscar(id);
		entrega.setHoraEntrega(LocalTime.parse(entrega.getHora(), DateTimeFormatter.ofPattern("HH:mm")));
		entrega.setPedido(p);
		servicio.guardar(entrega);
		status.setComplete();
		return "redirect:/pedido/entregas/" + id;
	}
}
