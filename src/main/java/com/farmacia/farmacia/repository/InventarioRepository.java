package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {
}
