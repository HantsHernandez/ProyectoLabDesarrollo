package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentosService {

    @Autowired
    private MedicamentoRepository medicamentoRepository;

    public Medicamento agregarMedicamento(Medicamento medicamento){
        return this.medicamentoRepository.save(medicamento);
    }

    public Medicamento obtenerMedicamento(Long id){
        return this.medicamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("MEDICAMENTO NO IDENTIFICADO"));
    }

    public void actualizarMedicamento(Medicamento nuevoMedicamento){
        Medicamento medicamento = this.medicamentoRepository.findById(nuevoMedicamento.getIdMedicamentos())
                .map(viejoMedicamento ->{
                    viejoMedicamento.setNombreMedicamento(nuevoMedicamento.getNombreMedicamento());
                    viejoMedicamento.setFormulaMedicamento(nuevoMedicamento.getFormulaMedicamento());
                    viejoMedicamento.setDescripcionMedicamento(nuevoMedicamento.getDescripcionMedicamento());
                    viejoMedicamento.setDosisMedicamento(nuevoMedicamento.getDosisMedicamento());
                    viejoMedicamento.setFechaVencimiento(nuevoMedicamento.getFechaVencimiento());
                    viejoMedicamento.setPrecioCompra(nuevoMedicamento.getPrecioCompra());
                    viejoMedicamento.setPrecioVenta(nuevoMedicamento.getPrecioVenta());
                    viejoMedicamento.setInventario(nuevoMedicamento.getInventario());
                    return viejoMedicamento;
                })
                .orElseThrow(() -> new RuntimeException("MEDICAMENTO NO IDENTIFICADO"));
        this.medicamentoRepository.save(medicamento);
    }

    public void eliminarCategoria(Long id){
        this.medicamentoRepository.findById(id)
                .ifPresentOrElse(
                        marca -> this.medicamentoRepository.delete(marca),
                        () -> new RuntimeException("MEDICAMENTO NO IDENTIFICADO")
                );
    }

    public List<Medicamento> listaMedicamentos(){
        return this.medicamentoRepository.findAll();
    }
}
