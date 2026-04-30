package com.example.demo.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.demo.models.Cliente;

public interface ClienteRepository extends MongoRepository<Cliente, String> {

    Optional<Cliente> findByCorreo(String correo);
}