package com.farmacia.farmacia.entity;

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
@Table(name = "PresentacionMedicamentos")
public class PresentacionMedicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPresentacionMedicamento;

    private String nombrePresentacion;

    private String descripcionPresentacion;

    @OneToMany(mappedBy = "presentacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Medicamento>listaMedicamentos;
}
