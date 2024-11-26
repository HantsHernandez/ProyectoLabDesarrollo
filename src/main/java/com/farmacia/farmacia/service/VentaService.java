package com.farmacia.farmacia.service;


import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.UsuarioRepository;
import com.farmacia.farmacia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private FacturasVentaService facturasVentaService;

    public Venta agregarVenta(float totalVenta, int totalVentaProducto, Long idCliente, Long idMetodoPago){
        Optional<Usuario> oUsuario = this.usuarioRepository.findByNombre(SecurityContextHolder.getContext().getAuthentication().getName());
        if (oUsuario.isPresent()) {
            Usuario usuario = oUsuario.get();
            Venta venta = Venta.builder()
                    .fechaVenta(LocalDate.now())
                    .totalVenta(totalVenta)
                    .cantidadTotalVendida(totalVentaProducto)
                    .cliente(this.clienteService.obtenerCliente(idCliente))
                    .empleado(usuario.getEmpleado())
                    .build();
            venta = ventaRepository.save(venta);
            venta.setFacturaVenta(facturasVentaService.agregarFactura(venta, idMetodoPago));
            return venta;
        } else {
            //Es porque no se autentificado
            throw new RuntimeException("EMPLEADO NO IDENTIFICADO");
        }
    }

    public Venta obtenerVenta(Long id){
        return this.ventaRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("VENTA NO IDENTIFICADA"));
    }

    public List<Venta> listaVentas(){
        return this.ventaRepository.findAll();
    }
}
