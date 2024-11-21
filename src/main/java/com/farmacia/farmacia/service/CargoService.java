package com.farmacia.farmacia.service;

import com.farmacia.farmacia.entity.Cargo;
import com.farmacia.farmacia.repository.CargoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoService {

    @Autowired
    private CargoRepository cargoRepository;

    public List<Cargo> listaCargos(){
        return this.cargoRepository.findAll();
    }
}
