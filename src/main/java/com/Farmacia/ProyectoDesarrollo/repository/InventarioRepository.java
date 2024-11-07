package com.Farmacia.ProyectoDesarrollo.repository;


import com.Farmacia.ProyectoDesarrollo.entity.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario,Long> {
}
