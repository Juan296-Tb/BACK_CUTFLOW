package com.example.demo.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "clientes")
public class Cliente {

    @Id
    private String id;
    private String nombre;
    private String telefono;
    private String correo;

    private List<String> preferencias;
    private String barberoPreferido;

    private String notas;

}