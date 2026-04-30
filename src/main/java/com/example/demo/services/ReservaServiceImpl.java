package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ReservaDto;
import com.example.demo.mapper.ReservaMapper;
import com.example.demo.models.Reserva;
import com.example.demo.repositories.ReservaRepository;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository repo;
    private final ReservaMapper mapper;

    public ReservaServiceImpl(ReservaRepository repo, ReservaMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    // ✅ CREAR
    @Override
    public Reserva crearReserva(ReservaDto dto) {

        // 🔥 VALIDACIONES BÁSICAS
        if (dto.getClienteId() == null || dto.getClienteId().isEmpty()) {
            throw new RuntimeException("El cliente es obligatorio");
        }

        if (dto.getFecha() == null || dto.getHora() == null) {
            throw new RuntimeException("Fecha y hora son obligatorias");
        }

        if (dto.getServicio() == null) {
            throw new RuntimeException("El servicio es obligatorio");
        }

        // 🔥 VALIDACIÓN REAL (esto te faltaba)
        // evita doble reserva en mismo horario + barbero
        boolean ocupado = repo.existsByFechaAndHoraAndBarbero(
                dto.getFecha(),
                dto.getHora(),
                dto.getBarbero()
        );

        if (ocupado) {
            throw new RuntimeException("Ese horario ya está ocupado");
        }

        Reserva reserva = mapper.toEntity(dto);

        return repo.save(reserva);
    }

    // ✅ LISTAR
    @Override
    public List<Reserva> listarReservas() {
        return repo.findAll();
    }

    // ✅ OBTENER
    @Override
    public Reserva obtenerPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));
    }

    // ✅ ACTUALIZAR
    @Override
    public Reserva actualizar(String id, ReservaDto dto) {

        Reserva existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        // ⚠️ validar si cambia horario
        if (!existente.getFecha().equals(dto.getFecha()) ||
            !existente.getHora().equals(dto.getHora()) ||
            !existente.getBarbero().equals(dto.getBarbero())) {

            boolean ocupado = repo.existsByFechaAndHoraAndBarbero(
                    dto.getFecha(),
                    dto.getHora(),
                    dto.getBarbero()
            );

            if (ocupado) {
                throw new RuntimeException("Ese horario ya está ocupado");
            }
        }

        existente.setClienteId(dto.getClienteId());
        existente.setNombreCliente(dto.getNombreCliente());
        existente.setBarbero(dto.getBarbero());
        existente.setFecha(dto.getFecha());
        existente.setHora(dto.getHora());

        // 🔥 ENUM
        try {
            existente.setServicio(
                com.example.demo.models.TipoServicio.valueOf(dto.getServicio())
            );
        } catch (Exception e) {
            throw new RuntimeException("Servicio inválido");
        }

        return repo.save(existente);
    }

    // ✅ ELIMINAR
    @Override
    public void eliminar(String id) {

        if (!repo.existsById(id)) {
            throw new RuntimeException("La reserva no existe");
        }

        repo.deleteById(id);
    }
}