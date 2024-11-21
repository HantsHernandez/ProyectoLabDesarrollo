package com.farmacia.farmacia.DTO;


import com.farmacia.farmacia.entity.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MedicamentoInventarioDTO {

    private Medicamento medicamento;

    private Inventario inventario;

}
