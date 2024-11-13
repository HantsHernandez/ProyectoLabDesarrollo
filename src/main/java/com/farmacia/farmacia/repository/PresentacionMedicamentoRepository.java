package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.PresentacionMedicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentacionMedicamentoRepository extends JpaRepository<PresentacionMedicamento,Long> {
}