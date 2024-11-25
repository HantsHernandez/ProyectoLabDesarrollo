package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.MetodoPago;
import com.farmacia.farmacia.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MetodoPagoService {

    @Autowired
    private MetodoPagoRepository metodoPagoRepository;

    public MetodoPago obtenerMetodoPago(Long id){
        return this.metodoPagoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("METODO DE PAGO NO IDENTIFICADO"));
    }

    public List<MetodoPago> listaMetodosPago(){
        return this.metodoPagoRepository.findAll();
    }
}
