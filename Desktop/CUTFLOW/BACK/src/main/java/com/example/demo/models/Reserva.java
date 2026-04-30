package com.example.demo.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "reservas")
public class Reserva {

    @Id
    private String id;

    private String clienteId;
    private String barberoId;
    private String servicioId;

    private String fecha;
    private String hora;

    private EstadoReserva estado;
}