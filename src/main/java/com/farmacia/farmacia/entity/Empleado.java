package com.farmacia.farmacia.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Setter
@Table(name = "empleados")
public class Empleado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmpleado;

    private String nombreEmpleado;

    private String apellidoEmpleado;

    private String duiEmpleado;

    private String isssEmpleado;

    private LocalDate fechaNacEmpleado;

    private String telefonoEmpleado;

    private String generoEmpleado;

    private String correoEmpleado;

    @ManyToOne
    @JoinColumn(name = "id_direccion")
    private Direccion direccion;

    @ManyToOne
    @JoinColumn(name = "id_cargo")
    private Cargo cargo;

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Venta>listaVenta = new ArrayList<>();

    @OneToMany(mappedBy = "empleado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario>listaUsuarios;
}
