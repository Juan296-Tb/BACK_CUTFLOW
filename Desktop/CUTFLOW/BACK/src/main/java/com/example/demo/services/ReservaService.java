package com.example.demo.services;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.EstadoReserva;

import java.util.List;

public interface ReservaService {

    ReservaDto crear(ReservaDto dto);

    List<ReservaDto> listar();

    void eliminar(String id);

    ReservaDto cambiarEstado(String id, EstadoReserva estado);
}