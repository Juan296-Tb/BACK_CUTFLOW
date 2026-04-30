package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.Reserva;

public interface ReservaService {

    Reserva crearReserva(ReservaDto dto);

    List<Reserva> listarReservas();

    Reserva obtenerPorId(String id);

    Reserva actualizar(String id, ReservaDto dto);

    void eliminar(String id);
}