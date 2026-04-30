package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.Reserva;
import com.example.demo.models.TipoServicio;

@Component
public class ReservaMapperImpl implements ReservaMapper {

    @Override
    public Reserva toEntity(ReservaDto dto) {
        if (dto == null) return null;

        Reserva reserva = new Reserva();

        reserva.setClienteId(dto.getClienteId());
        reserva.setNombreCliente(dto.getNombreCliente());

        try {
            reserva.setServicio(TipoServicio.valueOf(dto.getServicio()));
        } catch (Exception e) {
            throw new RuntimeException("Servicio inválido: " + dto.getServicio());
        }

        reserva.setBarbero(dto.getBarbero());
        reserva.setFecha(dto.getFecha());
        reserva.setHora(dto.getHora());

        return reserva;
    }

    @Override
    public ReservaDto toDto(Reserva reserva) {
        if (reserva == null) return null;

        ReservaDto dto = new ReservaDto();

        dto.setId(reserva.getId());
        dto.setClienteId(reserva.getClienteId());
        dto.setNombreCliente(reserva.getNombreCliente());

        dto.setServicio(
            reserva.getServicio() != null
                ? reserva.getServicio().name()
                : null
        );

        dto.setBarbero(reserva.getBarbero());
        dto.setFecha(reserva.getFecha());
        dto.setHora(reserva.getHora());

        return dto;
    }
}