package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ClienteDto;
import com.example.demo.models.Cliente;



@Component
public class ClienteMapperImpl implements ClienteMapper {

    @Override
    public Cliente toEntity(ClienteDto dto) {
        if (dto == null) return null;

        Cliente c = new Cliente();
        c.setNombre(dto.getNombre());
        c.setTelefono(dto.getTelefono());
        c.setCorreo(dto.getCorreo());
        c.setPreferencias(dto.getPreferencias());
        c.setBarberoPreferido(dto.getBarberoPreferido());
        c.setNotas(dto.getNotas());

        return c;
    }

    @Override
    public ClienteDto toDto(Cliente cliente) {
        if (cliente == null) return null;

        ClienteDto dto = new ClienteDto();
        dto.setNombre(cliente.getNombre());
        dto.setTelefono(cliente.getTelefono());
        dto.setCorreo(cliente.getCorreo());
        dto.setPreferencias(cliente.getPreferencias());
        dto.setBarberoPreferido(cliente.getBarberoPreferido());
        dto.setNotas(cliente.getNotas());

        return dto;
    }
}