package com.farmacia.farmacia.entity;


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
@Table(name = "detalleVenta")
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
    @JoinColumn(name = "id_factura_venta")
    private FacturaVenta facturaVenta;

    @ManyToOne()
    @JoinColumn(name = "id_medicamento")
    private Medicamento medicamento;

}
