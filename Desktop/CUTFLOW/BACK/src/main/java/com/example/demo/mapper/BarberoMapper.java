package com.example.demo.mapper;

import com.example.demo.dto.BarberoDto;
import com.example.demo.models.Barbero;

public interface BarberoMapper {

    BarberoDto toDto(Barbero barbero);

    Barbero toEntity(BarberoDto dto);
}