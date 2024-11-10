package com.farmacia.farmacia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "direcciones")
public class Direccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDireccion;

    private String linea1;

    private String linea2;

    @OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Empleado>listaEmpleados;

    @OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Cliente>listaClientes;

    @OneToMany(mappedBy = "direccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Laboratorio>listaLaboratorios;
}
