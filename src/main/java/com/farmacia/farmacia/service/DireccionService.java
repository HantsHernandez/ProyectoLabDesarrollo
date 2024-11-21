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

    public Direccion guardarDireccion(Direccion direccion){
        return direccionRepository.save(direccion);
    }

    public Direccion obtenerDireccion(Long id){
        return direccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DIRECCION NO IDENTIFICADA"));
    }

    public Direccion actualizarDireccion(Direccion direccion){
        Direccion direccionActualizada = direccionRepository.findById(direccion.getIdDireccion())
                .map(direccion1 -> {
                    direccion1.setLinea1(direccion.getLinea1());
                    return direccion1;
                })
                .orElseThrow(() -> new RuntimeException("DIRECCION NO IDENTIFICADA"));
        return this.direccionRepository.save(direccionActualizada);
    }

    public void eliminarDireccion(Long id){
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
