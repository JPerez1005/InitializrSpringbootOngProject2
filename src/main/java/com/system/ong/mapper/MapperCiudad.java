package com.system.ong.mapper;

import com.system.ong.dto.DtoCiudad;
import com.system.ong.models.Ciudad;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperCiudad {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperCiudad(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoCiudad toDto(Ciudad c) {
        return modelMapper.map(c, DtoCiudad.class);
    }

    public Ciudad toEntity(DtoCiudad dc) {
        return modelMapper.map(dc, Ciudad.class);
    }
}