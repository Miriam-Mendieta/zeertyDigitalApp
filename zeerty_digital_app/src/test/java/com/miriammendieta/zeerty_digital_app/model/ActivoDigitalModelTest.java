package com.miriammendieta.zeerty_digital_app.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * Test unitario del modelo ActivoDigital.
 * Verifica que los getters y setters funcionan correctamente
 * y que el objeto mantiene la integridad de sus datos.
 */
public class ActivoDigitalModelTest {

    @Test
    void testActivoDigitalCampos() {

        // Crear instancia del modelo
        ActivoDigital activo = new ActivoDigital();

        // Asignar valores a los campos mediante setters
        activo.setId_activo(10);
        activo.setNombre("Servidor Linux");
        activo.setDescripcion("Producción");
        activo.setId_usuario(1);
        activo.setFecha_alta(LocalDate.now()); 

        // Verificar que los getters devuelven los valores asignados
        assertEquals(10, activo.getId_activo(), "El ID del activo debe coincidir");
        assertEquals("Servidor Linux", activo.getNombre(), "El nombre debe coincidir");
        assertEquals("Producción", activo.getDescripcion(), "La descripción debe coincidir");
        assertEquals(1, activo.getId_usuario(), "El ID del usuario debe coincidir");
        assertNotNull(activo.getFecha_alta(), "La fecha de alta no debe ser nula");
    }

    @Test
    void testCamposModelo() {

        ActivoDigital a = new ActivoDigital();

        a.setId_activo(1);
        a.setNombre("Servidor");
        a.setDescripcion("Prod");
        a.setId_usuario(5);
        a.setFecha_alta(LocalDate.now()); // evita inconsistencias

        assertEquals(1, a.getId_activo());
        assertEquals("Servidor", a.getNombre());
        assertEquals("Prod", a.getDescripcion());
        assertEquals(5, a.getId_usuario());
        assertNotNull(a.getFecha_alta(), "La fecha de alta debe estar asignada");
    }
}
