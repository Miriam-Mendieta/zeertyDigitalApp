package com.miriammendieta.zeerty_digital_app.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.miriammendieta.zeerty_digital_app.model.ActivoDigital;
import com.miriammendieta.zeerty_digital_app.repository.ActivoDigitalRepository;

@Service
public class ActivoDigitalService {

    private final ActivoDigitalRepository repo;

    ActivoDigitalService(ActivoDigitalRepository repo) {
        this.repo = repo;
    }

    public List<ActivoDigital> listar() {
        return repo.findAll(); 
    }

    public void guardar(ActivoDigital activo) {
        repo.save(activo);
    }

    public ActivoDigital buscarPorId(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}
