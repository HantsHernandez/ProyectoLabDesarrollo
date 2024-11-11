package com.farmacia.farmacia.controlador;

import com.farmacia.farmacia.service.CargoService;
import com.farmacia.farmacia.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;

@Controller
public class Controllers {

    @GetMapping("/fragmentoClientes")
    public String fragmentoClientes() { return "fragments/GestionClientes :: contenido"; }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "index";
    }

}
