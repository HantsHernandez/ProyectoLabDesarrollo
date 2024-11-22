package com.farmacia.farmacia.service;


import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.UsuarioRepository;
import com.farmacia.farmacia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteService clienteService;

    public Venta agregarVenta(float totalVenta, Long idCliente){
        Venta venta = new Venta();
        venta.setTotalVenta(totalVenta);
        venta.setCliente(this.clienteService.obtenerCliente(idCliente));
        venta.setFechaHoraVenta(LocalDateTime.now());
        String username = SecurityContextHolder.getContext().getAuthentication().
        Optional<Usuario> oUsuario = usuarioRepository.findByNombre(username);
        if(oUsuario.isPresent()){
            Empleado empleado = oUsuario.get().getEmpleado();
            venta.setEmpleado(empleado);
            return this.ventaRepository.save(venta);
        }
        System.out.println("VENTA NO REGISTRADA PORQUE EL USUARIO NO ESTA AUTENTIFICADO");
        return null;
    }



}
