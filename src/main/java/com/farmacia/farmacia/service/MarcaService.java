package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.entity.Marca;
import com.farmacia.farmacia.repository.MarcaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public void agregarMarca(Marca marca){
        this.marcaRepository.save(marca);
    }

    public Marca obtenerMarca(Long id){
        return this.marcaRepository.findById(id).orElseThrow(() -> new RuntimeException("MARCA NO IDENTIFICADA"));
    }

    public void actualizarMarca(Marca nuevoMarca){
        Marca marca = this.marcaRepository.findById(nuevoMarca.getIdMarca())
                .map(viejaCategoria ->{
                    viejaCategoria.setNombreMarca(nuevoMarca.getNombreMarca());
                    viejaCategoria.setDescripcionMarca(nuevoMarca.getDescripcionMarca());
                    return viejaCategoria;
                })
                .orElseThrow(() -> new RuntimeException("MARCA NO IDENTIFICADA"));
        this.marcaRepository.save(marca);
    }

    public void eliminarCategoria(Long id){
        this.marcaRepository.findById(id)
                .ifPresentOrElse(
                        marca -> this.marcaRepository.delete(marca),
                        () -> new RuntimeException("MARCA NO IDENTIFICADA")
                );
    }

    public List<Marca> listaMarcas(){
        return this.marcaRepository.findAll();
    }

    public Page<Marca> listaMarcasPaginados(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.marcaRepository.findAll(pageable);
    }
}
