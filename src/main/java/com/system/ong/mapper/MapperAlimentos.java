package com.system.ong.mapper;

import com.system.ong.dto.DtoAlimentos;
import com.system.ong.models.Alimentos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperAlimentos {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperAlimentos(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoAlimentos toDto(Alimentos alimentos) {
        return modelMapper.map(alimentos, DtoAlimentos.class);
    }

    public Alimentos toEntity(DtoAlimentos da) {
        return modelMapper.map(da, Alimentos.class);
    }
}