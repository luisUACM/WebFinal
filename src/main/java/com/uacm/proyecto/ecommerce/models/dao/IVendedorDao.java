package com.uacm.proyecto.ecommerce.models.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.uacm.proyecto.ecommerce.models.entity.Vendedor;

public interface IVendedorDao extends PagingAndSortingRepository<Vendedor, Long>{

}
