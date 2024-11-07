package com.Farmacia.ProyectoDesarrollo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "inventarios")
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idInventario;

    private int cantidadStock;

    private int noEstanteria;

    private int noPasillo;

    @OneToMany(mappedBy = "inventario",  cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento> listaMedicamentos;
}
