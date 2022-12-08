package com.uacm.proyecto.ecommerce.controllers;

import java.util.Map;

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

import com.uacm.proyecto.ecommerce.models.entity.Producto;
import com.uacm.proyecto.ecommerce.models.entity.Vendedor;
import com.uacm.proyecto.ecommerce.models.service.IProductoService;
import com.uacm.proyecto.ecommerce.models.service.IVendedorService;
import com.uacm.proyecto.ecommerce.util.paginacion.PaginacionCalculos;

@Controller
@RequestMapping("/producto")
public class ProductoController {

	@Autowired
	IProductoService servicio;
	@Autowired
	IVendedorService servicioVendedor;
	
	@GetMapping("/listar")
	public String listar(Model model) {	
		model.addAttribute("listaProductos", servicio.obtenerTodos());
		model.addAttribute("paginacion", false);
		return "producto/productos";
	}
	
	@GetMapping("/listar/pag")
	public String listarPaginacion(@RequestParam(name="page", defaultValue="0") int page, Model model) {	
		Pageable pageRequest = PageRequest.of(page, 3);
		Page<Producto> productos = servicio.obtenerTodosPagina(pageRequest);
		
		PaginacionCalculos<Producto> paginacionCalculos = new PaginacionCalculos<>("/producto/listar/pag", productos);
		model.addAttribute("listaProductos", productos);
		model.addAttribute("pagina", paginacionCalculos);
		model.addAttribute("paginacion", true);
		return "producto/productos";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable(value="id") Long id) {
		Producto producto= servicio.buscar(id);
		if(producto != null) {
			servicio.eliminar(producto);
		}
		return "redirect:/producto/listar/pag";
	}
	
	@GetMapping("/crear/{id}")
	public String crear(@PathVariable(value="id") Long id, Model model) {
		Producto p = new Producto();
		model.addAttribute("titulo", "Crear producto");
		model.addAttribute("producto", p);
		model.addAttribute("idVendedor", id);
		model.addAttribute("esNuevo", true);
		return "producto/productoForm";
	}
	
	@PostMapping("/crear/{id}")
	public String guardar(@PathVariable(value="id") Long id, Producto producto) {
		Vendedor v = servicioVendedor.buscar(id);
		Producto p = producto;
		p.setVendedor(v);
		p.setDescripcion(producto.getDescripcion());
		p.setInformacionAdicional(producto.getInformacionAdicional());
		p.setNombre(producto.getNombre());
		p.setNumPiezas(producto.getNumPiezas());
		p.setPrecio(producto.getPrecio());
		p.setEstado(1);
		p.setId(null);
		servicio.guardar(producto);
		return"redirect:/vendedor/productos/" + id; 
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable(value="id") Long id, Model model) {
		Producto p = servicio.buscar(id);
		model.addAttribute("producto", p);
		model.addAttribute("idVendedor", p.getVendedor().getId());
		model.addAttribute("esNuevo", false);
		return "producto/productoForm";
	}
	
	@PostMapping("/reemplazar/{id}")
	public String reemplazar(@PathVariable(value="id") Long id,  Producto producto) {
		Vendedor v = servicioVendedor.buscar(id);
		Producto p = producto;
		p.setVendedor(v);
		p.setDescripcion(producto.getDescripcion());
		p.setInformacionAdicional(producto.getInformacionAdicional());
		p.setNombre(producto.getNombre());
		p.setNumPiezas(producto.getNumPiezas());
		p.setPrecio(producto.getPrecio());
		p.setEstado(1);
		servicio.guardar(producto);
		return"redirect:/vendedor/productos/" + id; 
	}
}
