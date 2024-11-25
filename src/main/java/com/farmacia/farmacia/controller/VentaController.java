package com.farmacia.farmacia.controller;
import com.farmacia.farmacia.entity.FacturaVenta;
import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.farmacia.farmacia.entity.DetalleVenta;

import java.util.*;

@Controller
public class VentaController {
    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private DetalleVentaService detalleVentaService;

    @Autowired
    private FacturasVentaService facturasVentaService;

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
        @RequestParam("totalVenta") float totalVenta,
        @RequestParam("idCliente") Long idCliente,
        Model model) {
        int cantidadVendidaProducto = 0;
        float cantidadTotal = 0;
        List<DetalleVenta> listaDetalleVenta = new ArrayList<>();
        for(int i = 0; i < idsMedicamentos.size(); i++){
            Medicamento medicamento = this.medicamentosService.actualizarStock(idsMedicamentos.get(i), cantidades.get(i));
            DetalleVenta detalleVenta = DetalleVenta.builder()
                    .precioUnitario(precios.get(i))
                    .cantidadVendida(cantidades.get(i))
                    .subTotalVendida(precios.get(i) * cantidades.get(i))
                    .medicamento(medicamento)
                    .build();
            listaDetalleVenta.add(detalleVenta);
            cantidadVendidaProducto = cantidadVendidaProducto + cantidades.get(i);
            cantidadTotal = cantidadTotal + (cantidades.get(i) * precios.get(i));
        }
        Venta venta = this.ventaService.agregarVenta(cantidadTotal, cantidadVendidaProducto, idCliente);
        this.detalleVentaService.agregarDetalleVenta(listaDetalleVenta, venta);
        model.addAttribute("listaMedicamentos", medicamentosService.listaMedicamentos());
        model.addAttribute("listaCliente", clienteService.listaClientes());
        return "fragments/NuevaVenta";
    }
}
