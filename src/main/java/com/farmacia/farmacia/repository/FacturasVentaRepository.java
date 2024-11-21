package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.FacturaVenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturasVentaRepository extends JpaRepository<FacturaVenta,Long> {
}
