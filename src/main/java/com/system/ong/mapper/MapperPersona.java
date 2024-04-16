package com.system.ong.mapper;

import com.system.ong.dto.DtoPersona;
import com.system.ong.models.Persona;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperPersona {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperPersona(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoPersona toDto(Persona p) {
        return modelMapper.map(p, DtoPersona.class);
    }

    public Persona toEntity(DtoPersona dp) {
        return modelMapper.map(dp, Persona.class);
    }
}