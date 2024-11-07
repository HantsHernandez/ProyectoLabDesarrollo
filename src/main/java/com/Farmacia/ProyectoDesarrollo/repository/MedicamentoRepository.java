package com.Farmacia.ProyectoDesarrollo.repository;

import com.Farmacia.ProyectoDesarrollo.entity.Medicamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicamentoRepository extends JpaRepository<Medicamento,Long> {

}
