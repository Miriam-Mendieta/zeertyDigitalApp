package com.miriammendieta.zeerty_digital_app.service;

import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.repository.ActivoDigitalRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas mínimas del servicio de activos.
 * Valida crear, editar y eliminar.
 */
public class ActivoDigitalServiceTest {

    @Mock
    private ActivoDigitalRepository repo;

    @InjectMocks
    private ActivoDigitalService service;

    public ActivoDigitalServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

  @Test
void testCrear() {
    ActivoDigital a = new ActivoDigital();
    a.setNombre("Servidor");

    when(repo.save(any())).thenReturn(a);

    ActivoDigital res = service.guardar(a);

    assertEquals("Servidor", res.getNombre());
    verify(repo).save(a);
}


    @Test
    void testEditar() {
        ActivoDigital a = new ActivoDigital();
        a.setId_activo(10);
        a.setNombre("Editado");

        when(repo.save(any())).thenReturn(a);

        ActivoDigital res = service.guardar(a);

        assertEquals("Editado", res.getNombre());
        verify(repo).save(a);
    }

    @Test
    void testEliminar() {
        doNothing().when(repo).deleteById(10);

        service.eliminar(10);

        verify(repo).deleteById(10);
    }
}

