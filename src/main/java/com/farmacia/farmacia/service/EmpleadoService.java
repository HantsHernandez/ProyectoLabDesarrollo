package com.farmacia.farmacia.service;

import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {

    @Autowired
    private EmpleadoRepository empleadoRepository;

    @Autowired
    private DireccionService direccionService;

    public Empleado guardarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO){
        Direccion direccion = this.direccionService.guardarDireccion(empleadoDireccionDTO.getDireccion());
        empleadoDireccionDTO.getEmpleado().setDireccion(direccion);
        return this.empleadoRepository.save(empleadoDireccionDTO.getEmpleado());
    }

    public Empleado obtenerEmpleado(Long id){
        return this.empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("USUARIO NO ENCONTRADO"));
    }

    public Empleado actualizarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO){
        Direccion direccion = this.direccionService.actualizarDireccion(empleadoDireccionDTO.getDireccion());
        empleadoDireccionDTO.setDireccion(direccion);
        Empleado empleadoActualizado = empleadoDireccionDTO.getEmpleado();
        return this.empleadoRepository.save(this.empleadoRepository.findById(empleadoActualizado.getIdEmpleado())
                .map(empleado -> {
                    empleado.setNombreEmpleado(empleadoActualizado.getNombreEmpleado());
                    empleado.setApellidoEmpleado(empleadoActualizado.getApellidoEmpleado());
                    empleado.setCorreoEmpleado(empleadoActualizado.getCorreoEmpleado());
                    empleado.setDuiEmpleado(empleadoActualizado.getDuiEmpleado());
                    empleado.setGeneroEmpleado(empleadoActualizado.getGeneroEmpleado());
                    empleado.setIsssEmpleado(empleadoActualizado.getIsssEmpleado());
                    empleado.setCargo(empleadoActualizado.getCargo());
                    empleado.setFechaNacEmpleado(empleadoActualizado.getFechaNacEmpleado());
                    empleado.setTelefonoEmpleado(empleadoActualizado.getTelefonoEmpleado());
                    empleado.setDireccion(empleadoActualizado.getDireccion());
                    return empleado;
                }).orElseThrow(() -> new RuntimeException("USUARIO NO ENCONTRADO")));
    }

    public void eliminarEmpleado(Long id){
        this.empleadoRepository.findById(id)
                .ifPresentOrElse(
                        empleado -> {
                            this.direccionService.eliminarDireccion(empleado.getDireccion().getIdDireccion());
                            this.empleadoRepository.delete(empleado);
                        },
                        () -> new RuntimeException("USUARIO NO ENCONTRADO")
                );
    }

    public List<Empleado> listaEmpleados(){
        return this.empleadoRepository.findAll();
    }

    public Page<Empleado> listaEmpleadosPaginados(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.empleadoRepository.findAll(pageable);
    }

}
