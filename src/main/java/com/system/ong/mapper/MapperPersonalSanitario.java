package com.system.ong.mapper;

import com.system.ong.dto.DtoPersonalSanitario;
import com.system.ong.models.PersonalSanitario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperPersonalSanitario {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperPersonalSanitario(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoPersonalSanitario toDto(PersonalSanitario p) {
        return modelMapper.map(p, DtoPersonalSanitario.class);
    }

    public PersonalSanitario toEntity(DtoPersonalSanitario dp) {
        return modelMapper.map(dp, PersonalSanitario.class);
    }
}