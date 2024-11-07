package com.Farmacia.ProyectoDesarrollo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "detallesCompra")
public class DetalleCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleCompra;

    private int cantidadComprada;

    private float precioUnitario;

    private float subTotalCompra;

    @ManyToOne()
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

    @ManyToOne()
    @JoinColumn(name = "id_compra")
    private Compra compra;

    @ManyToOne
    @JoinColumn(name = "id_factura_compra")
    private FacturaCompra facturaCompra;

}
