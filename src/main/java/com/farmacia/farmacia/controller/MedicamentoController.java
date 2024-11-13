package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.DTO.MedicamentoInventarioDTO;
import com.farmacia.farmacia.entity.*;
import com.farmacia.farmacia.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MedicamentoController {

    @Autowired
    private MedicamentosService medicamentosService;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private PresentacionMedicamentosService presentacionMedicamentosService;

    @Autowired
    private CategoriaService categoriaService;

    private List<String> pasillos = List.of(
            "Pasillo 1: Medicamentos genéricos",
            "Pasillo 2: Suplementos y vitaminas",
            "Pasillo 3: Productos para el cuidado personal e higiene",
            "Pasillo 4: Equipo médico y suministros"
    );

    private List<String> estanterias = List.of(
            "Estanteria 1 Nivel superior",
            "Estanteria 1 Nivel intermedio",
            "Estanteria 1 Nivel bajo",
            "Estanteria 2 Nivel superior",
            "Estanderia 2 Nivel intermedio",
            "Estanteria 2 Nivel bajo"
    );

    // CRUD
    @PostMapping("/guardar-medicamento")
    public String guardarMedicamento(MedicamentoInventarioDTO medicamentoDTO) {
        System.out.println("Medicamento: " + medicamentoDTO.getMedicamento());
        System.out.println("Inventario: "  + medicamentoDTO.getInventario());

        Inventario inventario = this.inventarioService.agregarInventario(medicamentoDTO.getInventario());
        medicamentoDTO.getMedicamento().setInventario(inventario);
        Medicamento medicamento = this.medicamentosService.agregarMedicamento(medicamentoDTO.getMedicamento());
        inventario.getListaMedicamentos().add(medicamento);
        return "redirect:/fragmentoMedicamentos";
    }

    @PostMapping("/actualizar-medicamento")
    public String actualizarEmpleado(MedicamentoInventarioDTO medicamentoInventarioDTO){
        Inventario inventario = this.inventarioService.actualizarInventario(medicamentoInventarioDTO.getInventario());
        medicamentoInventarioDTO.getMedicamento().setInventario(inventario);
        this.medicamentosService.actualizarMedicamento(medicamentoInventarioDTO.getMedicamento());
        return "redirect:/fragmentoMedicamentos";
    }

    @GetMapping("/eliminar-medicamento/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        Long idInventario = this.medicamentosService.obtenerMedicamento(id).getInventario().getIdInventario();
        this.medicamentosService.eliminarCategoria(id);
        this.inventarioService.eliminarInventario(idInventario);
        return "redirect:/fragmentoMedicamentos";
    }

    // Vistas

    // METODO QUE DEBE DE PASAR AL HOME
    @GetMapping("/fragmentoMedicamentos")
    public String fragmentoEmpleados(Model model) {
        model.addAttribute("listaMedicamentos", this.medicamentosService.listaMedicamentos());
        return "fragments/GestionMedicamentos :: contenido";
    }

    @GetMapping("/guardar-medicamento")
    public String mostrarFormularioMedicamento(Model model) {
        model.addAttribute("medicamento", null);
        model.addAttribute("listaCategorias", this.categoriaService.listaCategorias());
        model.addAttribute("listaMarcas", this.marcaService.listaMarcas());
        model.addAttribute("listaPresentacion", this.presentacionMedicamentosService.listaPresentacionMedicamentos());
        model.addAttribute("listaPasillos", this.pasillos);
        model.addAttribute("listaEstanterias", this.estanterias);
        return "fragments/AgregarMedicamento :: contenido";
    }

    @GetMapping("/actualizar-medicamento/{id}")
    public String mostrarFormularioActualizar(Model model, @PathVariable Long id) {
        System.out.println("INVENTARIO: ID" + this.medicamentosService.obtenerMedicamento(id).getInventario().getIdInventario());
        System.out.println("INVENTARIO: PAsillo: " + this.medicamentosService.obtenerMedicamento(id).getInventario().getPasillo());
        System.out.println("INVENTARIO: estanteria: " + this.medicamentosService.obtenerMedicamento(id).getInventario().getEstanteria());
        System.out.println("INVENTARIO: stock: " + this.medicamentosService.obtenerMedicamento(id).getInventario().getCantidadStock());

        model.addAttribute("medicamento", this.medicamentosService.obtenerMedicamento(id));
        //model.addAttribute("inventario", this.inventarioService.obtenerInventario())
        model.addAttribute("listaCategorias", this.categoriaService.listaCategorias());
        model.addAttribute("listaMarcas", this.marcaService.listaMarcas());
        model.addAttribute("listaPresentacion", this.presentacionMedicamentosService.listaPresentacionMedicamentos());
        model.addAttribute("listaPasillos", this.pasillos);
        model.addAttribute("listaEstanterias", this.estanterias);
        return "fragments/AgregarMedicamento :: contenido";
    }

}
