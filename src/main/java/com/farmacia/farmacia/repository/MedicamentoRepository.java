package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento,Long> {

}
