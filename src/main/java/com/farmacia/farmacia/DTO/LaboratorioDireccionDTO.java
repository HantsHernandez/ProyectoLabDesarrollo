package com.farmacia.farmacia.DTO;

import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Laboratorio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LaboratorioDireccionDTO {

    private Laboratorio laboratorio;

    private Direccion direccion;
}
