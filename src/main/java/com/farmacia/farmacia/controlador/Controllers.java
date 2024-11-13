package com.farmacia.farmacia.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Controllers {

    @GetMapping("/fragmentoClientes")
    public String fragmentoClientes() { return "fragments/GestionClientes :: contenido"; }

    @GetMapping("/fragmentoSucursales")
    public String fragmentoSucursales() {
        return "fragments/GestionSucursales :: contenido";
    }

    @GetMapping("/dashboard")
    public String mostrarDashboard() {
        return "index";
    }
/*
    @GetMapping("/fragmentoCategorias")
    public String fragmentoCategorias() {
        return "fragments/GestionCategorias :: contenido";
    }

    @GetMapping("/fragmentoMarcas")
    public String fragmentoMarcas() {
        return "fragments/GestionMarcas :: contenido";
    }
*/
    @GetMapping("/fragmentoPresentacionMedicamentos")
    public String fragmentoPresentacionMedicamentos() {
        return "fragments/GestionPresentacionMedicamentos :: contenido";
    }
/*
    @GetMapping("/fragmentoMedicamentos")
    public String fragmentoMedicamentos() {
        return "fragments/GestionMedicamentos :: contenido";
    }
*/
    @GetMapping("/fragmentoDirecciones")
    public String fragmentoDirecciones() {
        return "fragments/GestionDirecciones :: contenido";
    }

    @PostMapping("/empleados/agregar")
    public String guardarEmpleado() {
        //guardar informacion

        //Obtener los registros y enviarlos a la vista
        return "fragments/GestionEmpleados";
    }

    // Ruta para mostrar el formulario de agregar empleado
    @GetMapping("/nuevoEmpleado")
    public String mostrarFormularioEmpleado() {
        return "fragments/AgregarEmpleado :: contenido";
    }

    @GetMapping("/nuevoCliente")
    public String mostrarFormularioCliente() {
        return "fragments/AgregarCliente :: contenido";
    }

    @GetMapping("/nuevaSucursal")
    public String mostrarFormularioSucursal() {
        return "fragments/AgregarSucursal :: contenido";
    }
/*
    @GetMapping("/nuevaCategoria")
    public String mostrarFormularioCategoria() {
        return "fragments/AgregarCategoria :: contenido";
    }

    @GetMapping("/nuevaMarca")
    public String mostrarFormularioMarca() {
        return "fragments/AgregarMarca:: contenido";
    }
*/
    @GetMapping("/nuevaPresentacion")
    public String mostrarFormularioPresentacion() {
        return "fragments/AgregarPresentacionMedicamento :: contenido";
    }

    /*
    @GetMapping("/nuevoMedicamento")
    public String mostrarFormularioMedicamento() {
        return "fragments/AgregarMedicamento :: contenido";
    }
*/
    @GetMapping("/nuevaDireccion")
    public String mostrarFormularioDireccion() {
        return "fragments/AgregarDireccion :: contenido";
    }
}
