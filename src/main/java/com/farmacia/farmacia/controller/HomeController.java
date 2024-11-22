package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.entity.*;
import com.farmacia.farmacia.repository.UsuarioRepository;
import com.farmacia.farmacia.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {

    @Autowired
    private EmpleadoService empleadoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private LaboratorioService laboratorioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/index")
    public String getIndex(){
        return "index";
    }

    @GetMapping("/login")
    public String login(){
        return "Login";
    }

    @GetMapping("/fragmentoEmpleados")
    public String fragmentoEmpleados(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Empleado> paginacionEmpleados = empleadoService.listaEmpleadosPaginados(page, size);
        model.addAttribute("listaEmpleados", paginacionEmpleados.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionEmpleados.getTotalPages());
        return "fragments/GestionEmpleados :: contenido";
    }

    @GetMapping("/fragmentoCategorias")
    public String fragmentoCategorias(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Categoria> paginacionCategorias = this.categoriaService.listaCategoriasPaginados(page, size);
        model.addAttribute("listaCategorias", paginacionCategorias.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionCategorias.getTotalPages());
        return "fragments/GestionCategorias :: contenido";
    }

    @GetMapping("/fragmentoMarcas")
    public String fragmentoMarca(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Marca> paginacionMarca = this.marcaService.listaMarcasPaginados(page, size);
        model.addAttribute("listaMarcas", paginacionMarca.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionMarca.getTotalPages());
        return "fragments/GestionMarcas :: contenido";
    }

    @GetMapping("/fragmentoMedicamentos")
    public String fragmentoMedicamentos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            Model model) {
        Page<Medicamento> paginacionMedicamentos = medicamentosService.listaMedicamentosPaginados(page, size);
        model.addAttribute("listaMedicamentos", paginacionMedicamentos.getContent());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", paginacionMedicamentos.getTotalPages());
        return "fragments/GestionMedicamentos :: contenido";
    }

    @GetMapping("/fragmentoUsuario")
    public String fragmentoUsuario(Model model){
        model.addAttribute("listaUsuarios", this.usuarioService.listaUsuarios());
        return "fragments/GestionarUsuario :: contenido";
    }

    @GetMapping("/fragmentoClientes")
    public String fragmentoClientes(Model model) {
        model.addAttribute("listaClientes", this.clienteService.listaClientes());
        return "fragments/GestionClientes :: contenido";
    }

    @GetMapping("/fragmentoLaboratorios")
    public String fragmentoLaboratorio(Model model) {
        model.addAttribute("listaLaboratorio",  this.laboratorioService.listaLaboratorios());
        return "fragments/GestionLaboratorios :: contenido";
    }

}
