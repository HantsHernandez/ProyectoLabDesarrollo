package com.farmacia.farmacia.repository;


import com.farmacia.farmacia.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria,Long> {
    /*@Query(value = "SELECT * FROM categorias p WHERE p.nombreCategoria LIKE %?1%", nativeQuery = true)
    public List<Categoria> findAll(String palabraBuscar);*/

    @Query(value = "SELECT * FROM categorias p WHERE p.nombre_categoria LIKE CONCAT('%', :palabraBuscar, '%')", nativeQuery = true)
    public Page<Categoria> findAll(@Param("palabraBuscar") String palabraBuscar, Pageable pageable);


}
