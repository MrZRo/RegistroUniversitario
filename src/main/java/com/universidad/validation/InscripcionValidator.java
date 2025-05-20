package com.universidad.validation;

import com.universidad.dto.InscripcionDTO;
import com.universidad.repository.InscripcionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InscripcionValidator {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    public void validar(InscripcionDTO inscripcionDTO) {
        boolean existe = inscripcionRepository.existsById(inscripcionDTO.getId());
        if (existe) {
            throw new RuntimeException("La inscripción ya existe.");
        }
    }
}