package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Inventario;
import com.farmacia.farmacia.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    public Inventario agregarInventario(Inventario inventario){
        return this.inventarioRepository.save(inventario);
    }

    public Inventario obtenerInventario(Long id){
        return this.inventarioRepository.findById(id).orElseThrow(() -> new RuntimeException("INVENTARIO NO IDENTIFICADO"));
    }

    public Inventario actualizarInventario(Inventario nuevoInventario){
        Inventario inventario = this.inventarioRepository.findById(nuevoInventario.getIdInventario())
                .map(viejoInventario ->{
                    viejoInventario.setEstanteria(nuevoInventario.getEstanteria());
                    viejoInventario.setPasillo(nuevoInventario.getPasillo());
                    viejoInventario.setCantidadStock(nuevoInventario.getCantidadStock());
                    return viejoInventario;
                })
                .orElseThrow(() -> new RuntimeException("INVENTARIO NO IDENTIFICADO"));
        this.inventarioRepository.save(inventario);
        return inventario;
    }

    public void eliminarInventario(Long id){
        this.inventarioRepository.findById(id)
                .ifPresentOrElse(
                        inventario -> this.inventarioRepository.delete(inventario),
                        () -> new RuntimeException("INVENTARIO NO IDENTIFICADO")
                );
    }

    public List<Inventario> listaInvetario(){
        return this.inventarioRepository.findAll();
    }
}
