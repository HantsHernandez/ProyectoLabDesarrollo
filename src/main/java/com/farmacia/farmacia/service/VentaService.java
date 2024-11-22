package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.entity.Venta;
import com.farmacia.farmacia.repository.CategoriaRepository;
import com.farmacia.farmacia.repository.UsuarioRepository;
import com.farmacia.farmacia.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void agregarVenta(Venta venta){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Usuario> oUsuario = usuarioRepository.findByNombre(username);
        if(oUsuario.isPresent()){
            Empleado empleado = oUsuario.get().getEmpleado();
            venta.setEmpleado(empleado);
            this.ventaRepository.save(venta);
        }
        System.out.println("VENTA NO REGISTRADA PORQUE EL USUARIO NO ESTA AUTENTIFICADO");
    }

}
