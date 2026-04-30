package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.Reserva;
import com.example.demo.services.ReservaService;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Reserva> crear(@RequestBody ReservaDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }
}