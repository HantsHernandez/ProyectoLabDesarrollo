package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.FacturaVenta;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.FacturasVentaRepository;
import com.farmacia.farmacia.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class FacturasVentaService {

    @Autowired
    private FacturasVentaRepository facturasVentaRepository;

    @Autowired
    private MetodoPagoService metodoPagoService;

    public FacturaVenta agregarFactura(Venta venta){
        FacturaVenta facturaVenta = new FacturaVenta();
        facturaVenta.setNumeroFactura(UUID.randomUUID().toString());
        facturaVenta.setFechaHoraFacturacion(LocalDateTime.now().toString());
        facturaVenta.setVenta(venta);
        facturaVenta.setMetodoPago(this.metodoPagoService.obtenerMetodoPago(1L));
        return facturasVentaRepository.save(facturaVenta);
    }

}
