package com.uacm.proyecto.ecommerce.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uacm.proyecto.ecommerce.models.entity.Cliente;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;
import com.uacm.proyecto.ecommerce.models.service.IClienteService;
import com.uacm.proyecto.ecommerce.models.service.IEntregaService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoService;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

	@Autowired
	IClienteService servicio;
	@Autowired
	IPedidoService servicioPedido;
	@Autowired
	IEntregaService servicioEntrega;
	
	@GetMapping("/listar")
	public String listar(Model model) {	
		model.addAttribute("listaClientes", servicio.obtenerTodos());
		return "cliente/clientes";
	}
	
	@GetMapping("/pedidos/{id}")
	public String listarPedidos(@PathVariable(value="id") Long id, Model model){
		Cliente c = servicio.buscar(id);
		List<Pedido> lista = c.getPedidos();
		model.addAttribute("cliente", c);
		model.addAttribute("listaPedidos", lista);
		return "cliente/cliente-pedidos";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Cliente c = new Cliente();
		model.addAttribute("cliente", c);
		model.addAttribute("esCliente", true);
		model.addAttribute("titulo", "Registrar cliente");
		model.addAttribute("esNuevo", true);
		return "usuarioForm";
	}
	
	@PostMapping("/crear")
	public String guardar(Cliente c) {
		if (c.getId() != null) {
			Cliente cliente = servicio.buscar(c.getId());
			c.setFechaRegistro(cliente.getFechaRegistro());
		}
		servicio.guardar(c);
		return "redirect:/cliente/listar";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Model model){
		Cliente c = servicio.buscar(id);
		servicio.eliminar(c);
		return "redirect:/cliente/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model){
		Cliente c = servicio.buscar(id);
		model.addAttribute("cliente", c);
		model.addAttribute("esCliente", true);
		model.addAttribute("titulo", "Editar cliente");
		model.addAttribute("esNuevo", false);
		return "usuarioForm";
	}
	
}
