package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.BarberoDto;
import com.example.demo.mapper.BarberoMapper;
import com.example.demo.models.Barbero;
import com.example.demo.models.Especialidad;
import com.example.demo.repositories.BarberoRepository;

@Service
public class BarberoServiceImpl implements BarberoService {

    private final BarberoRepository repo;
    private final BarberoMapper mapper;

    public BarberoServiceImpl(BarberoRepository repo, BarberoMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    @Override
    public Barbero crear(BarberoDto dto) {

        if (dto.getNombre() == null || dto.getNombre().isEmpty()) {
            throw new RuntimeException("El nombre es obligatorio");
        }

        if (dto.getTelefono() == null || dto.getTelefono().isEmpty()) {
            throw new RuntimeException("El teléfono es obligatorio");
        }

        if (dto.getHoraEntrada() == null || dto.getHoraSalida() == null) {
            throw new RuntimeException("Los horarios son obligatorios");
        }

        Barbero barbero = mapper.toEntity(dto);

        Barbero guardado = repo.save(barbero);

        System.out.println("BARBERO GUARDADO: " + guardado);

        return guardado;
    }

    @Override
    public List<Barbero> listar() {
        List<Barbero> lista = repo.findAll();
        System.out.println("BARBEROS BD: " + lista);
        return lista;
    }

    @Override
    public Barbero obtenerPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));
    }

    @Override
    public void eliminar(String id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("El barbero no existe");
        }

        repo.deleteById(id);
    }

    @Override
    public Barbero actualizar(String id, BarberoDto dto) {

        Barbero existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Barbero no encontrado"));

        if (dto.getNombre() != null) {
            existente.setNombre(dto.getNombre());
        }

        if (dto.getTelefono() != null) {
            existente.setTelefono(dto.getTelefono());
        }

        if (dto.getEspecialidad() != null) {
            existente.setEspecialidad(
                    Especialidad.valueOf(dto.getEspecialidad().toUpperCase())
            );
        }

        if (dto.getHoraEntrada() != null) {
            existente.setHoraEntrada(dto.getHoraEntrada());
        }

        if (dto.getHoraSalida() != null) {
            existente.setHoraSalida(dto.getHoraSalida());
        }

        if (dto.getBiografia() != null) {
            existente.setBiografia(dto.getBiografia());
        }

        return repo.save(existente);
    }
}