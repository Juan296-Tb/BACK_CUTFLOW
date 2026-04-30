package com.example.demo.models;

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
@Document(collection = "servicios")
public class Servicio {

    @Id
    private String id;

    private String nombre;

    private String descripcion;

    private double precio;

    private int duracion;

    private TipoServicio clasificacion;
}