package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.ClienteDTO;
import com.farmacia.farmacia.DTO.ClienteDireccionDTO;
import com.farmacia.farmacia.service.ClienteService;
import com.farmacia.farmacia.service.DireccionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private DireccionService direccionService;

    // CRUD
    @PostMapping("/guardar-cliente")
    public String guardarEmpleado(ClienteDireccionDTO clienteDireccionDTO, RedirectAttributes redirectAttributes){
        this.clienteService.guardarCliente(clienteDireccionDTO);
        redirectAttributes.addFlashAttribute("mensaje", "El Cliente Fue Guardado Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "agregar");
        return "redirect:/fragmentoClientes";
    }

    @PostMapping("/actualizar-cliente")
    public String actualizarEmpleado(ClienteDireccionDTO clienteDireccionDTO, RedirectAttributes redirectAttributes){
        this.clienteService.actualizarCliente(clienteDireccionDTO);
        redirectAttributes.addFlashAttribute("mensaje", "El Cliente Fue Actualizado Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "actualizar");
        return "redirect:/fragmentoClientes";
    }

    @GetMapping("/eliminar-cliente/{id}")
    public String eliminarEmpleado(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.clienteService.eliminarCliente(id);
        redirectAttributes.addFlashAttribute("mensaje", "El Cliente fue Eliminado Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "eliminar");
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

    @GetMapping("/cliente/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerDetallesCliente(@PathVariable Long id) {
        ClienteDTO cliente = clienteService.obtenerClienteDTO(id);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cliente no encontrado");
    }
}
