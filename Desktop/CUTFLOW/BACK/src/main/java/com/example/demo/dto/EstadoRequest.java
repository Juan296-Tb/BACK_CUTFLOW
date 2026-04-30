package com.example.demo.dto;

import com.example.demo.models.EstadoReserva;

public class EstadoRequest {

    private EstadoReserva estado;

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }
}