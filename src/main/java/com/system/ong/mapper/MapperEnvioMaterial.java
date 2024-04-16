package com.system.ong.mapper;

import com.system.ong.dto.DtoEnvioMaterial;
import com.system.ong.models.EnvioMaterial;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperEnvioMaterial {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperEnvioMaterial(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoEnvioMaterial toDto(EnvioMaterial e) {
        return modelMapper.map(e, DtoEnvioMaterial.class);
    }

    public EnvioMaterial toEntity(DtoEnvioMaterial de) {
        return modelMapper.map(de, EnvioMaterial.class);
    }
}