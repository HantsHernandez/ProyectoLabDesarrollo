package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.FacturaVenta;
import com.farmacia.farmacia.repository.FacturasVentaRepository;
import com.farmacia.farmacia.repository.MetodoPagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class FacturasVentaService {

    @Autowired
    private FacturasVentaRepository facturasVentaRepository;

    @Autowired
    private MetodoPagoRepository metodoPagoService;

    public FacturaVenta agregarFactura(float total){
        FacturaVenta facturaVenta = new FacturaVenta();
        //facturaVenta.setIvaFacturaVenta(total * (16/100));
        //facturaVenta.setMetodoPago(metodoPagoService.findById(1L).get());
        facturaVenta.setFechaHoraFacturacion(LocalDateTime.now().toString());
        return facturasVentaRepository.save(facturaVenta);
    }

}
