package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.PresentacionMedicamento;
import com.farmacia.farmacia.repository.PresentacionMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PresentacionMedicamentosService {

    @Autowired
    private PresentacionMedicamentoRepository presentacionMedicamentoRepository;

    public void agregarPresentacionMedicamento(PresentacionMedicamento presentacionMedicamento){
        this.presentacionMedicamentoRepository.save(presentacionMedicamento);
    }

    public PresentacionMedicamento obtenerPresentacionMedicamentos(Long id){
        return this.presentacionMedicamentoRepository.findById(id).orElseThrow(() -> new RuntimeException("PRESENTACION DE MEDICAMENTOS NO IDENTIFICADA"));
    }

    public void actualizarPresentacionMedicamento(PresentacionMedicamento nuevaPresentacionMedicamento){
        PresentacionMedicamento presentacionMedicamento = this.presentacionMedicamentoRepository.findById(nuevaPresentacionMedicamento.getIdPresentacionMedicamento())
                .map(viejaPresentacionMedicamento ->{
                    viejaPresentacionMedicamento.setNombrePresentacion(nuevaPresentacionMedicamento.getNombrePresentacion());
                    viejaPresentacionMedicamento.setDescripcionPresentacion(nuevaPresentacionMedicamento.getDescripcionPresentacion());
                    return viejaPresentacionMedicamento;
                })
                .orElseThrow(() -> new RuntimeException("PRESENTACION DE MEDICAMENTOS NO IDENTIFICADA"));
        this.presentacionMedicamentoRepository.save(presentacionMedicamento);
    }

    public void eliminarPresentacionMedicamentos(Long id){
        this.presentacionMedicamentoRepository.findById(id)
                .ifPresentOrElse(
                        marca -> this.presentacionMedicamentoRepository.delete(marca),
                        () -> new RuntimeException("PRESENTACION DE MEDICAMENTOS NO IDENTIFICADA")
                );
    }

    public List<PresentacionMedicamento> listaPresentacionMedicamentos(){
        return this.presentacionMedicamentoRepository.findAll();
    }

    public Page<PresentacionMedicamento> listaCategoriasPaginados(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.presentacionMedicamentoRepository.findAll(pageable);
    }
}
