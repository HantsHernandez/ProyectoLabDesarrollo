package com.farmacia.farmacia.controller;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.farmacia.farmacia.service.ClienteService;
import com.farmacia.farmacia.service.MedicamentosService;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacia.farmacia.entity.DetalleVenta;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class VentaController {
    
    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/fragmentoVentas")
    public String mostrarVenta(Model model) {
        model.addAttribute("listaMedicamentos", medicamentosService.listaMedicamentos());
        model.addAttribute("listaCliente", clienteService.listaClientes());
        return "fragments/NuevaVenta";
    }

    
    @PostMapping("/guardarVenta")
    public String guardarVenta(
        @RequestParam("detalleVenta[]['idMedicamento']") List<Long> idsMedicamentos,
        @RequestParam("detalleVenta[]['precioUnitario']") List<Float> precios,
        @RequestParam("detalleVenta[]['cantidadVendida']") List<Integer> cantidades,
        @RequestParam("totalVenta") Double totalVenta,
        @RequestParam("idCliente") Long idCliente,
        Model model
    ) {
        List<DetalleVenta> detalleVenta = new ArrayList<>();
        for (int i = 0; i < idsMedicamentos.size(); i++) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setPrecioUnitario(precios.get(i));
            detalle.setCantidadVendida(cantidades.get(i));
            detalleVenta.add(detalle);
        }
    
        // Procesar la lista de detalleVenta
        for (DetalleVenta detalle : detalleVenta) {
            System.out.println("Precio Unitario: " + detalle.getPrecioUnitario());
            System.out.println("Cantidad Vendida: " + detalle.getCantidadVendida());
        }
        System.out.println("Total venta: " + totalVenta.toString());
        System.out.println("idCliente: " + idCliente.toString());

        model.addAttribute("listaMedicamentos", medicamentosService.listaMedicamentos());
        model.addAttribute("listaCliente", clienteService.listaClientes());
        return "fragments/NuevaVenta";
    }
}
