package com.farmacia.farmacia.service;

import com.farmacia.farmacia.DTO.ClienteDireccionDTO;
import com.farmacia.farmacia.DTO.LaboratorioDireccionDTO;
import com.farmacia.farmacia.entity.Cliente;
import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Laboratorio;
import com.farmacia.farmacia.repository.LaboratorioReppsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratorioService {

    @Autowired
    private LaboratorioReppsitory laboratorioReppsitory;

    @Autowired
    private DireccionService direccionService;

    public Laboratorio guardarLaboratorio(LaboratorioDireccionDTO laboratorioDireccionDTO){
        Direccion direccion = this.direccionService.guardarDireccion(laboratorioDireccionDTO.getDireccion());
        laboratorioDireccionDTO.getLaboratorio().setDireccion(direccion);
        return this.laboratorioReppsitory.save(laboratorioDireccionDTO.getLaboratorio());
    }

    public Laboratorio obtenerLaboratorio(Long id){
        return this.laboratorioReppsitory.findById(id).orElseThrow(() -> new RuntimeException("LABORATORIO NO ENCONTRADO"));
    }

    public Laboratorio actualizarLaboratorio(LaboratorioDireccionDTO laboratorioDireccionDTO){
        Direccion direccion = this.direccionService.actualizarDireccion(laboratorioDireccionDTO.getDireccion());
        laboratorioDireccionDTO.getLaboratorio().setDireccion(direccion);
        Laboratorio laboratorioActualizado = laboratorioDireccionDTO.getLaboratorio();
        return this.laboratorioReppsitory.save(this.laboratorioReppsitory.findById(laboratorioActualizado.getIdLaboratorio())
                .map(laboratorio -> {
                    laboratorio.setNombreLaboratorio(laboratorioActualizado.getNombreLaboratorio());
                    laboratorio.setNumeroContacto(laboratorioActualizado.getNumeroContacto());
                    laboratorio.setDireccion(laboratorioActualizado.getDireccion());
                    return laboratorio;
                }).orElseThrow(() -> new RuntimeException("LABORATORIO NO ENCONTRADO")));
    }

    public void eliminarLaboratorio(Long id){
        this.laboratorioReppsitory.findById(id)
                .ifPresentOrElse(
                        laboratorio -> {
                            this.direccionService.eliminarDireccion(laboratorio.getDireccion().getIdDireccion());
                            this.laboratorioReppsitory.delete(laboratorio);
                        },
                        () -> new RuntimeException("LABORATORIO NO ENCONTRADO")
                );
    }

    public List<Laboratorio> listaLaboratorios(){
        return this.laboratorioReppsitory.findAll();
    }

    public Page<Laboratorio> listaLaboratoriosPaginados(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.laboratorioReppsitory.findAll(pageable);
    }

}
