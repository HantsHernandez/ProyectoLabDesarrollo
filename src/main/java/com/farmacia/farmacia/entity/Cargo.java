package com.farmacia.farmacia.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "cargos")
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCargo;

    private String cargo;

    private String descripcionCargo;

    @JsonManagedReference
    @OneToMany(mappedBy = "cargo", cascade = CascadeType.ALL,orphanRemoval = true)
    private List<Empleado>listaEmpleados;
}
