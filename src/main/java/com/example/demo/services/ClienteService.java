package com.example.demo.services;

import com.example.demo.dto.ClienteDto;
import com.example.demo.models.Cliente;

public interface ClienteService {

    Cliente crearCliente(ClienteDto dto);
}