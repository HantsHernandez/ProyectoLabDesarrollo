package com.farmacia.farmacia.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "medicamentos")
@ToString
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamentos;

    private String nombreMedicamento;

    private String formulaMedicamento;

    private String descripcionMedicamento;

    private String dosisMedicamento;

    private LocalDate fechaVencimiento;

    private float precioCompra;

    private float precioVenta;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_inventario")
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "id_presentacion_medicamentos")
    private PresentacionMedicamento presentacion;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> listaDetalleVenta = new ArrayList<>();

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> listaDetalleCompra = new ArrayList<>();
}
