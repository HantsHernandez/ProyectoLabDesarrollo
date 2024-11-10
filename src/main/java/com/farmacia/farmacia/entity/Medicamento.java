package com.farmacia.farmacia.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "medicamentos")
public class Medicamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMedicamentos;

    private String nombreMedicamento;

    private String formulaMedicamento;

    private String descripcionMedicamento;

    private String dosisMedicametno;

    private LocalDate fechaVencimiento;

    private float precioCompra;

    private float precioVenta;

    @ManyToOne
    @JoinColumn(name = "id_inventariio")
    private Inventario inventario;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "id_marca")
    private Marca marca;

    @ManyToOne
    @JoinColumn(name = "id_presentacion_medicamentos")
    private PresentacionMedicamento presentacionMedicamento;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> listaDetalleVenta;

    @OneToMany(mappedBy = "medicamento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra> listaDetalleCompra;
}
