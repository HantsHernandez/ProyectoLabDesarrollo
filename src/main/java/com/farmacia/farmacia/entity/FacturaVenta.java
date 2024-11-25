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
@Table(name = "facturasVenta")
public class FacturaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacturaVenta;

    private String numeroFactura;

    private String fechaHoraFacturacion;

    @OneToOne()
    @JoinColumn(name = "id_venta", nullable = false)
    private Venta venta;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPago metodoPago;

}
