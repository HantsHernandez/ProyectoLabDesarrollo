package com.farmacia.farmacia.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "detallesVenta")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idDetalleVenta;

    private int cantidadVendida;

    private float precioUnitario;

    private float subTotalVendida;

    @ManyToOne()
    @JoinColumn(name = "id_venta")
    private Venta venta;

    @ManyToOne()
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

}
