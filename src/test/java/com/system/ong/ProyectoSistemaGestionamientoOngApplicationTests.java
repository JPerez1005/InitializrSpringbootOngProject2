package com.system.ong;

import com.system.ong.dto.DtoPersona;
import com.system.ong.mapper.MapperPersona;
import com.system.ong.models.Persona;
import com.system.ong.repositories.RepositoryPersona;
import com.system.ong.service.ServicePersona;
import com.system.ong.service.impl.UniversalServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ProyectoSistemaGestionamientoOngApplicationTests {

    @Autowired ServicePersona sp;

    @Autowired UniversalServiceImpl usi;

    @Autowired RepositoryPersona rp;

    @Autowired MapperPersona mp;

    @Test
    public void creacionPersona() {
        Persona p = new Persona("kenia",
                "1005",
                "315",
                "Administrador",
                "kenia@gmail.com",
                "12345678",
                "true");
        System.out.println(p);

        DtoPersona dp = mp.toDto(p);
        usi.save(rp, dp, Persona.class);
    }

}
