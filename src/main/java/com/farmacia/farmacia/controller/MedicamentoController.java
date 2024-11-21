package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.MedicamentoInventarioDTO;
import com.farmacia.farmacia.entity.*;
import com.farmacia.farmacia.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        this.medicamentosService.agregarMedicamento(medicamentoDTO);
        return "redirect:/fragmentoMedicamentos";
    }

    @PostMapping("/actualizar-medicamento")
    public String actualizarEmpleado(MedicamentoInventarioDTO medicamentoInventarioDTO){
        this.medicamentosService.actualizarMedicamento(medicamentoInventarioDTO);
        return "redirect:/fragmentoMedicamentos";
    }

    @GetMapping("/eliminar-medicamento/{id}")
    public String eliminarEmpleado(@PathVariable Long id){
        this.medicamentosService.eliminarMedicamento(id);
        return "redirect:/fragmentoMedicamentos";
    }

    // Vistas
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
        model.addAttribute("medicamento", this.medicamentosService.obtenerMedicamento(id));
        model.addAttribute("listaCategorias", this.categoriaService.listaCategorias());
        model.addAttribute("listaMarcas", this.marcaService.listaMarcas());
        model.addAttribute("listaPresentacion", this.presentacionMedicamentosService.listaPresentacionMedicamentos());
        model.addAttribute("listaPasillos", this.pasillos);
        model.addAttribute("listaEstanterias", this.estanterias);
        return "fragments/AgregarMedicamento :: contenido";
    }

}
