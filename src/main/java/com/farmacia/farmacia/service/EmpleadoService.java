package com.farmacia.farmacia.service;

import com.farmacia.farmacia.DTO.EmpleadoDTO;
import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.entity.Categoria;
import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    public Empleado actualizarEmpleado(EmpleadoDireccionDTO empleadoDireccionDTO) {
        Direccion direccion = this.direccionService.actualizarDireccion(empleadoDireccionDTO.getDireccion());
        empleadoDireccionDTO.getEmpleado().setDireccion(direccion);
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
                    if(empleado.getUrlImagen().equals("/img/imgEmpPredeterminado.jpg") && empleadoActualizado.getUrlImagen() != null){
                        empleado.setUrlImagen(empleadoActualizado.getUrlImagen());
                    }else if(!empleado.getUrlImagen().equals("/img/imgEmpPredeterminado.jpg") && empleadoActualizado.getUrlImagen() != null) {
                        Path path = Paths.get("C:/aplicacionFarmacia" + empleado.getUrlImagen());
                        if(Files.exists(path)){
                            try {
                                Files.delete(path);
                                empleado.setUrlImagen(empleadoActualizado.getUrlImagen());
                            } catch (IOException e) {
                                throw new RuntimeException("Error al eliminar la imagen anterior", e);
                            }
                        }
                    }
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

    public Page<Empleado> listaEmpleadosPaginados(int page, int size, String palabraBuscar){
        Pageable pageable = PageRequest.of(page, size);
        if(palabraBuscar != null){
            return this.empleadoRepository.findAll(palabraBuscar, pageable);
        }
        return this.empleadoRepository.findAll(pageable);
    }

    public EmpleadoDTO obtenerEmpleadoDTO(Long id){
        Empleado empleado = this.obtenerEmpleado(id);
        EmpleadoDTO empleadoDTO = new EmpleadoDTO();

        empleadoDTO.setNombre(empleado.getNombreEmpleado());
        empleadoDTO.setApellido(empleado.getApellidoEmpleado());
        empleadoDTO.setDui(empleado.getDuiEmpleado());
        empleadoDTO.setIsss(empleado.getIsssEmpleado());
        empleadoDTO.setFechaNac(empleado.getFechaNacEmpleado());
        empleadoDTO.setTelefono(empleado.getTelefonoEmpleado());
        empleadoDTO.setGenero(empleado.getGeneroEmpleado());
        empleadoDTO.setCorreo(empleado.getCorreoEmpleado());
        empleadoDTO.setCargo(empleado.getCargo().getCargo());
        empleadoDTO.setDireccion(empleado.getDireccion().getLinea1());

        return empleadoDTO;
    }
}
