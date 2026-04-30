package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.dto.ReservaDto;
import com.example.demo.models.*;
import com.example.demo.repositories.*;

@Component
public class ReservaMapperImpl implements ReservaMapper {

    private final ClienteRepository clienteRepo;
    private final BarberoRepository barberoRepo;
    private final ServicioRepository servicioRepo;

    public ReservaMapperImpl(
            ClienteRepository clienteRepo,
            BarberoRepository barberoRepo,
            ServicioRepository servicioRepo
    ) {
        this.clienteRepo = clienteRepo;
        this.barberoRepo = barberoRepo;
        this.servicioRepo = servicioRepo;
    }

    @Override
    public ReservaDto toDto(Reserva r) {

        if (r == null) return null;

        ReservaDto dto = new ReservaDto();

        dto.setId(r.getId());
        dto.setClienteId(r.getClienteId());
        dto.setBarberoId(r.getBarberoId());
        dto.setServicioId(r.getServicioId());
        dto.setFecha(r.getFecha());
        dto.setHora(r.getHora());

        // enum → string
        dto.setEstado(r.getEstado() != null ? r.getEstado().name() : null);

        Cliente c = clienteRepo.findById(r.getClienteId()).orElse(null);
        Barbero b = barberoRepo.findById(r.getBarberoId()).orElse(null);
        Servicio s = servicioRepo.findById(r.getServicioId()).orElse(null);

        dto.setNombreCliente(c != null ? c.getNombre() : null);
        dto.setNombreBarbero(b != null ? b.getNombre() : null);
        dto.setNombreServicio(s != null ? s.getNombre() : null);

        return dto;
    }

    @Override
    public Reserva toEntity(ReservaDto dto) {

        if (dto == null) return null;

        Reserva r = new Reserva();

        r.setId(dto.getId());
        r.setClienteId(dto.getClienteId());
        r.setBarberoId(dto.getBarberoId());
        r.setServicioId(dto.getServicioId());
        r.setFecha(dto.getFecha());
        r.setHora(dto.getHora());

        // string → enum
        if (dto.getEstado() != null) {
            r.setEstado(EstadoReserva.valueOf(dto.getEstado().toUpperCase()));
        }

        return r;
    }
}