package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ServicioDto {

    private String id;
    private String nombre;
    private String descripcion;
    private double precio;
    private int duracion;
    private String clasificacion;
}