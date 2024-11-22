package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.ClienteDireccionDTO;
import com.farmacia.farmacia.DTO.LaboratorioDireccionDTO;
import com.farmacia.farmacia.entity.Laboratorio;
import com.farmacia.farmacia.service.ClienteService;
import com.farmacia.farmacia.service.DireccionService;
import com.farmacia.farmacia.service.LaboratorioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class LaboratorioControlador {

    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private DireccionService direccionService;

    // CRUD
    @PostMapping("/guardar-laboratorio")
    public String guardarEmpleado(LaboratorioDireccionDTO laboratorioDireccionDTO){
        this.laboratorioService.guardarLaboratorio(laboratorioDireccionDTO);
        return "redirect:/fragmentoLaboratorio";
    }

    @PostMapping("/actualizar-laboratorio")
    public String actualizarEmpleado(LaboratorioDireccionDTO laboratorioDireccionDTO){
        this.laboratorioService.actualizarLaboratorio(laboratorioDireccionDTO);
        return "redirect:/fragmentoLaboratorios";
    }

    @GetMapping("/eliminar-laboratorio/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        this.laboratorioService.eliminarLaboratorio(id);
        return "redirect:/fragmentoLaboratorios";
    }

    // Vistas

    @GetMapping("/guardar-laboratorio")
    public String mostrarFormularioGuardar(Model model) {
        model.addAttribute("laboratorio", null);
        model.addAttribute("listaDirecciones", this.direccionService.listaDirecciones());
        return "fragments/AgregarLaboratorio :: contenido";
    }

    @GetMapping("/actualizar-laboratorio/{id}")
    public String mostrarFormularioActualizar(Model model, @PathVariable Long id) {
        model.addAttribute("laboratorio", this.laboratorioService.obtenerLaboratorio(id));
        model.addAttribute("listaDirecciones", this.direccionService.listaDirecciones());
        return "fragments/AgregarLaboratorio :: contenido";
    }




    @GetMapping("/laboratorio/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerDetallesLaboratorio(@PathVariable Long id) {
        // Lógica para obtener el laboratorio por ID
        Laboratorio laboratorioOpt = laboratorioService.obtenerLaboratorio(id);
        if (laboratorioOpt != null) {
            return ResponseEntity.ok(laboratorioOpt);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laboratorio no encontrado");
    }

}
