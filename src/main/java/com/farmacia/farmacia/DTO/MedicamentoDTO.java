package com.farmacia.farmacia.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicamentoDTO {

    private String nombre;

    private String formula;

    private String descripcion;

    private String dosis;

    private LocalDate fechaVencimiento;

    private float precioCompra;

    private float precioVenta;

    private String presentacion;

    private String marca;
}
