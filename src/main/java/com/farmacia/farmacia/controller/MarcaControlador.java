package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Marca;
import com.farmacia.farmacia.service.MarcaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class MarcaControlador {

    @Autowired
    private MarcaService marcaService;

    // CRUD

    @PostMapping("/guardar-marca")
    public String guardarMarca(Marca marca, RedirectAttributes redirectAttributes) {
        this.marcaService.agregarMarca(marca);
        redirectAttributes.addFlashAttribute("mensaje", "Marca Fue Guardada Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "agregar");
        return "redirect:/fragmentoMarcas";  // Se redirige a la URL del fragmento
    }

    @PostMapping("/actualizar-marca")
    public String actualizarMarca(Marca marca, RedirectAttributes redirectAttributes){
        this.marcaService.actualizarMarca(marca);
        redirectAttributes.addFlashAttribute("mensaje", "La Marca Fue Actualizada Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "actualizar");
        return "redirect:/fragmentoMarcas";
    }

    @GetMapping("/eliminar-marca/{id}")
    public String eliminarMarca(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.marcaService.eliminarCategoria(id);
        redirectAttributes.addFlashAttribute("mensaje", "La Marca fue Eliminada Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "eliminar");
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
