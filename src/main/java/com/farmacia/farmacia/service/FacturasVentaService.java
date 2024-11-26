package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.FacturaVenta;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.FacturasVentaRepository;
import com.farmacia.farmacia.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Service
public class FacturasVentaService {

    @Autowired
    private FacturasVentaRepository facturasVentaRepository;

    @Autowired
    private MetodoPagoService metodoPagoService;

    public FacturaVenta agregarFactura(Venta venta, Long idMetodoPago){
        FacturaVenta facturaVenta = new FacturaVenta();
        facturaVenta.setNumeroFactura(this.generarNumeroFactura());
        facturaVenta.setFechaHoraFacturacion(LocalDateTime.now().toString());
        facturaVenta.setVenta(venta);
        facturaVenta.setMetodoPago(this.metodoPagoService.obtenerMetodoPago(idMetodoPago));
        return facturasVentaRepository.save(facturaVenta);
    }


    private String generarNumeroFactura() {
        String prefijo = "F--"; // Prefijo para identificar facturas
        String fechaActual = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String identificadorUnico = UUID.randomUUID().toString().substring(0, 4); // Reducir el UUID para hacerlo más corto
        return prefijo + fechaActual + "--" + identificadorUnico;
    }
}
