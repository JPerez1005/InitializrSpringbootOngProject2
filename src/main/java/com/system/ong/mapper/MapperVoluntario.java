package com.system.ong.mapper;

import com.system.ong.dto.DtoVoluntario;
import com.system.ong.models.Voluntario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperVoluntario {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperVoluntario(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoVoluntario toDto(Voluntario v) {
        return modelMapper.map(v, DtoVoluntario.class);
    }

    public Voluntario toEntity(DtoVoluntario dv) {
        return modelMapper.map(dv, Voluntario.class);
    }
}