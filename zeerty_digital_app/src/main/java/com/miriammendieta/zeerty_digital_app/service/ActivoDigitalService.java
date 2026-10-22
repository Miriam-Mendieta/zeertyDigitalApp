package com.miriammendieta.zeerty_digital_app.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.repository.ActivoDigitalRepository;

@Service
public class ActivoDigitalService {

    private final ActivoDigitalRepository repo = null;

    public List<ActivoDigital> listar() {
        return repo.findAll(); // LISTADO DE LA BD
    }

    public void guardar(ActivoDigital activo) {
        repo.save(activo);
    }
}
