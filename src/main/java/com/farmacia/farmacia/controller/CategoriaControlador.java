package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class CategoriaControlador {

    @Autowired
    private CategoriaService categoriaService;

    // CRUD

    @PostMapping("/guardar-categoria")
    public String guardarCategoria(Categoria categoria, RedirectAttributes redirectAttributes){
        this.categoriaService.agregarCategoria(categoria);
        redirectAttributes.addFlashAttribute("mensaje", "La Categoria Fue Guardada Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "agregar");
        return "redirect:/fragmentoCategorias";
    }

    @PostMapping("/actualizar-categoria")
    public String actualizarCategoria(Categoria categoria, RedirectAttributes redirectAttributes){
        this.categoriaService.actualizarCategoria(categoria);
        redirectAttributes.addFlashAttribute("mensaje", "La Categoria Fue Actualizada Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "actualizar");
        return "redirect:/fragmentoCategorias";
    }

    @GetMapping("/eliminar-categoria/{id}")
    public String eliminarCategoria(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.categoriaService.eliminarCategoria(id);
        redirectAttributes.addFlashAttribute("mensaje", "La Categoria fue Eliminada Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "eliminar");
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


    /*@GetMapping("/filtrar-categorias")
    public ResponseEntity<List<Categoria>> filtrarCategorias(@RequestParam String filtroTexto, @RequestParam String filtroSelect) {
        List<Categoria> categoriasFiltradas = categoriaService.filtrarCategorias(filtroTexto, filtroSelect);
        return ResponseEntity.ok(categoriasFiltradas);
    }*/

}
