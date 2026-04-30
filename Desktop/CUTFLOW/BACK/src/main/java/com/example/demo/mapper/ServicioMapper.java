package com.example.demo.mapper;

import com.example.demo.dto.ServicioDto;
import com.example.demo.models.Servicio;

public interface ServicioMapper {

    ServicioDto toDto(Servicio servicio);

    Servicio toEntity(ServicioDto dto);
}