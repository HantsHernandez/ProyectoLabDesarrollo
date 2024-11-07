package com.Farmacia.ProyectoDesarrollo.repository;

import com.Farmacia.ProyectoDesarrollo.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
}
