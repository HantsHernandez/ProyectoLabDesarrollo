package com.Farmacia.ProyectoDesarrollo.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "FacturasCompra")
public class FacturaCompra {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFacturaCompra;

    private LocalDateTime fechaHoraFacturacion;

    private float ivaFacturaCompra;

    private float creditoFiscalCompra;

    @OneToMany(mappedBy = "facturaCompra", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleCompra>listaDetallesCompra;

}
