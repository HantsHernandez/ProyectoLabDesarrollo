package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.entity.Direccion;
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
    public String guardarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO) {
        System.out.println("EMPLEADO: " + empleadoDireccionDTO.getEmpleado());
        System.out.println("CARGO ID: " + empleadoDireccionDTO.getEmpleado().getCargo().getIdCargo());
        System.out.println("CARGO: " + empleadoDireccionDTO.getEmpleado().getCargo().getCargo());
        Direccion direccion = this.direccionService.guardarDireccion(empleadoDireccionDTO.getDireccion());
        empleadoDireccionDTO.getEmpleado().setDireccion(direccion);
        Empleado empleado = this.empleadoService.guardarEmpleado(empleadoDireccionDTO.getEmpleado());
        direccion.getListaEmpleados().add(empleado);
        return "redirect:/fragmentoEmpleados";
    }

    @PostMapping("/actualizar-empleado")
    public String actualizarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO){
        Direccion direccion = this.direccionService.actualizarDireccion(empleadoDireccionDTO.getDireccion());
        empleadoDireccionDTO.getEmpleado().setDireccion(direccion);
        this.empleadoService.actualizarEmpleado(empleadoDireccionDTO.getEmpleado());
        return "redirect:/fragmentoEmpleados";
    }

    @GetMapping("/eliminar-empleado/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        this.empleadoService.eliminarEmpleado(id);
        return "redirect:/fragmentoEmpleados";
    }

    // Vistas

    // METODO QUE DEBE DE PASAR AL HOME
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
