package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.service.ClienteService;
import com.farmacia.farmacia.service.MedicamentosService;
import com.farmacia.farmacia.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ControladorVenta {
    @Autowired
    private VentaService ventaService;

    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/fragementoVentas")
    public String vistaVenta(Model model){
        model.addAttribute("listaMedicamentos", this.medicamentosService.listaMedicamentos());
        model.addAttribute("listaClientes", this.clienteService.listaClientes());
        return "redirect:/fragmentoVentas";
    }

}
