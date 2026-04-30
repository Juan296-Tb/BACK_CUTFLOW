package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDto;
import com.example.demo.mapper.ClienteMapper;
import com.example.demo.models.Cliente;
import com.example.demo.repositories.ClienteRepository;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository repo;
    private final ClienteMapper mapper;

    public ClienteServiceImpl(ClienteRepository repo, ClienteMapper mapper) {
        this.repo = repo;
        this.mapper = mapper;
    }

    // ✅ CREAR
    @Override
    public Cliente crearCliente(ClienteDto dto) {

        if (dto.getCorreo() == null || dto.getCorreo().isEmpty()) {
            throw new RuntimeException("El correo es obligatorio");
        }

        repo.findByCorreo(dto.getCorreo()).ifPresent(c -> {
            throw new RuntimeException("Ya existe un cliente con ese correo");
        });

        Cliente cliente = mapper.toEntity(dto);

        Cliente guardado = repo.save(cliente);
        System.out.println("GUARDADO: " + guardado); // 🔥 debug

        return guardado;
    }

    // ✅ LISTAR
    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> lista = repo.findAll();
        System.out.println("CLIENTES BD: " + lista); // 🔥 debug
        return lista;
    }

    // ✅ OBTENER POR ID
    @Override
    public Cliente obtenerPorId(String id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    // ✅ ELIMINAR
    @Override
    public void eliminar(String id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("El cliente no existe");
        }
        repo.deleteById(id);
    }

    // ✅ ACTUALIZAR (🔥 CORREGIDO)
    @Override
    public Cliente actualizar(String id, ClienteDto dto) {

        Cliente existente = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        if (dto.getCorreo() != null &&
            !dto.getCorreo().equals(existente.getCorreo())) {

            repo.findByCorreo(dto.getCorreo()).ifPresent(c -> {
                throw new RuntimeException("Ese correo ya está en uso");
            });

            existente.setCorreo(dto.getCorreo());
        }

        existente.setNombre(dto.getNombre());
        existente.setTelefono(dto.getTelefono());

        // 🔥 AQUÍ ESTABA TU ERROR
        existente.setPreferencias(dto.getPreferencias());

        existente.setBarberoPreferido(dto.getBarberoPreferido());
        existente.setNotas(dto.getNotas());

        return repo.save(existente);
    }
}