package com.example.demo.mapper;

import java.time.LocalTime;

import org.springframework.stereotype.Component;

import com.example.demo.dto.BarberoDto;
import com.example.demo.models.Barbero;
import com.example.demo.models.Especialidad;

@Component
public class BarberoMapperImpl implements BarberoMapper {

    @Override
    public BarberoDto toDto(Barbero b) {
        if (b == null) return null;

        BarberoDto dto = new BarberoDto();
        dto.setId(b.getId());
        dto.setNombre(b.getNombre());
        dto.setTelefono(b.getTelefono());
        dto.setEspecialidad(b.getEspecialidad().name());
        dto.setHoraEntrada(b.getHoraEntrada());
        dto.setHoraSalida(b.getHoraSalida());
        dto.setBiografia(b.getBiografia());

        return dto;
    }

    @Override
    public Barbero toEntity(BarberoDto dto) {
        if (dto == null) return null;

        Barbero b = new Barbero();
        b.setId(dto.getId());
        b.setNombre(dto.getNombre());
        b.setTelefono(dto.getTelefono());

        b.setEspecialidad(
                Especialidad.valueOf(dto.getEspecialidad().toUpperCase())
        );

        b.setHoraEntrada(dto.getHoraEntrada());
        b.setHoraSalida(dto.getHoraSalida());
        b.setBiografia(dto.getBiografia());

        return b;
    }
}