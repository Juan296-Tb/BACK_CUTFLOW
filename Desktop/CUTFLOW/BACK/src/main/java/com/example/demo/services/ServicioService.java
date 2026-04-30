package com.example.demo.services;

import java.util.List;
import com.example.demo.dto.ServicioDto;
import com.example.demo.models.Servicio;

public interface ServicioService {

    Servicio crear(ServicioDto dto);

    List<Servicio> listar();

    Servicio obtenerPorId(String id);

    void eliminar(String id);

    Servicio actualizar(String id, ServicioDto dto);
}