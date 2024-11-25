package com.farmacia.farmacia.service;

import com.farmacia.farmacia.DTO.MedicamentoDTO;
import com.farmacia.farmacia.DTO.MedicamentoInventarioDTO;
import com.farmacia.farmacia.entity.Inventario;
import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
public class MedicamentosService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    @Autowired
    private InventarioService inventarioService;

    @Autowired
    private MarcaService marcaService;

    @Autowired
    private PresentacionMedicamentosService presentacionMedicamentosService;

    @Autowired
    private CategoriaService categoriaService;

    public Medicamento agregarMedicamento(MedicamentoInventarioDTO medicamentoDTO){
        Inventario inventario = this.inventarioService.agregarInventario(medicamentoDTO.getInventario());
        medicamentoDTO.getMedicamento().setInventario(inventario);
        return this.medicamentoRepository.save(medicamentoDTO.getMedicamento());
    }

    public Medicamento obtenerMedicamento(Long id){
        return this.medicamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("MEDICAMENTO NO IDENTIFICADO"));
    }

    public Medicamento actualizarMedicamento(MedicamentoInventarioDTO medicamentoDTO){
        Inventario inventario = this.inventarioService.actualizarInventario(medicamentoDTO.getInventario());
        medicamentoDTO.getMedicamento().setInventario(inventario);
        Medicamento medicamentoActualizado = medicamentoDTO.getMedicamento();
        return this.medicamentoRepository.save(this.medicamentoRepository.findById(medicamentoActualizado.getIdMedicamentos())
                        .map(medicamento -> {
                            medicamento.setNombreMedicamento(medicamentoActualizado.getNombreMedicamento());
                            medicamento.setFormulaMedicamento(medicamentoActualizado.getFormulaMedicamento());
                            medicamento.setDescripcionMedicamento(medicamentoActualizado.getDescripcionMedicamento());
                            medicamento.setDosisMedicamento(medicamentoActualizado.getDosisMedicamento());
                            medicamento.setFechaVencimiento(medicamentoActualizado.getFechaVencimiento());
                            medicamento.setPrecioCompra(medicamentoActualizado.getPrecioCompra());
                            medicamento.setPrecioVenta(medicamentoActualizado.getPrecioVenta());
                            medicamento.setInventario(medicamentoActualizado.getInventario());
                            if(medicamento.getUrlImagen().equals("/img/imgMedPredeterminado.jpg") && medicamentoActualizado.getUrlImagen() != null){
                                medicamento.setUrlImagen(medicamentoActualizado.getUrlImagen());
                            }else if(!medicamento.getUrlImagen().equals("/img/imgEmpPredeterminado.jpg") && medicamentoActualizado.getUrlImagen() != null) {
                                Path path = Paths.get("C:/aplicacionFarmacia" + medicamento.getUrlImagen());
                                if(Files.exists(path)){
                                    try {
                                        Files.delete(path);
                                        medicamento.setUrlImagen(medicamentoActualizado.getUrlImagen());
                                    } catch (IOException e) {
                                        throw new RuntimeException("Error al eliminar la imagen anterior", e);
                                    }
                                }
                            }
                            return medicamento;
                        }).orElseThrow(() -> new RuntimeException("MEDICAMENTO NO IDENTIFICADO")));
    }

    public void eliminarMedicamento(Long idMedicamento){
        this.medicamentoRepository.findById(idMedicamento)
                .ifPresentOrElse(
                        medicamento -> {
                            Long idInventario = medicamento.getInventario().getIdInventario();
                            this.medicamentoRepository.delete(medicamento);
                            this.inventarioService.eliminarInventario(idInventario);
                        },
                        () -> new RuntimeException("MEDICAMENTO NO IDENTIFICADO")
                );
    }

    public List<Medicamento> listaMedicamentos(){
        return this.medicamentoRepository.findAll();
    }

    public Page<Medicamento> listaCategoriasPaginados(int page, int size, String palabraBuscar){
        Pageable pageable = PageRequest.of(page, size);
        if(palabraBuscar != null){
            return this.medicamentoRepository.findAll(palabraBuscar, pageable);
        }
        return this.medicamentoRepository.findAll(pageable);
    }

    public MedicamentoDTO obtenerMedicamentoDTO(Long id){
        Medicamento medicamento = this.obtenerMedicamento(id);
        MedicamentoDTO medicamentoDTO = new MedicamentoDTO();
        medicamentoDTO.setNombre(medicamento.getNombreMedicamento());
        medicamentoDTO.setFormula(medicamento.getFormulaMedicamento());
        medicamentoDTO.setDescripcion(medicamento.getDescripcionMedicamento());
        medicamentoDTO.setDosis(medicamento.getDosisMedicamento());
        medicamentoDTO.setFechaVencimiento(medicamento.getFechaVencimiento());
        medicamentoDTO.setPrecioCompra(medicamento.getPrecioCompra());
        medicamentoDTO.setPrecioVenta(medicamento.getPrecioVenta());
        medicamentoDTO.setMarca(medicamento.getMarca().getNombreMarca());
        medicamentoDTO.setPresentacion(medicamento.getPresentacion().getNombrePresentacion());
        medicamentoDTO.setDosis(medicamento.getDosisMedicamento());
        return medicamentoDTO;
    }

    public Medicamento actualizarStock(Long id, int cantidadComprada){
        Medicamento medicamento = this.obtenerMedicamento(id);
        int cantidadStockActualizada = medicamento.getInventario().getCantidadStock() - cantidadComprada;
        medicamento.getInventario().setCantidadStock(cantidadStockActualizada);
        this.medicamentoRepository.save(medicamento);
        return  medicamento;
    }

}
