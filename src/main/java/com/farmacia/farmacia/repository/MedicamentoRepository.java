package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Medicamento;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento,Long> {
    //Page<Medicamento> findAll(Pageable pageable);
}
