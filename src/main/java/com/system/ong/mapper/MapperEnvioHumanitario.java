package com.system.ong.mapper;

import com.system.ong.dto.DtoEnvioHumanitario;
import com.system.ong.models.EnvioHumanitario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperEnvioHumanitario {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperEnvioHumanitario(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoEnvioHumanitario toDto(EnvioHumanitario e) {
        return modelMapper.map(e, DtoEnvioHumanitario.class);
    }

    public EnvioHumanitario toEntity(DtoEnvioHumanitario de) {
        return modelMapper.map(de, EnvioHumanitario.class);
    }
}