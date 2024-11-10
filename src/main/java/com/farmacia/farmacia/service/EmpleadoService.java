package com.farmacia.farmacia.service;

import com.farmacia.farmacia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmpleadoService {
    @Autowired
    private DireccionService direccionService;

    @Autowired
    private EmpleadoRepository empleadoRepository;





}
