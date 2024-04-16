package com.system.ong.mapper;

import com.system.ong.dto.DtoPersonalAdministrativo;
import com.system.ong.models.PersonalAdministrativo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperPersonalAdministrativo {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperPersonalAdministrativo(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoPersonalAdministrativo toDto(PersonalAdministrativo p) {
        return modelMapper.map(p, DtoPersonalAdministrativo.class);
    }

    public PersonalAdministrativo toEntity(DtoPersonalAdministrativo dp) {
        return modelMapper.map(dp, PersonalAdministrativo.class);
    }
}