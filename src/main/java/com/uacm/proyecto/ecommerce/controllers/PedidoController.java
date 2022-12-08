package com.uacm.proyecto.ecommerce.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import com.uacm.proyecto.ecommerce.models.dto.PedidoProductoDto;
import com.uacm.proyecto.ecommerce.models.entity.Cliente;
import com.uacm.proyecto.ecommerce.models.entity.Entrega;
import com.uacm.proyecto.ecommerce.models.entity.Pago;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;
import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;
import com.uacm.proyecto.ecommerce.models.entity.Producto;
import com.uacm.proyecto.ecommerce.models.service.IClienteService;
import com.uacm.proyecto.ecommerce.models.service.IEntregaService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoProductoService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoService;
import com.uacm.proyecto.ecommerce.models.service.IProductoService;
import com.uacm.proyecto.ecommerce.util.paginacion.PaginacionCalculos;

@Controller
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	IPedidoService servicio;
	@Autowired
	IClienteService servicioCliente;
	@Autowired
	IProductoService servicioProducto;
	@Autowired
	IPedidoProductoService servicioPedidoProducto;
	@Autowired
	IEntregaService servicioEntrega;
	
	@GetMapping("/listar")
	public String listar(Model model) {	
		model.addAttribute("listaPedidos", servicio.obtenerTodos());
		return "pedido/pedidos";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		Pedido pedido = servicio.buscar(id);
		if(pedido != null) {
			servicio.eliminar(pedido);
		}
		return "redirect:/pedido/listar";
	}
	
	@GetMapping("/crear/{id}")
	public String seleccionarProductos(@PathVariable(value="id") Long id, Model model) {
		PedidoProductoDto dto = crearDto();
		model.addAttribute("dto", dto);
		model.addAttribute("idCliente", id);
		return "pedido/pedidoForm";
	}
	
	@PostMapping("/crear/{id}")
	public String crear(@PathVariable(value="id") Long id, @ModelAttribute("dto") PedidoProductoDto dto, Model model, SessionStatus status) {
		Cliente c = servicioCliente.buscar(id);
		List<PedidoProducto> listaPp = dto.getProductos();
		List<PedidoProducto> lista = new ArrayList<>();
		Pedido pe = new Pedido();
		PedidoProducto pp;
		Producto pr;
		double total = 0;
		
		pe.setCliente(c);
		for (PedidoProducto p: listaPp) {
			pr = p.getProducto();
			if (pr.isSeleccionado()) {
				pp = new PedidoProducto();
				pp.setPedido(pe);
				pp.setProducto(pr);
				pp.setCantidadPiezas(p.getCantidadPiezas());
				servicioPedidoProducto.guardar(pp);
				total += pp.getSubtotalPagar();
			}
		}
		pe.setInfoAdicional(dto.getInfo());
		pe.setPedidosProductos(lista);
		pe.setEstado(4);
		pe.setTotalPagar(total);
		servicio.guardar(pe);
		status.setComplete();
		return "redirect:/entrega/crear/" + pe.getId();
	 }
	 
	 @ModelAttribute("dto")
	 public PedidoProductoDto crearDto() {
		PedidoProductoDto dto = new PedidoProductoDto();
		PedidoProducto pp;
		for (Producto p: servicioProducto.obtenerTodos()) {
			if(p.getEstado() != 2) {
				pp = new PedidoProducto();
				pp.setProducto(p);
				dto.addProducto(pp);
			}
		}
		return dto;
	 }
	 
	@GetMapping("/listar/pag")
	public String listarPaginacion(@RequestParam(name="page", defaultValue="0") int page, Model model) {	
		Pageable pageRequest = PageRequest.of(page, 3);
		Page<Pedido> pedidos = servicio.obtenerTodosPagina(pageRequest);
			
		PaginacionCalculos<Pedido> paginacionCalculos = new PaginacionCalculos<>("pedido/listar/pag", pedidos);
		model.addAttribute("listaPedidos", pedidos);
		model.addAttribute("pagina", paginacionCalculos);
		model.addAttribute("paginacion", true);
		return "pedido/pedidos";
	}
	 
	@GetMapping("/entregas/{id}")
	public String listarEntregas(@PathVariable(value="id") Long id, Model model){
		Pedido p = servicio.buscar(id);
		List<Entrega> lista = servicioEntrega.obtenerTodosPedido(p);
		List<Entrega> lista2 = /*servicioEntrega.obtenerHistorialPedido(p);*/p.getHistorialEntregas();
		model.addAttribute("pedido", p);
		model.addAttribute("listaEntregas", lista);
		model.addAttribute("historial", lista2);
		return "pedido/pedido-entregas";
	}
	 
	 @GetMapping("/pago/{id}")
	 public String listarPago(@PathVariable(value="id") Long id, Model model) {
		 Pedido pe = servicio.buscar(id);
		 Pago pa = pe.getPago();
		 String nTarjeta = pa.getNumTarjeta();
		 pa.setTarjeta("****" + nTarjeta.substring(nTarjeta.length() - 4, nTarjeta.length()));
		 model.addAttribute("listaPagos", pa);
		 model.addAttribute("pedido", pe);
		 return "pedido/pedido-pago";
	 }
}
