package com.miriammendieta.zeerty_digital_app.service;

import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.miriammendieta.zeerty_digital_app.model.LogMovimiento;
import com.miriammendieta.zeerty_digital_app.model.TipoAccion;
import com.miriammendieta.zeerty_digital_app.repository.LogMovimientoRepository;

@Service
public class LogMovimientoService {

    private final LogMovimientoRepository repo;

    // Inyección por constructor 
    public LogMovimientoService(LogMovimientoRepository repo) {
        this.repo = repo;
    }

    public void registrar(TipoAccion accion, Integer idUsuario, Integer idActivo) {

        LogMovimiento log = new LogMovimiento();
        log.setFechaHora(LocalDateTime.now());
        log.setTipoAccion(accion);
        log.setIdUsuario(idUsuario);
        log.setIdActivo(idActivo);

        repo.save(log);
    }
}