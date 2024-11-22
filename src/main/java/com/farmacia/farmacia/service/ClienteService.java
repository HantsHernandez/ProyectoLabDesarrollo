package com.farmacia.farmacia.service;

import com.farmacia.farmacia.DTO.ClienteDTO;
import com.farmacia.farmacia.DTO.ClienteDireccionDTO;
import com.farmacia.farmacia.DTO.EmpleadoDireccionDTO;
import com.farmacia.farmacia.DTO.MedicamentoDTO;
import com.farmacia.farmacia.entity.Cliente;
import com.farmacia.farmacia.entity.Direccion;
import com.farmacia.farmacia.entity.Empleado;
import com.farmacia.farmacia.entity.Medicamento;
import com.farmacia.farmacia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDate;
import java.time.Period;
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

    public ClienteDTO obtenerClienteDTO(Long id){
        Cliente cliente = this.obtenerCliente(id);
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setNombres(cliente.getNombresCliente());
        clienteDTO.setApellidos(cliente.getApellidosCliennte());
        clienteDTO.setEdad(Integer.toString(Period.between(cliente.getFechaNacimientoCliente(), LocalDate.now()).getYears()));
        clienteDTO.setDui(cliente.getDuiCliente());
        clienteDTO.setDireccion(cliente.getDireccion().getLinea1());
        clienteDTO.setCorreo(cliente.getCorreoCliente());
        clienteDTO.setTelefono(cliente.getTelefonoCliente());

        return clienteDTO;
    }

}
