package com.example.demo.dto;

import lombok.Data;

@Data
public class ReservaDto {

    private String id;
    private String clienteId;
    private String nombreCliente;

    private String barberoId;
    private String nombreBarbero;

    private String servicioId;
    private String nombreServicio;

    private String fecha;
    private String hora;
    private String estado;
}