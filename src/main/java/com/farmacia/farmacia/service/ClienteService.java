package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Cliente;
import com.farmacia.farmacia.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;


    public List<Cliente> listaClientes(){
        return this.clienteRepository.findAll();
    }

}
