package com.example.demo.models;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;

    private String clienteId;
    private String nombreCliente;

    private TipoServicio servicio;
    private String barbero;

    private String hora;
    private String fecha;

}