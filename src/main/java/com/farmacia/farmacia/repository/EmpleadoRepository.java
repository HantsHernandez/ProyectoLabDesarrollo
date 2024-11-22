package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmpleadoRepository extends JpaRepository<Empleado,Long> {
    @Query(value = "SELECT * FROM empleados m WHERE m.nombre_empleado LIKE CONCAT('%', :palabraBuscar, '%') OR m.apellido_empleado LIKE CONCAT('%', :palabraBuscar, '%')", nativeQuery = true)
    public Page<Empleado> findAll(@Param("palabraBuscar") String palabraBuscar, Pageable pageable);
}
