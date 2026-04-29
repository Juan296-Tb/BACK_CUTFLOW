package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClienteDto {

    private String nombre;
    private String telefono;
    private String correo;
    private List<String> preferencias;
    private String barberoPreferido;
    private String notas;

}