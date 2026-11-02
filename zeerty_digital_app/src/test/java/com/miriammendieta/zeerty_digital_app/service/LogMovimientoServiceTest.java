package com.miriammendieta.zeerty_digital_app.service;

import com.miriammendieta.zeerty_digital_app.model.TipoAccion;
import com.miriammendieta.zeerty_digital_app.model.LogMovimiento;
import com.miriammendieta.zeerty_digital_app.repository.LogMovimientoRepository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

/**
 * Pruebas mínimas del servicio de auditoría.
 */
public class LogMovimientoServiceTest {

    @Mock
    private LogMovimientoRepository repo;

    @InjectMocks
    private LogMovimientoService service;

    public LogMovimientoServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testRegistrarCrear() {

        // save() devuelve un objeto
        when(repo.save(any())).thenReturn(new LogMovimiento());

        service.registrar(TipoAccion.CREAR, 1, 10);

        verify(repo).save(any());
    }

    @Test
    void testRegistrarEditar() {

        when(repo.save(any())).thenReturn(new LogMovimiento());

        service.registrar(TipoAccion.EDITAR, 1, 10);

        verify(repo).save(any());
    }
}
