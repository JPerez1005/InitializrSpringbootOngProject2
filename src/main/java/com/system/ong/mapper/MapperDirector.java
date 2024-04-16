package com.system.ong.mapper;

import com.system.ong.dto.DtoDirector;
import com.system.ong.models.Director;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperDirector {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperDirector(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoDirector toDto(Director d) {
        return modelMapper.map(d, DtoDirector.class);
    }

    public Director toEntity(DtoDirector dd) {
        return modelMapper.map(dd, Director.class);
    }
}