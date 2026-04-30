package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ClienteDto;
import com.example.demo.models.Cliente;

public interface ClienteService {

    Cliente crearCliente(ClienteDto dto);

    List<Cliente> listarClientes();

    Cliente obtenerPorId(String id);

    Cliente actualizar(String id, ClienteDto dto);

    void eliminar(String id);
}