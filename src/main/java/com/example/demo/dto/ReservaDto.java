package com.example.demo.dto;

import lombok.*;

@Data
public class ReservaDto {

    private String clienteId;
    private String nombreCliente;

    private String servicio;
    private String barbero;

    private String hora;
    private String fecha;
}