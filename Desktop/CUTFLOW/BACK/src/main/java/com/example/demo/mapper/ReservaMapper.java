package com.example.demo.mapper;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.Reserva;

public interface ReservaMapper {

    ReservaDto toDto(Reserva reserva);

    Reserva toEntity(ReservaDto dto);
}