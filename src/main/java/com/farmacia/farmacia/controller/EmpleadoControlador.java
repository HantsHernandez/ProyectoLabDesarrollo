package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Sucursal;
import com.farmacia.farmacia.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/empleado")
public class EmpleadoControlador {
    @Autowired
    EmpleadoService empleadoService;

    @PostMapping("/agregar")
    public String guardarEmpleado(Empleado empleado) {
        return "redirect:/fragmentoEmpleados";
    }

    @PostMapping("/modificar")
    public void actualizarEmpleado(Empleado empleado, Direccion direccion, Sucursal sucursal){

    }
}
