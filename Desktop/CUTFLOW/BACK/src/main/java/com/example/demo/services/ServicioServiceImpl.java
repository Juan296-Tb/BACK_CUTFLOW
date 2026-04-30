package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ServicioDto;
import com.example.demo.mapper.ServicioMapper;
import com.example.demo.models.Servicio;
import com.example.demo.models.TipoServicio;
import com.example.demo.repositories.ServicioRepository;

@Service
public class ServicioServiceImpl implements ServicioService {

    private final ServicioRepository repo;
    private final ServicioMapper mapper;

    public ServicioServiceImpl(ServicioRepository repo, ServicioMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }


    @Override
    public Servicio crear(ServicioDto dto) {

        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (dto.getPrecio() <= 0) {
            throw new RuntimeException("El precio debe ser mayor a 0");
        }

        Servicio servicio = mapper.toEntity(dto);

        Servicio guardado = repo.save(servicio);

        System.out.println("SERVICIO GUARDADO: " + guardado); // debug

        return guardado;
    }


    @Override
    public List<Servicio> listar() {
        List<Servicio> lista = repo.findAll();
        System.out.println("SERVICIOS BD: " + lista); // debug
        return lista;
    }


    @Override
    public Servicio obtenerPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));
    }


    @Override
    public void eliminar(String id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("El servicio no existe");
        }

        repo.deleteById(id);
    }

    @Override
    public Servicio actualizar(String id, ServicioDto dto) {

        Servicio existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio no encontrado"));

        if (dto.getNombre() != null) {
            existente.setNombre(dto.getNombre());
        }

        if (dto.getDescripcion() != null) {
            existente.setDescripcion(dto.getDescripcion());
        }

        if (dto.getPrecio() > 0) {
            existente.setPrecio(dto.getPrecio());
        }

        if (dto.getDuracion() > 0) {
            existente.setDuracion(dto.getDuracion());
        }

        if (dto.getClasificacion() != null) {
            existente.setClasificacion(
                    TipoServicio.valueOf(dto.getClasificacion().toUpperCase())
            );
        }

        return repo.save(existente);
    }
}