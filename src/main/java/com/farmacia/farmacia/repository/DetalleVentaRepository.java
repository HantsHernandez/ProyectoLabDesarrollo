package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.DetalleVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleVentaRepository extends JpaRepository<DetalleVenta,Long> {
    List<DetalleVenta> findByVenta_IdVenta(Long idVenta);
}
