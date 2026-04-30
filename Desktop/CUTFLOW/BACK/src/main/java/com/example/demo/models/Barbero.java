package com.example.demo.models;

import java.time.LocalTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "barberos")
public class Barbero {

    @Id
    private String id;

    private String nombre;

    private String telefono;

    private Especialidad especialidad;

    private LocalTime horaEntrada;

    private LocalTime horaSalida;

    private String biografia;
}