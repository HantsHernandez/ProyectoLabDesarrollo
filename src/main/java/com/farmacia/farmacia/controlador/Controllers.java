package com.farmacia.farmacia.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

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

    @PostMapping("/empleados/agregar")
    public String guardarEmpleado() {
        //guardar informacion


        //Obtener los registros y enviarlos a la vista
        return "fragments/GestionEmpleados";
    }

    // Ruta para mostrar el formulario de agregar empleado
    @GetMapping("/nuevoEmpleado")
    public String mostrarFormularioEmpleado() {
        return "fragments/AgregarEmpleado :: contenido";
    }
}
