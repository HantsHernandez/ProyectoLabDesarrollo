package com.farmacia.farmacia.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ClienteDTO {

    private String nombres;

    private String apellidos;

    private String telefono;

    private String dui;

    private String edad;

    private String correo;

    private String direccion;

}
