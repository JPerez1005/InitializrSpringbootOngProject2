package com.system.ong.mapper;

import com.system.ong.dto.DtoSede;
import com.system.ong.models.Sede;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperSede {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperSede(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoSede toDto(Sede s) {
        return modelMapper.map(s, DtoSede.class);
    }

    public Sede toEntity(DtoSede ds) {
        return modelMapper.map(ds, Sede.class);
    }
}