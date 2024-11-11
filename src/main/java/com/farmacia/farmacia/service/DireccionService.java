package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.repository.DireccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionService {

    @Autowired
    private DireccionRepository direccionRepository;

    public void saveDireccion(Direccion direccion){
        direccionRepository.save(direccion);
    }

    public Direccion getDireccion(Long id){
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DIRECCION NO IDENTIFICADA"));
    }

    public void updateDireccion(Direccion direccion){
        Direccion direccionActualizada = direccionRepository.findById(direccion.getIdDireccion())
                .map(direccion1 -> {
                    direccion1.setLinea1(direccion.getLinea1());
                    //direccion1.setLinea2(direccion.getLinea2());
                    return direccion1;
                })
                .orElseThrow(() -> new RuntimeException("DIRECCION NO IDENTIFICADA"));
        this.direccionRepository.save(direccion);
    }

    public void deleteDireccion(Long id){
        this.direccionRepository.findById(id)
                .ifPresentOrElse(
                        direccion -> this.direccionRepository.delete(direccion),
                        () -> {throw new RuntimeException("DIRECCION NO IDENTIFICADA");}
                );
    }

    public List<Direccion> listaDirecciones(){
        return this.direccionRepository.findAll();
    }
}
