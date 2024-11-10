package com.farmacia.farmacia.service;


import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService implements UserDetailsService {

    @Autowired
    UsuarioRepository usuarioRepository;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.
                findByCorreo(username)
                .orElseThrow(() -> new RuntimeException("USUARIO NO IDENTIFICADO"));
    }
/*    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByCorreo(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }

        Set<GrantedAuthority> authorities = new HashSet<>();
        for (Rol rol : usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + rol.getRol().name()));
        }

        return new User(usuario.getCorreo(), usuario.getPassword(), authorities);
    }*/


    private void save(Usuario usuario){
        // hay que encriptar
        usuarioRepository.save(usuario);
    }

    private Usuario getUsuario(Long id){
        return this.usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    private void updateUsuario(Usuario newUsuario){
        Usuario usuario = this.usuarioRepository.findById(newUsuario.getId())
                .map(oldUsuario ->{
                    oldUsuario.setCorreo(newUsuario.getCorreo());
                    oldUsuario.setPassword(newUsuario.getPassword()); /// hay que encriptar
                    //manejar los roles
                    return oldUsuario;
                })
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        this.usuarioRepository.save(usuario);
    }

    private void delete(Long id){
        this.usuarioRepository.deleteById(id);
    }


}

