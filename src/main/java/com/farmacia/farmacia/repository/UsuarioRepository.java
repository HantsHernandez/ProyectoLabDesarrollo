package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByNombre(String correo);

    boolean existsByNombre(String hants20);
}
