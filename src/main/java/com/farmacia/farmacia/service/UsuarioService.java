package com.farmacia.farmacia.service;


import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario= usuarioRepository.
                findByNombre(username)
                .orElseThrow(() -> new RuntimeException("USUARIO NO IDENTIFICADO"));
        System.out.println("Usuario encontrado: " + usuario.getUsername());

        return  usuario;

    }

    public void agregarUsuario(Usuario usuario) {
        // Cifrar la contraseña antes de guardarla
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        this.usuarioRepository.save(usuario);
    }

    // Método para obtener un usuario por ID
    public Usuario obtenerUsuario(Long id) {
        return this.usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("USUARIO NO IDENTIFICADO"));
    }

    // Método para actualizar un usuario
    public Usuario actualizarUsuario(Usuario nuevoUsuario) {
        return this.usuarioRepository.save(
                this.usuarioRepository.findById(nuevoUsuario.getId())
                        .map(usuario -> {
                            usuario.setNombre(nuevoUsuario.getNombre());
                            usuario.setEmpleado(nuevoUsuario.getEmpleado());
                            // Si la contraseña se ha actualizado, se cifra antes de almacenarla
                            if (nuevoUsuario.getPassword() != null && !nuevoUsuario.getPassword().isEmpty()) {
                                usuario.setPassword(passwordEncoder.encode(nuevoUsuario.getPassword()));
                            }
                            return usuario;
                        })
                        .orElseThrow(() -> new RuntimeException("USUARIO NO IDENTIFICADO"))
        );
    }

    // Método para eliminar un usuario
    public void eliminarUsuario(Long id) {
        this.usuarioRepository.findById(id)
                .ifPresentOrElse(
                        usuario -> this.usuarioRepository.delete(usuario),
                        () -> new RuntimeException("USUARIO NO IDENTIFICADO")
                );
    }

    // Obtener lista de usuarios
    public List<Usuario> listaUsuarios() {
        return this.usuarioRepository.findAll();
    }

    // Obtener lista paginada de usuarios
    public Page<Usuario> listaUsuarioPaginados(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return this.usuarioRepository.findAll(pageable);
    }

}




   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByna(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol rol : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol().name()));
        }

        return new User(usuario.getCorreo(), usuario.getPassword(), authorities);
    }*/

