package com.farmacia.farmacia.repository;

import com.farmacia.farmacia.entity.Rol;
import com.farmacia.farmacia.utils.ERol;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolRepository  extends JpaRepository<Rol,Long> {
    boolean existsByRol(ERol eRol);
    Rol findByRol(ERol rol);
}
