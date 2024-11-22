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
public class EmpleadoDTO {

    private String nombre;

    private String apellido;

    private String dui;

    private String isss;

    private LocalDate fechaNac;

    private String telefono;

    private String genero;

    private String correo;

    private String cargo;

    private String direccion;
}
