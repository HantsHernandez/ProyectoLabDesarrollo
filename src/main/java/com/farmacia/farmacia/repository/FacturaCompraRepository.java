package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.FacturaCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FacturaCompraRepository extends JpaRepository<FacturaCompra,Long> {
}
