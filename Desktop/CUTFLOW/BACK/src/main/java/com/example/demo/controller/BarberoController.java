package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.BarberoDto;
import com.example.demo.models.Barbero;
import com.example.demo.services.BarberoService;

@RestController
@RequestMapping("/api/barberos")
@CrossOrigin(origins = "http://localhost:5173")
public class BarberoController {

    private final BarberoService service;

    public BarberoController(BarberoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Barbero> crear(@RequestBody BarberoDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }

    @GetMapping
    public ResponseEntity<List<Barbero>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Barbero> obtener(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Barbero> actualizar(
            @PathVariable String id,
            @RequestBody BarberoDto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}