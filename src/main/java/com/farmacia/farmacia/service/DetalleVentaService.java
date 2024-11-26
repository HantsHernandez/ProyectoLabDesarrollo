package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.DetalleVenta;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.DetalleVentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Autowired
    private VentaService ventaService;

    public List<DetalleVenta> agregarDetalleVenta(List<DetalleVenta>listadoDetalleVentas, Venta venta){
        for(DetalleVenta detalle : listadoDetalleVentas){
            detalle.setVenta(venta);
        }
        return this.detalleVentaRepository.saveAll(listadoDetalleVentas);
    }

    public List<DetalleVenta> obtenerDetallesPorIdVenta(Long idVenta) {
        return detalleVentaRepository.findByVenta_IdVenta(idVenta);
    }
}
