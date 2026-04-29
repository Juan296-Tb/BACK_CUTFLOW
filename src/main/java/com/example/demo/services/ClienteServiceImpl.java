package com.example.demo.services;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDto;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.models.Cliente;
import com.example.demo.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;
    private final ClienteMapper mapper;

    public ClienteServiceImpl(ClienteRepository repo, ClienteMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Cliente crearCliente(ClienteDto dto) {

        repo.findByCorreo(dto.getCorreo()).ifPresent(c -> {
            throw new RuntimeException("El cliente ya existe con ese correo");
        });

        Cliente cliente = mapper.toEntity(dto);

        return repo.save(cliente);
    }
}