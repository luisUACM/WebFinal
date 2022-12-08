package com.uacm.proyecto.ecommerce.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.uacm.proyecto.ecommerce.models.entity.Cliente;
import com.uacm.proyecto.ecommerce.models.entity.Vendedor;
import com.uacm.proyecto.ecommerce.models.service.IVendedorService;
import com.uacm.proyecto.ecommerce.util.paginacion.PaginacionCalculos;

@Controller
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	IVendedorService servicio;
	
	@GetMapping("/listar")
	public String listar(Model model) {	
		model.addAttribute("listaVendedores", servicio.obtenerTodos());
		model.addAttribute("paginacion", false);
		return "vendedor/vendedores";
	}
	
	@GetMapping("/listar/pag")
	public String listarPaginacion(@RequestParam(name="page", defaultValue="0") int page, Model model) {	
		Pageable pageRequest = PageRequest.of(page, 3);
		Page<Vendedor> vendedores = servicio.obtenerTodosPagina(pageRequest);
		
		PaginacionCalculos<Vendedor> paginacionCalculos = new PaginacionCalculos<>("/vendedor/listar/pag", vendedores);
		model.addAttribute("listaVendedores", vendedores);
		model.addAttribute("pagina", paginacionCalculos);
		model.addAttribute("paginacion", true);
		return "vendedor/vendedores";
	}
	
	@GetMapping("/productos/{id}")
	public String listarProductos(@PathVariable(value="id") Long id, Model model) {
		model.addAttribute("vendedor", servicio.buscar(id));
		return "vendedor/vendedor-productos";
	}
	
	@GetMapping("/crear")
	public String crear(Model model) {
		Vendedor v = new Vendedor();
		model.addAttribute("vendedor", v);
		model.addAttribute("esCliente", false);
		model.addAttribute("titulo", "Registrar vendedor");
		model.addAttribute("esNuevo", true);
		return "usuarioForm";
	}
	
	@PostMapping("/crear")
	public String guardar(Vendedor v) {
		if (v.getId() != null) {
			Vendedor vendedor = servicio.buscar(v.getId());
			v.setFechaRegistro(vendedor.getFechaRegistro());
		}
		servicio.guardar(v);
		return "redirect:/vendedor/listar/pag";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id, Model model){
		Vendedor v = servicio.buscar(id);
		servicio.eliminar(v);
		return "redirect:/vendedor/listar";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model){
		Vendedor v = servicio.buscar(id);
		model.addAttribute("vendedor", v);
		model.addAttribute("esCliente", false);
		model.addAttribute("titulo", "Editar vendedor");
		model.addAttribute("esNuevo", false);
		return "usuarioForm";
	}
}
