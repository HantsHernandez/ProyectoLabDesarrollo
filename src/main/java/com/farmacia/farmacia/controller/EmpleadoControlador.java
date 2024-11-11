package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.service.CargoService;
import com.farmacia.farmacia.service.DireccionService;
import com.farmacia.farmacia.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String guardarEmpleado(Empleado empleado) {
        System.out.println(empleado);
        System.out.println("GENERO EMPLEADO: " + empleado.getGeneroEmpleado());
        empleadoService.guardarEmpleado(empleado);
        return "redirect:/fragmentoEmpleados";
    }

    @PostMapping("/actualizar-empleado")
    public String actualizarEmpleado(Empleado empleado){
        this.empleadoService.actualizarEmpleado(empleado);
        return "redirect:/fragmentoEmpleados";
    }

    @GetMapping("/eliminar-empleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        this.empleadoService.eliminarEmpleado(id);
        return "redirect:/fragmentoEmpleados";
    }

    // Vistas

    @GetMapping("/fragmentoEmpleados")
    public String fragmentoEmpleados(Model model) {
        model.addAttribute("listaEmpleados", this.empleadoService.listaEmpleados());
        return "fragments/GestionEmpleados :: contenido";
    }

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
}
