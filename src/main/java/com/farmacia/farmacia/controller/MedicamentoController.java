package com.farmacia.farmacia.controller;

import com.farmacia.farmacia.DTO.MedicamentoDTO;
import com.farmacia.farmacia.DTO.MedicamentoInventarioDTO;
import com.farmacia.farmacia.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

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
    public String guardarMedicamento(MedicamentoInventarioDTO medicamentoDTO,
                                     @RequestParam(required = false)MultipartFile imagen) throws IOException {
        if(imagen.isEmpty()){
            medicamentoDTO.getMedicamento().setUrlImagen("/img/imgMedPredeterminado.jpg");
        }else{
            String direccionImagen = UUID.randomUUID().toString() + imagen.getOriginalFilename();
            Path path = Paths.get("C:/aplicacionFarmacia/img/" + direccionImagen);
            System.out.println("valor"  + imagen.getOriginalFilename());
            System.out.println("Valor"  + imagen.getContentType());
            Files.write(path, imagen.getBytes());
            medicamentoDTO.getMedicamento().setUrlImagen("/img/" + direccionImagen);
        }
        this.medicamentosService.agregarMedicamento(medicamentoDTO);
        return "redirect:/fragmentoMedicamentos";
    }

    @PostMapping("/actualizar-medicamento")
    public String actualizarEmpleado(MedicamentoInventarioDTO medicamentoDTO,
                                     @RequestParam(required = false)MultipartFile imagen) throws IOException {
        if(!imagen.isEmpty()){
            String direccion = UUID.randomUUID().toString() + imagen.getOriginalFilename();
            Path path = Paths.get("C:/aplicacionFarmacia/img/" + direccion);
            Files.write(path, imagen.getBytes());
            medicamentoDTO.getMedicamento().setUrlImagen("/img/" + direccion);
        }
        this.medicamentosService.actualizarMedicamento(medicamentoDTO);
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


    @GetMapping("/medicamento/{id}")
    @ResponseBody
    public ResponseEntity<?> obtenerDetallesMedicamento(@PathVariable Long id) {
        MedicamentoDTO medicamento = medicamentosService.obtenerMedicamentoDTO(id);
        if (medicamento != null) {
            return ResponseEntity.ok(medicamento);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Medicamento no encontrado");
    }

}
