package com.example.demo.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Barbero;

@Repository
public interface BarberoRepository extends MongoRepository<Barbero, String> {
}