package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Marca;
import com.farmacia.farmacia.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MarcaControlador {

    @Autowired
    private MarcaService marcaService;

    // CRUD

    @PostMapping("/guardar-marca")
    public String guardarMarca(Marca marca){
        this.marcaService.agregarMarca(marca);
        return "redirect:/fragmentoMarcas";
    }

    @PostMapping("/actualizar-marca")
    public String actualizarMarca(Marca marca){
        this.marcaService.actualizarMarca(marca);
        return "redirect:/fragmentoMarcas";
    }

    @GetMapping("/eliminar-marca/{id}")
    public String eliminarMarca(@PathVariable Long id){
        this.marcaService.eliminarCategoria(id);
        return "redirect:/fragmentoMarcas";
    }

    // VISTAS

    @GetMapping("/guardar-marca")
    public String mostrarFormularioGuardar(Model model){
        model.addAttribute("marca", null);
        return "fragments/AgregarMarca :: contenido";
    }

    @GetMapping("/actualizar-marca/{id}")
    public String mostrarFormularioModificar(Model model, @PathVariable Long id){
        model.addAttribute("marca", this.marcaService.obtenerMarca(id));
        return "fragments/AgregarMarca :: contenido";
    }
}
