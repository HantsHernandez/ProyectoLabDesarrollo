package com.Farmacia.ProyectoDesarrollo.repository;

import com.Farmacia.ProyectoDesarrollo.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
}
