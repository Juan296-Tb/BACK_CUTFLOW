package com.example.demo.mapper;

import com.example.demo.dto.ClienteDto;
import com.example.demo.models.Cliente;

public interface ClienteMapper {

    Cliente toEntity(ClienteDto dto);

    ClienteDto toDto(Cliente cliente);
}