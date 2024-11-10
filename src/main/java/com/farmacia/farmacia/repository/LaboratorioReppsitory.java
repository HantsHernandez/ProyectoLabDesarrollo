package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Laboratorio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LaboratorioReppsitory extends JpaRepository<Laboratorio,Long> {

}
