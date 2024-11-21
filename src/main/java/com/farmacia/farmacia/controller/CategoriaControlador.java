package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CategoriaControlador {

    @Autowired
    private CategoriaService categoriaService;

    // CRUD

    @PostMapping("/guardar-categoria")
    public String guardarCategoria(Categoria categoria){
        this.categoriaService.agregarCategoria(categoria);
        return "redirect:/fragmentoCategorias";
    }

    @PostMapping("/actualizar-categoria")
    public String actualizarCategoria(Categoria categoria){
        this.categoriaService.actualizarCategoria(categoria);
        return "redirect:/fragmentoCategorias";
    }

    @GetMapping("/eliminar-categoria/{id}")
    public String eliminarCategoria(@PathVariable Long id){
        this.categoriaService.eliminarCategoria(id);
        return "redirect:/fragmentoCategorias";
    }

    // VISTAS

    @GetMapping("/guardar-categoria")
    public String mostrarFormularioGuardar(Model model){
        model.addAttribute("categoria", null);
        return "fragments/AgregarCategoria :: contenido";
    }

    @GetMapping("/actualizar-categoria/{id}")
    public String mostrarFormularioModificar(Model model, @PathVariable Long id){
        model.addAttribute("categoria", this.categoriaService.obtenerCategoria(id));
        return "fragments/AgregarCategoria :: contenido";
    }

}
