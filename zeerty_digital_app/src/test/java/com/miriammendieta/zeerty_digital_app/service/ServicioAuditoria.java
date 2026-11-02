package com.miriammendieta.zeerty_digital_app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.miriammendieta.zeerty_digital_app.model.LogMovimiento;

@Service
public class ServicioAuditoria {

    // método que devuelve la lista de movimientos
    public List<LogMovimiento> obtenerMovimientos() {
        return new ArrayList<>();
    }
}
