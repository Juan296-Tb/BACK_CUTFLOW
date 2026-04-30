package com.example.demo.services;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.*;
import com.example.demo.repositories.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;
    private final ClienteRepository clienteRepository;
    private final BarberoRepository barberoRepository;
    private final ServicioRepository servicioRepository;

    public ReservaServiceImpl(
            ReservaRepository reservaRepository,
            ClienteRepository clienteRepository,
            BarberoRepository barberoRepository,
            ServicioRepository servicioRepository
    ) {
        this.reservaRepository = reservaRepository;
        this.clienteRepository = clienteRepository;
        this.barberoRepository = barberoRepository;
        this.servicioRepository = servicioRepository;
    }

    @Override
    public ReservaDto crear(ReservaDto dto) {

        Reserva reserva = new Reserva();

        reserva.setClienteId(dto.getClienteId());
        reserva.setBarberoId(dto.getBarberoId());
        reserva.setServicioId(dto.getServicioId());
        reserva.setHora(dto.getHora());
        reserva.setFecha(dto.getFecha());

        reserva.setEstado(EstadoReserva.PENDIENTE);

        return mapToDto(reservaRepository.save(reserva));
    }

    @Override
    public List<ReservaDto> listar() {
        return reservaRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    @Override
    public void eliminar(String id) {
        reservaRepository.deleteById(id);
    }

    // 🔥 CAMBIO DE ESTADO FUNCIONAL
    @Override
    public ReservaDto cambiarEstado(String id, EstadoReserva estado) {

        Reserva reserva = reservaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reserva no encontrada"));

        reserva.setEstado(estado);

        return mapToDto(reservaRepository.save(reserva));
    }

    // 🔥 MAPPER ROBUSTO (NO ROMPE FRONT)
    private ReservaDto mapToDto(Reserva r) {

        ReservaDto dto = new ReservaDto();

        dto.setId(r.getId());
        dto.setClienteId(r.getClienteId());
        dto.setBarberoId(r.getBarberoId());
        dto.setServicioId(r.getServicioId());
        dto.setHora(r.getHora());
        dto.setFecha(r.getFecha());

        dto.setEstado(r.getEstado() != null ? r.getEstado().name() : "PENDIENTE");

        clienteRepository.findById(r.getClienteId())
                .ifPresentOrElse(
                        c -> dto.setNombreCliente(c.getNombre()),
                        () -> dto.setNombreCliente("Cliente no disponible")
                );

        barberoRepository.findById(r.getBarberoId())
                .ifPresentOrElse(
                        b -> dto.setNombreBarbero(b.getNombre()),
                        () -> dto.setNombreBarbero("Barbero no disponible")
                );

        servicioRepository.findById(r.getServicioId())
                .ifPresentOrElse(
                        s -> dto.setNombreServicio(s.getNombre()),
                        () -> dto.setNombreServicio("Servicio no disponible")
                );

        return dto;
    }
}