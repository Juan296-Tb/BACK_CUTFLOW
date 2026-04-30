package com.example.demo.controller;

import com.example.demo.dto.EstadoRequest;
import com.example.demo.dto.ReservaDto;
import com.example.demo.models.EstadoReserva;
import com.example.demo.services.ReservaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservas")
@CrossOrigin(origins = "http://localhost:5173")
public class ReservaController {

    private final ReservaService service;

    public ReservaController(ReservaService service) {
        this.service = service;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ReservaDto> crear(@RequestBody ReservaDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    // LIST
    @GetMapping
    public ResponseEntity<List<ReservaDto>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }

    // 🔥 CAMBIAR ESTADO (ARREGLADO)
    @PutMapping("/{id}/estado")
    public ResponseEntity<ReservaDto> cambiarEstado(
            @PathVariable String id,
            @RequestBody EstadoRequest request
    ) {
        return ResponseEntity.ok(
                service.cambiarEstado(id, request.getEstado())
        );
    }
}