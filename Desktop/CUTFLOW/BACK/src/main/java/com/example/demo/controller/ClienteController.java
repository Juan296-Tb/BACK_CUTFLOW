package com.example.demo.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.ClienteDto;
import com.example.demo.models.Cliente;
import com.example.demo.services.ClienteService;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "http://localhost:5173")
public class ClienteController {

    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Cliente> crear(@RequestBody ClienteDto dto) {
        return ResponseEntity.ok(service.crearCliente(dto));
    }


    @GetMapping
    public ResponseEntity<List<Cliente>> listar() {
        return ResponseEntity.ok(service.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtener(@PathVariable String id) {
        return ResponseEntity.ok(service.obtenerPorId(id));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizar(
            @PathVariable String id,
            @RequestBody ClienteDto dto) {
        return ResponseEntity.ok(service.actualizar(id, dto));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
} 