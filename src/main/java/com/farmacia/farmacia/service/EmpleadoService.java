package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpleadoService {
    @Autowired
    private DireccionService direccionService;

    @Autowired
    private EmpleadoRepository empleadoRepository;

    public void guardarEmpleado(Empleado empleado){
        this.empleadoRepository.save(empleado);
    }

    public Empleado obtenerEmpleado(Long id){
        return this.empleadoRepository.findById(id).orElseThrow(() -> new RuntimeException("USUARIO NO ENCONTRADO"));
    }

    public void actualizarEmpleado(Empleado nuevoEmpleado){
        Empleado empleado = this.empleadoRepository.findById(nuevoEmpleado.getIdEmpleado())
                .map(viejoEmpleado -> {
                    viejoEmpleado.setNombreEmpleado(nuevoEmpleado.getNombreEmpleado());
                    viejoEmpleado.setApellidoEmpleado(nuevoEmpleado.getApellidoEmpleado());
                    viejoEmpleado.setCorreoEmpleado(nuevoEmpleado.getCorreoEmpleado());
                    viejoEmpleado.setDuiEmpleado(nuevoEmpleado.getDuiEmpleado());
                    viejoEmpleado.setGeneroEmpleado(nuevoEmpleado.getGeneroEmpleado());
                    viejoEmpleado.setIsssEmpleado(nuevoEmpleado.getIsssEmpleado());
                    viejoEmpleado.setCargo(nuevoEmpleado.getCargo());
                    viejoEmpleado.setFechaNacEmpleado(nuevoEmpleado.getFechaNacEmpleado());
                    viejoEmpleado.setTelefonoEmpleado(nuevoEmpleado.getTelefonoEmpleado());
                    viejoEmpleado.setDireccion(nuevoEmpleado.getDireccion());
                    return viejoEmpleado;
                }).orElseThrow(() -> new RuntimeException("USUARIO NO ENCONTRADO"));
        this.empleadoRepository.save(empleado);
    }

    public void eliminarEmpleado(Long id){
        this.empleadoRepository.findById(id)
                .ifPresentOrElse(
                        empleado -> this.empleadoRepository.delete(empleado),
                        () -> new RuntimeException("USUARIO NO ENCONTRADO")
                );
    }

    public List<Empleado> listaEmpleados(){
        return this.empleadoRepository.findAll();
    }

}
