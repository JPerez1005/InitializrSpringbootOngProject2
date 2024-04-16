package com.system.ong.mapper;

import com.system.ong.dto.DtoRefugio;
import com.system.ong.models.Refugio;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperRefugio {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperRefugio(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoRefugio toDto(Refugio r) {
        return modelMapper.map(r, DtoRefugio.class);
    }

    public Refugio toEntity(DtoRefugio dr) {
        return modelMapper.map(dr, Refugio.class);
    }
}