package com.uacm.proyecto.ecommerce.util.paginacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

public class PaginacionCalculos<T> {
	private String url;
	private Page<T> pagina;
	private List<Pagina> paginas;
	private int totalPaginas;
	private int numElementosPorPagina;
	private int paginaActual;

	public PaginacionCalculos(String url, Page<T> pagina) {
		this.url = url;
		this.pagina = pagina;

		paginas = new ArrayList<Pagina>();
		totalPaginas = pagina.getSize();
		numElementosPorPagina = pagina.getTotalPages();
		paginaActual = pagina.getNumber() + 1;

		int desde, hasta;
		if (totalPaginas <= numElementosPorPagina) {
			desde = 1;
			hasta = totalPaginas;
		} else {
			if (paginaActual <= numElementosPorPagina / 2) {
				desde = 1;
				hasta = numElementosPorPagina;
			} else if (paginaActual >= totalPaginas - numElementosPorPagina / 2) {
				desde = totalPaginas - numElementosPorPagina + 1;
				hasta = numElementosPorPagina;
			} else {
				desde = paginaActual - numElementosPorPagina / 2;
				hasta = numElementosPorPagina;
			}
		}

		for (int i = 0; i < hasta; i++) {
			paginas.add(new Pagina(desde + i, paginaActual == desde + i));
		}

	}

	public String getUrl() {
		return url;
	}

	public List<Pagina> getPaginas() {
		return paginas;
	}

	public int getTotalPaginas() {
		return totalPaginas;
	}

	public int getPaginaActual() {
		return paginaActual;
	}

	public boolean isFirst() {
		return pagina.isFirst();
	}

	public boolean isLast() {
		return pagina.isLast();
	}

	public boolean isHasNext() {
		return pagina.hasNext();
	}

	public boolean isHasPrevious() {
		return pagina.hasPrevious();
	}
}
