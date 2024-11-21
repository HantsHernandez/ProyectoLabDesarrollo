package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public void agregarCategoria(Categoria categoria){
        this.categoriaRepository.save(categoria);
    }

    public Categoria obtenerCategoria(Long id){
        return this.categoriaRepository.findById(id).orElseThrow(() -> new RuntimeException("CATEGORIA NO IDENTIFICADA"));
    }

    public void actualizarCategoria(Categoria nuevaCategoria){
        Categoria categoria = this.categoriaRepository.findById(nuevaCategoria.getIdCategoria())
                .map(viejaCategoria ->{
                    viejaCategoria.setDescripcionCategoria(nuevaCategoria.getDescripcionCategoria());
                    viejaCategoria.setNombreCategoria(nuevaCategoria.getNombreCategoria());
                    return viejaCategoria;
                })
                .orElseThrow(() -> new RuntimeException("CATEGORIA NO IDENTIFICADA"));
        this.categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(Long id){
        this.categoriaRepository.findById(id)
                .ifPresentOrElse(
                        categoria -> this.categoriaRepository.delete(categoria),
                        () -> new RuntimeException("CATEGORIA NO IDENTIFICADA")
                );
    }

    public List<Categoria> listaCategorias(){
        return this.categoriaRepository.findAll();
    }

    public Page<Categoria> listaCategoriasPaginados(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.categoriaRepository.findAll(pageable);
    }
}
