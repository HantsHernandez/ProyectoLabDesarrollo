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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


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
    public String guardarUsuario(Usuario usuario, RedirectAttributes redirectAttributes) {
        this.usuarioService.agregarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "El Usuario Fue Guardado Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "agregar");
        return "redirect:/fragmentoUsuario";
    }

    @PostMapping("/actualizar-usuario")
    public String actualizarUsuario(Usuario usuario, RedirectAttributes redirectAttributes){
        this.usuarioService.actualizarUsuario(usuario);
        redirectAttributes.addFlashAttribute("mensaje", "El Usuario Fue Actualizado Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "actualizar");
        return "redirect:/fragmentoUsuario";
    }

    @GetMapping("/eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable Long id, RedirectAttributes redirectAttributes){
        this.usuarioService.eliminarUsuario(id);
        redirectAttributes.addFlashAttribute("mensaje", "El Usuario fue Eliminado Exitosamente");
        redirectAttributes.addFlashAttribute("tipoAlerta", "eliminar");
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
