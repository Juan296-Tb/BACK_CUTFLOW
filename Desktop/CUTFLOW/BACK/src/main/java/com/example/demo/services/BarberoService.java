package com.example.demo.services;

import java.util.List;

import com.example.demo.dto.BarberoDto;
import com.example.demo.models.Barbero;

public interface BarberoService {

    Barbero crear(BarberoDto dto);

    List<Barbero> listar();

    Barbero obtenerPorId(String id);

    void eliminar(String id);

    Barbero actualizar(String id, BarberoDto dto);
}