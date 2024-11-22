package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.ClienteDireccionDTO;
import com.farmacia.farmacia.service.ClienteService;
import com.farmacia.farmacia.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DireccionService direccionService;

    // CRUD
    @PostMapping("/guardar-cliente")
    public String guardarEmpleado(ClienteDireccionDTO clienteDireccionDTO){
        this.clienteService.guardarCliente(clienteDireccionDTO);
        return "redirect:/fragmentoClientes";
    }

    @PostMapping("/actualizar-cliente")
    public String actualizarEmpleado(ClienteDireccionDTO clienteDireccionDTO){
        this.clienteService.actualizarCliente(clienteDireccionDTO);
        return "redirect:/fragmentoClientes";
    }

    @GetMapping("/eliminar-cliente/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        this.clienteService.eliminarCliente(id);
        return "redirect:/fragmentoClientes";
    }

    // Vistas

    @GetMapping("/guardar-cliente")
    public String mostrarFormularioGuardar(Model model) {
        model.addAttribute("cliente", null);
        model.addAttribute("listaDirecciones", this.direccionService.listaDirecciones());
        return "fragments/AgregarCliente :: contenido";
    }

    @GetMapping("/actualizar-cliente/{id}")
    public String mostrarFormularioActualizar(Model model, @PathVariable Long id) {
        model.addAttribute("cliente", this.clienteService.obtenerCliente(id));
        model.addAttribute("listaDirecciones", this.direccionService.listaDirecciones());
        return "fragments/AgregarCliente :: contenido";
    }
}
