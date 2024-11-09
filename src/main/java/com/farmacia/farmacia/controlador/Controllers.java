package com.farmacia.farmacia.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Controllers {
    @GetMapping("/fragmentoEmpleados")
    public String fragmentoEmpleados() {
        return "fragments/GestionEmpleados :: contenido";
    }

    @GetMapping("/fragmentoClientes")
    public String fragmentoClientes() {
        return "fragments/GestionClientes :: contenido";
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "index";
    }

    // Ruta para mostrar el formulario de agregar empleado
    @GetMapping("/nuevoEmpleado")
    public String mostrarFormularioEmpleado() {
        return "fragments/AgregarEmpleado :: contenido";
    }
}
