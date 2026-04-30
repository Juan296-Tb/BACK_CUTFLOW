package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ServicioDto;
import com.example.demo.models.Servicio;
import com.example.demo.models.TipoServicio;

@Component
public class ServicioMapperImpl implements ServicioMapper {

    @Override
    public ServicioDto toDto(Servicio s) {
        if (s == null) return null;

        ServicioDto dto = new ServicioDto();
        dto.setId(s.getId());
        dto.setNombre(s.getNombre());
        dto.setDescripcion(s.getDescripcion());
        dto.setPrecio(s.getPrecio());
        dto.setDuracion(s.getDuracion());
        dto.setClasificacion(s.getClasificacion().name());

        return dto;
    }

    @Override
    public Servicio toEntity(ServicioDto dto) {
        if (dto == null) return null;

        Servicio s = new Servicio();
        s.setId(dto.getId());
        s.setNombre(dto.getNombre());
        s.setDescripcion(dto.getDescripcion());
        s.setPrecio(dto.getPrecio());
        s.setDuracion(dto.getDuracion());
        s.setClasificacion(
                TipoServicio.valueOf(dto.getClasificacion().toUpperCase())
        );

        return s;
    }
}