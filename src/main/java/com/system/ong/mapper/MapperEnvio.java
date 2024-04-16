package com.system.ong.mapper;

import com.system.ong.dto.DtoEnvio;
import com.system.ong.models.Envio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperEnvio {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperEnvio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoEnvio toDto(Envio e) {
        return modelMapper.map(e, DtoEnvio.class);
    }

    public Envio toEntity(DtoEnvio de) {
        return modelMapper.map(de, Envio.class);
    }
}