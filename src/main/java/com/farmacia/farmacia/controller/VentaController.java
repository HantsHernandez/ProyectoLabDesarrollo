package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.service.*;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;


import com.farmacia.farmacia.entity.DetalleVenta;


import java.io.ByteArrayOutputStream;
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

    @Autowired
    private MetodoPagoService metodoPagoService;

    @Autowired
    private TemplateEngine templateEngine;

    @GetMapping("/fragmentoVentas")
    public String mostrarVenta(Model model) {
        model.addAttribute("listaMetodosPagos", this.metodoPagoService.listaMetodosPago());
        model.addAttribute("listaMedicamentos", this.medicamentosService.listaMedicamentos());
        model.addAttribute("listaCliente", this.clienteService.listaClientes());
        return "fragments/NuevaVenta";
    }

    @PostMapping("/guardarVenta")
    public String guardarVenta(
        @RequestParam("detalleVenta[]['idMedicamento']") List<Long> idsMedicamentos,
        @RequestParam("detalleVenta[]['precioUnitario']") List<Float> precios,
        @RequestParam("detalleVenta[]['cantidadVendida']") List<Integer> cantidades,
        @RequestParam("totalVenta") float totalVenta,
        @RequestParam("idCliente") Long idCliente,
        @RequestParam("idMetodoPago") Long idMetodoPago,
        Model model) {
        int cantidadVendidaProducto = 0;
        System.out.println("VALOR: EMTODO " + idMetodoPago);
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
        Venta venta = this.ventaService.agregarVenta(cantidadTotal, cantidadVendidaProducto, idCliente, idMetodoPago);
        this.detalleVentaService.agregarDetalleVenta(listaDetalleVenta, venta);

        model.addAttribute("listaMedicamentos", medicamentosService.listaMedicamentos());
        model.addAttribute("listaCliente", clienteService.listaClientes());
        return "fragments/NuevaVenta";
    }

    @GetMapping("/facturas/descargar/{idVenta}")
    public ResponseEntity<byte[]> descargarFactura(@PathVariable Long idVenta){
        try {
            System.out.println("ingreso : " + idVenta);
            Venta venta = this.ventaService.obtenerVenta(idVenta);
            Context context = new Context();
            context.setVariable("venta", venta);
            context.setVariable("listaDetalles", venta.getListaDetalle());
            context.setVariable("cliente", venta.getCliente());

            String html = templateEngine.process("factura", context);
            byte[] pdfBytes = this.generarPdfDesdeHtml(html);

            if (pdfBytes.length == 0) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }

                HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factura_" + idVenta + ".pdf");

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    private byte[] generarPdfDesdeHtml(String html) {
        try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            PdfRendererBuilder builder = new PdfRendererBuilder();
            builder.useFastMode();
            builder.withHtmlContent(html, "file:/C:/aplicacionFarmacia/");
            builder.toStream(outputStream);
            builder.run();

            return outputStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
            return new byte[0];  // Retornamos un array vacío en caso de error
        }
    }


}
