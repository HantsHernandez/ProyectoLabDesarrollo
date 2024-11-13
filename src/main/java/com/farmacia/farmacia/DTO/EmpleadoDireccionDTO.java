package com.farmacia.farmacia.DTO;

import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Empleado;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EmpleadoDireccionDTO {

    private Empleado empleado;

    private Direccion direccion;

}
