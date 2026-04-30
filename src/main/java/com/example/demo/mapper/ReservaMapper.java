package com.example.demo.mapper;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.Reserva;

public interface ReservaMapper {

    Reserva toEntity(ReservaDto dto);

    ReservaDto toDto(Reserva reserva);
}