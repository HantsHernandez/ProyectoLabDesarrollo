package com.farmacia.farmacia.service;

import com.farmacia.farmacia.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaService ventaService;


    //public void guardarDetallesVenta()




}
