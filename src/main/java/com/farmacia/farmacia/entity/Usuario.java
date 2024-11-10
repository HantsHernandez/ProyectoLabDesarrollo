package com.farmacia.farmacia.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "usuarios")
public class Usuario implements UserDetails{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String correo;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER, targetEntity = Rol.class)
    @JoinTable(
            name = "usuarioRoles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private Set<Rol> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(rol ->{
                    System.out.println("ROL: " + rol.getRol().name());
                    return new SimpleGrantedAuthority(rol.getRol().name());
                    //return new SimpleGrantedAuthority("ROLE_" + rol.getRol().name());
                })
                .toList();
    }

    @Override
    public String getUsername() {
        return correo;
    }
}
