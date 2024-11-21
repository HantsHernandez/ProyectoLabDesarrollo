package com.farmacia.farmacia.controller;


import com.farmacia.farmacia.entity.Usuario;
import com.farmacia.farmacia.repository.RolRepository;
import com.farmacia.farmacia.service.EmpleadoService;
import com.farmacia.farmacia.service.RolService;
import com.farmacia.farmacia.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsuarioController {

    @Autowired
    private EmpleadoService empleadoServi1ce;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private RolService rolService;

    // CRUD
    @PostMapping("/guardar-usuario")
    public String guardarUsuario(Usuario usuario) {
        this.usuarioService.agregarUsuario(usuario);
        return "redirect:/fragmentoUsuario";
    }

    @PostMapping("/actualizar-usuario")
    public String actualizarUsuario(Usuario usuario){
        this.usuarioService.actualizarUsuario(usuario);
        return "redirect:/fragmentoUsuario";
    }

    @GetMapping("/eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable Long id){
        this.usuarioService.eliminarUsuario(id);
        return "redirect:/fragmentoUsuario";
    }

    // Vistas

    @GetMapping("/guardar-usuario")
    public String mostrarFormularioGuardar(Model model) {
        model.addAttribute("usuario", null);
        model.addAttribute("listaEmpleados", this.empleadoServi1ce.listaEmpleados());
        model.addAttribute("listaRoles", this.rolService.listaRoles());
        System.out.println("ROLES: ");
        this.rolService.listaRoles().forEach(rol -> System.out.println(rol));
        return "fragments/AgregarUsuario :: contenido";
    }

    @GetMapping("/actualizar-usuario/{id}")
    public String mostrarFormularioActualizar(Model model, @PathVariable Long id) {
        model.addAttribute("usuario", this.usuarioService.obtenerUsuario(id));
        model.addAttribute("listaEmpleados", this.empleadoServi1ce.listaEmpleados());
        model.addAttribute("listaRoles", this.rolService.listaRoles());
        System.out.println("ROLES: ");
        this.rolService.listaRoles().forEach(rol -> System.out.println(rol));
        return "fragments/AgregarUsuario :: contenido";
    }
}
