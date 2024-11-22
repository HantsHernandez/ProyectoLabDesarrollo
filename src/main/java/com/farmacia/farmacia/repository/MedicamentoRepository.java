package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.entity.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento,Long> {
    @Query(value = "SELECT * FROM medicamentos m WHERE m.nombre_medicamento LIKE CONCAT('%', :palabraBuscar, '%') OR m.formula_medicamento LIKE CONCAT('%', :palabraBuscar, '%')", nativeQuery = true)
    public Page<Medicamento> findAll(@Param("palabraBuscar") String palabraBuscar, Pageable pageable);

}
