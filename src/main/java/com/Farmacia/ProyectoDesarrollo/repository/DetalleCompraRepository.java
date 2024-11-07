package com.Farmacia.ProyectoDesarrollo.repository;

import com.Farmacia.ProyectoDesarrollo.entity.DetalleCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleCompraRepository extends JpaRepository<DetalleCompra,Long> {
}
