package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ServicioDto;
import com.example.demo.models.Servicio;
import com.example.demo.services.ServicioService;

@RestController
@RequestMapping("/api/servicios")
@CrossOrigin(origins = "http://localhost:5173")
public class ServicioController {

    private final ServicioService service;

    public ServicioController(ServicioService service) {
        this.service = service;
    }


    @PostMapping
    public ResponseEntity<Servicio> crear(@RequestBody ServicioDto dto) {
        return ResponseEntity.ok(service.crear(dto));
    }


    @GetMapping
    public ResponseEntity<List<Servicio>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Servicio> obtener(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Servicio> actualizar(
            @PathVariable String id,
            @RequestBody ServicioDto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}