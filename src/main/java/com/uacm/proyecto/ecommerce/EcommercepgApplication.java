package com.uacm.proyecto.ecommerce;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.uacm.proyecto.ecommerce.models.entity.Cliente;
import com.uacm.proyecto.ecommerce.models.entity.Entrega;
import com.uacm.proyecto.ecommerce.models.entity.Pago;
import com.uacm.proyecto.ecommerce.models.entity.Pedido;
import com.uacm.proyecto.ecommerce.models.entity.PedidoProducto;
import com.uacm.proyecto.ecommerce.models.entity.Producto;
import com.uacm.proyecto.ecommerce.models.entity.Vendedor;
import com.uacm.proyecto.ecommerce.models.service.IClienteService;
import com.uacm.proyecto.ecommerce.models.service.IEntregaService;
import com.uacm.proyecto.ecommerce.models.service.IPagoService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoProductoService;
import com.uacm.proyecto.ecommerce.models.service.IPedidoService;
import com.uacm.proyecto.ecommerce.models.service.IProductoService;
import com.uacm.proyecto.ecommerce.models.service.IVendedorService;

@SpringBootApplication
public class EcommercepgApplication implements CommandLineRunner{

	@Autowired
	IProductoService productoService;
	@Autowired	//No se si hace falta
	IVendedorService vendedorService;
	@Autowired
	IClienteService clienteService;
	@Autowired
	IPedidoService pedidoService;
	@Autowired
	IPagoService pagoService;
	@Autowired
	IEntregaService entregaService;
	@Autowired
	IPedidoProductoService pedidoProductoService;
	
	public static void main(String[] args) {
		SpringApplication.run(EcommercepgApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Vendedor v = new Vendedor();
		Vendedor v2 = new Vendedor();
		Vendedor v3 = new Vendedor();
		Vendedor v4 = new Vendedor();
		Vendedor v5 = new Vendedor();
		Vendedor v6 = new Vendedor();
		Vendedor v7 = new Vendedor();
		Vendedor v8 = new Vendedor();
		Vendedor v9 = new Vendedor();
		Vendedor v10 = new Vendedor();
		Vendedor v11 = new Vendedor();
		Producto pr = new Producto();
		Producto pr2 = new Producto();
		Producto pr3 = new Producto();
		Producto pr4 = new Producto();
		Producto pr5 = new Producto();
		Producto pr6 = new Producto();
		Producto pr7 = new Producto();
		Producto pr8 = new Producto();
		Producto pr9 = new Producto();
		Producto pr10 = new Producto();
		Producto pr11 = new Producto();
		Cliente c = new Cliente();
		Entrega e = new Entrega();
		Pago pa = new Pago();
		Pedido pe = new Pedido();
		PedidoProducto pp = new PedidoProducto();
		List<PedidoProducto> lista = new ArrayList();
		List<Producto> listaPr = new ArrayList();
		
		lista.add(pp);
		
		c.setNombre("Jose");
		c.setAppaterno("Barrera");
		c.setApmaterno("Mora");
		c.setCorreo("jose@gmail.com");
		c.setContrasena("123456");
		c.setEstadoActivo(true);
		c.setUsername("JoseComprador");
		
		v.setNombre("Pepe");
		v.setAppaterno("Lopez");
		v.setApmaterno("Perez");
		v.setCorreo("pepe@gmail.com");
		v.setContrasena("123");
		v.setEstadoActivo(true);
		v.setUsername("PepeVendedor");
		
		pr.setNombre("Algo");
		pr.setNumPiezas(1);
		pr.setDescripcion("Bueno, bonito y barato");
		pr.setInformacionAdicional("Compre ya");
		pr.setPrecio(2.1);
		pr.setEstado(1);
		pr.setVendedor(v);
		pr.setPedidosProductos(lista);	//No necesario
		
		pe.setEstado(3);
		pe.setTotalPagar(100.0);
		pe.setInfoAdicional("Nada");
		pe.setCliente(c);
		pe.setPago(pa);
		pe.setEntrega(e);
		pe.setPedidosProductos(lista);	//No necesario
		
		pa.setInfoAdicional("VISA");
		pa.setPedido(pe);
		pa.setNumTarjeta("64372154995376565");
		pa.setNombre("José Barrera González");
		
		e.setPuntoEntrega("Mi casa");
		e.setNotasAdicionales("Ninguna");
		e.setEstado(1);
		e.setFechaEntrega(new Date());
		e.setHoraEntrega(LocalTime.now());
		e.setPedido(pe);
		
		pp.setCantidadPiezas(2);
		pp.setPedido(pe);
		pp.setProducto(pr);
		pp.setDescuentoPorcentaje(0.0);
		
	
		/*vendedorService.guardar(v);
		clienteService.guardar(c);
		productoService.guardar(pr);
		pedidoService.guardar(pe);
		pagoService.guardar(pa);
		entregaService.guardar(e);*/
		pedidoProductoService.guardar(pp);
		
		
		/*listaPr.add(pr3);
		listaPr.add(pr4);*/
		v2.setNombre("Pepe");
		v2.setAppaterno("Lopez");
		v2.setApmaterno("Perez");
		v2.setCorreo("pepe@gmail.com");
		v2.setContrasena("123");
		v2.setEstadoActivo(true);
		v2.setUsername("PepeVendedor");
		v3.setNombre("Pepe");
		v3.setAppaterno("Lopez");
		v3.setApmaterno("Perez");
		v3.setCorreo("pepe@gmail.com");
		v3.setContrasena("123");
		v3.setEstadoActivo(true);
		v3.setUsername("PepeVendedor");
		//v3.setProductos(listaPr);
		
		v4.setNombre("Pepe");
		v4.setAppaterno("Lopez");
		v4.setApmaterno("Perez");
		v4.setCorreo("pepe@gmail.com");
		v4.setContrasena("123");
		v4.setEstadoActivo(true);
		v4.setUsername("PepeVendedor");
		v5.setNombre("Pepe");
		v5.setAppaterno("Lopez");
		v5.setApmaterno("Perez");
		v5.setCorreo("pepe@gmail.com");
		v5.setContrasena("123");
		v5.setEstadoActivo(true);
		v5.setUsername("PepeVendedor");
		v6.setNombre("Pepe");
		v6.setAppaterno("Lopez");
		v6.setApmaterno("Perez");
		v6.setCorreo("pepe@gmail.com");
		v6.setContrasena("123");
		v6.setEstadoActivo(true);
		v6.setUsername("PepeVendedor");
		v7.setNombre("Pepe");
		v7.setAppaterno("Lopez");
		v7.setApmaterno("Perez");
		v7.setCorreo("pepe@gmail.com");
		v7.setContrasena("123");
		v7.setEstadoActivo(true);
		v7.setUsername("PepeVendedor");
		v8.setNombre("Pepe");
		v8.setAppaterno("Lopez");
		v8.setApmaterno("Perez");
		v8.setCorreo("pepe@gmail.com");
		v8.setContrasena("123");
		v8.setEstadoActivo(true);
		v8.setUsername("PepeVendedor");
		v9.setNombre("Pepe");
		v9.setAppaterno("Lopez");
		v9.setApmaterno("Perez");
		v9.setCorreo("pepe@gmail.com");
		v9.setContrasena("123");
		v9.setEstadoActivo(true);
		v9.setUsername("PepeVendedor");
		v10.setNombre("Pepe");
		v10.setAppaterno("Lopez");
		v10.setApmaterno("Perez");
		v10.setCorreo("pepe@gmail.com");
		v10.setContrasena("123");
		v10.setEstadoActivo(true);
		v10.setUsername("PepeVendedor");
		v11.setNombre("Pepe");
		v11.setAppaterno("Lopez");
		v11.setApmaterno("Perez");
		v11.setCorreo("pepe@gmail.com");
		v11.setContrasena("123");
		v11.setEstadoActivo(true);
		v11.setUsername("PepeVendedor");
		
		pr2.setNombre("Algo");
		pr2.setNumPiezas(1);
		pr2.setDescripcion("Bueno, bonito y barato");
		pr2.setInformacionAdicional("Compre ya");
		pr2.setPrecio(2.1);
		pr2.setEstado(1);
		pr2.setVendedor(v2);
		pr3.setNombre("Algo");
		pr3.setNumPiezas(1);
		pr3.setDescripcion("Bueno, bonito y barato");
		pr3.setInformacionAdicional("Compre ya");
		pr3.setPrecio(2.1);
		pr3.setEstado(1);
		pr3.setVendedor(v3);
		pr4.setNombre("Algo");
		pr4.setNumPiezas(1);
		pr4.setDescripcion("Bueno, bonito y barato");
		pr4.setInformacionAdicional("Compre ya");
		pr4.setPrecio(2.1);
		pr4.setEstado(1);
		pr4.setVendedor(v3);
		pr5.setNombre("Algo");
		pr5.setNumPiezas(1);
		pr5.setDescripcion("Bueno, bonito y barato");
		pr5.setInformacionAdicional("Compre ya");
		pr5.setPrecio(2.1);
		pr5.setEstado(1);
		pr5.setVendedor(v5);
		pr6.setNombre("Algo");
		pr6.setNumPiezas(1);
		pr6.setDescripcion("Bueno, bonito y barato");
		pr6.setInformacionAdicional("Compre ya");
		pr6.setPrecio(2.1);
		pr6.setEstado(1);
		pr6.setVendedor(v6);
		pr7.setNombre("Algo");
		pr7.setNumPiezas(1);
		pr7.setDescripcion("Bueno, bonito y barato");
		pr7.setInformacionAdicional("Compre ya");
		pr7.setPrecio(2.1);
		pr7.setEstado(1);
		pr7.setVendedor(v7);
		pr8.setNombre("Algo");
		pr8.setNumPiezas(1);
		pr8.setDescripcion("Bueno, bonito y barato");
		pr8.setInformacionAdicional("Compre ya");
		pr8.setPrecio(2.1);
		pr8.setEstado(1);
		pr8.setVendedor(v8);
		pr9.setNombre("Algo");
		pr9.setNumPiezas(1);
		pr9.setDescripcion("Bueno, bonito y barato");
		pr9.setInformacionAdicional("Compre ya");
		pr9.setPrecio(2.1);
		pr9.setEstado(1);
		pr9.setVendedor(v9);
		pr10.setNombre("Algo");
		pr10.setNumPiezas(1);
		pr10.setDescripcion("Bueno, bonito y barato");
		pr10.setInformacionAdicional("Compre ya");
		pr10.setPrecio(2.1);
		pr10.setEstado(1);
		pr10.setVendedor(v10);
		pr11.setNombre("Algo");
		pr11.setNumPiezas(1);
		pr11.setDescripcion("Bueno, bonito y barato");
		pr11.setInformacionAdicional("Compre ya");
		pr11.setPrecio(2.1);
		pr11.setEstado(1);
		pr11.setVendedor(v11);
		
		vendedorService.guardar(v4);
		/*vendedorService.guardar(v2);
		vendedorService.guardar(v3);
		
		vendedorService.guardar(v5);
		vendedorService.guardar(v6);
		vendedorService.guardar(v7);
		vendedorService.guardar(v8);
		vendedorService.guardar(v9);
		vendedorService.guardar(v10);
		vendedorService.guardar(v11);*/
		
		productoService.guardar(pr2);
		productoService.guardar(pr3);
		//productoService.guardar(pr4);
		productoService.guardar(pr5);
		productoService.guardar(pr6);
		productoService.guardar(pr7);
		productoService.guardar(pr8);
		productoService.guardar(pr9);
		productoService.guardar(pr10);
		productoService.guardar(pr11);
	}
}
