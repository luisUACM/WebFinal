package com.uacm.proyecto.ecommerce.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.uacm.proyecto.ecommerce.models.entity.Pago;

public interface IPagoDao extends JpaRepository<Pago, Long>{

}
