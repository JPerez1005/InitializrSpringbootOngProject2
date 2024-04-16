package com.system.ong.mapper;

import com.system.ong.dto.DtoMedicamentos;
import com.system.ong.models.Medicamentos;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MapperMedicamentos {

    private final ModelMapper modelMapper;

    @Autowired
    public MapperMedicamentos(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public DtoMedicamentos toDto(Medicamentos m) {
        return modelMapper.map(m, DtoMedicamentos.class);
    }

    public Medicamentos toEntity(DtoMedicamentos dm) {
        return modelMapper.map(dm, Medicamentos.class);
    }
}