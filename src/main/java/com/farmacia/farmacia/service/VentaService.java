package com.farmacia.farmacia.service;


import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.UsuarioRepository;
import com.farmacia.farmacia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public Venta agregarVenta(float totalVenta, int totalVentaProducto, Long idCliente){
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
            facturasVentaService.agregarFactura(venta);
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
}
