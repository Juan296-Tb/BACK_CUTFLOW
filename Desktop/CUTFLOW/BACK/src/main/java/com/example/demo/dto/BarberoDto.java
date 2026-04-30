package com.example.demo.dto;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
public class BarberoDto {

    @Id
    private String id;
    private String nombre;

    private String telefono;

    private String especialidad;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private String biografia;

    public void setId(String id) {
        throw new UnsupportedOperationException("Unimplemented method 'setId'");
    }
}