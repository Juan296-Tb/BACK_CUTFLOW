package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.example.demo.models.Reserva;

public interface ReservaRepository extends MongoRepository<Reserva, String> {
}