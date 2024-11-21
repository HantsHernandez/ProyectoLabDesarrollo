package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Rol;
import com.farmacia.farmacia.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolService {
    @Autowired
    private RolRepository rolRepository;

    public List<Rol> listaRoles(){
        return this.rolRepository.findAll();
    }
}
