package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.EmpleadoDTO;
import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Laboratorio;
import com.farmacia.farmacia.service.CargoService;
import com.farmacia.farmacia.service.DireccionService;
import com.farmacia.farmacia.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class EmpleadoControlador {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private CargoService cargoService;

    @Autowired
    private DireccionService direccionService;

    // CRUD
    @PostMapping("/guardar-empleado")
    public String guardarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO) {
        this.empleadoService.guardarEmpleado(empleadoDireccionDTO);
        return "redirect:/fragmentoEmpleados";
    }

    @PostMapping("/actualizar-empleado")
    public String actualizarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO){
        this.empleadoService.actualizarEmpleado(empleadoDireccionDTO);
        return "redirect:/fragmentoEmpleados";
    }

    @GetMapping("/eliminar-empleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        this.empleadoService.eliminarEmpleado(id);
        return "redirect:/fragmentoEmpleados";
    }

    // Vistas

    @GetMapping("/guardar-empleado")
    public String mostrarFormularioGuardar(Model model) {
        model.addAttribute("empleado", null);
        model.addAttribute("listaCargos", this.cargoService.listaCargos());
        model.addAttribute("listaDirecciones", this.direccionService.listaDirecciones());
        return "fragments/AgregarEmpleado :: contenido";
    }

    @GetMapping("/actualizar-empleado/{id}")
    public String mostrarFormularioActualizar(Model model, @PathVariable Long id) {
        model.addAttribute("empleado", this.empleadoService.obtenerEmpleado(id));
        model.addAttribute("listaCargos", this.cargoService.listaCargos());
        model.addAttribute("listaDirecciones", this.direccionService.listaDirecciones());
        return "fragments/AgregarEmpleado :: contenido";
    }

    @GetMapping("/empleado/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerDetallesEmpleado(@PathVariable Long id) {
        // Lógica para obtener el laboratorio por ID
        EmpleadoDTO empleado = empleadoService.obtenerEmpleadoDTO(id);
        if (empleado != null) {
            return ResponseEntity.ok(empleado);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Laboratorio no encontrado");
    }
}
