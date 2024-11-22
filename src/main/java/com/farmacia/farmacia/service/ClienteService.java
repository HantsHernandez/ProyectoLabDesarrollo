package com.farmacia.farmacia.service;

import com.farmacia.farmacia.DTO.ClienteDireccionDTO;
import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.entity.Cliente;
import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private DireccionService direccionService;

    public Cliente guardarCliente(ClienteDireccionDTO clienteDireccionDTO){
        Direccion direccion = this.direccionService.guardarDireccion(clienteDireccionDTO.getDireccion());
        clienteDireccionDTO.getCliente().setDireccion(direccion);
        return this.clienteRepository.save(clienteDireccionDTO.getCliente());
    }

    public Cliente obtenerCliente(Long id){
        return this.clienteRepository.findById(id).orElseThrow(() -> new RuntimeException("CLIENTE NO ENCONTRADO"));
    }

    public Cliente actualizarCliente(ClienteDireccionDTO clienteDireccionDTO){
        Direccion direccion = this.direccionService.actualizarDireccion(clienteDireccionDTO.getDireccion());
        clienteDireccionDTO.getCliente().setDireccion(direccion);
        Cliente clienteActualizado = clienteDireccionDTO.getCliente();
        return this.clienteRepository.save(this.clienteRepository.findById(clienteActualizado.getIdCliente())
                .map(cliente -> {
                    cliente.setNombresCliente(clienteActualizado.getNombresCliente());
                    cliente.setApellidosCliennte(clienteActualizado.getApellidosCliennte());
                    cliente.setTelefonoCliente(clienteActualizado.getCorreoCliente());
                    cliente.setDuiCliente(clienteActualizado.getDuiCliente());
                    cliente.setFechaNacimientoCliente(clienteActualizado.getFechaNacimientoCliente());
                    cliente.setCorreoCliente(clienteActualizado.getCorreoCliente());
                    cliente.setDireccion(clienteActualizado.getDireccion());
                    return cliente;
                }).orElseThrow(() -> new RuntimeException("CLIENTE NO ENCONTRADO")));
    }

    public void eliminarCliente(Long id){
        this.clienteRepository.findById(id)
                .ifPresentOrElse(
                        cliente -> {
                            this.direccionService.eliminarDireccion(cliente.getDireccion().getIdDireccion());
                            this.clienteRepository.delete(cliente);
                        },
                        () -> new RuntimeException("CLIENTE NO ENCONTRADO")
                );
    }

    public List<Cliente> listaClientes(){
        return this.clienteRepository.findAll();
    }

    public Page<Cliente> listaClientesPaginados(int page, int size){
        Pageable pageable = PageRequest.of(page, size);
        return this.clienteRepository.findAll(pageable);
    }


}
