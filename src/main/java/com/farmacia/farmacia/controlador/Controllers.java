package com.farmacia.farmacia.controlador;

import com.farmacia.farmacia.service.CargoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Controllers {

    @Autowired
    private CargoService cargoService;

   @GetMapping("/fragmentoEmpleados")
    public String fragmentoEmpleados() {
        return "fragments/GestionEmpleados :: contenido";
    }

    @GetMapping("/fragmentoClientes")
    public String fragmentoClientes() { return "fragments/GestionClientes :: contenido"; }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "index";
    }

    // Ruta para mostrar el formulario de agregar empleado
    @GetMapping("/nuevoEmpleado")
    public String mostrarFormularioEmpleado(Model model) {
        model.addAttribute("listaCargos", this.cargoService.listaCargos());
        return "fragments/AgregarEmpleado :: contenido";
    }
}
