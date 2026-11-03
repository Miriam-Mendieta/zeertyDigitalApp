package com.miriammendieta.zeerty_digital_app.repository;

import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.model.CategoriaActivo;
import com.miriammendieta.zeerty_digital_app.model.EstadoActivo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class ActivoDigitalRepositoryTest {

    @Autowired
    private ActivoDigitalRepository repo;

    @Test
    void testGuardarYBuscar() {

        ActivoDigital activo = new ActivoDigital();
        activo.setNombre("Servidor");
        activo.setDescripcion("Prod");
        activo.setFecha_alta(LocalDate.now());
        activo.setCategoria(CategoriaActivo.DISPOSITIVOS);
        activo.setEstado(EstadoActivo.ACTIVO);
        activo.setId_usuario(1);

        ActivoDigital guardado = repo.save(activo);

        assertNotNull(guardado.getId_activo());

        ActivoDigital encontrado = repo.findById(guardado.getId_activo()).orElse(null);

        assertNotNull(encontrado);
        assertEquals("Servidor", encontrado.getNombre());
        assertEquals(CategoriaActivo.DISPOSITIVOS, encontrado.getCategoria());
        assertEquals(EstadoActivo.ACTIVO, encontrado.getEstado());
        assertEquals(1, encontrado.getId_usuario());
    }
}
