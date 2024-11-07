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
@Table(name = "facturasVenta")
public class FacturaVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacturaVenta;

    private String fechaHoraFacturacion;

    private float ivaFacturaVenta;

    private float credictoFiscalFacturaVenta;

    @ManyToOne
    @JoinColumn(name = "id_metodo_pago")
    private MetodoPago metodoPago;

    @OneToMany(mappedBy = "facturaVenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta>listaDetalleVenta;
}
