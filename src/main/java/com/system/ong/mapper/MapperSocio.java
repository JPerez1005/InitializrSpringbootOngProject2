package com.system.ong.mapper;

import com.system.ong.dto.DtoSocio;
import com.system.ong.models.Socio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperSocio {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperSocio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoSocio toDto(Socio s) {
        return modelMapper.map(s, DtoSocio.class);
    }

    public Socio toEntity(DtoSocio ds) {
        return modelMapper.map(ds, Socio.class);
    }
}