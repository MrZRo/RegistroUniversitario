package com.universidad.validation;

import com.universidad.dto.MateriaDTO;
import com.universidad.model.Materia;
import com.universidad.repository.MateriaRepository;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MateriaValidator {

    @Autowired
    private MateriaRepository materiaRepository;

    public void validarMateriaParaCrear(MateriaDTO dto) {
        // Validar unicidad de código único
        if (materiaRepository.findByCodigoUnico(dto.getCodigoUnico()) != null) {
            throw new ValidationException("El código único ya está en uso.");
        }

        validarPrerequisitos(dto, null);
    }

    public void validarMateriaParaActualizar(MateriaDTO dto, Long idExistente) {
        // Validar existencia
        Materia materiaExistente = materiaRepository.findById(idExistente)
                .orElseThrow(() -> new ValidationException("Materia no encontrada para actualizar."));

        // Validar unicidad si cambió el código
        Materia materiaConMismoCodigo = materiaRepository.findByCodigoUnico(dto.getCodigoUnico());
        if (materiaConMismoCodigo != null && !materiaConMismoCodigo.getId().equals(idExistente)) {
            throw new ValidationException("Otro registro ya usa este código único.");
        }

        validarPrerequisitos(dto, materiaExistente);
    }

    private void validarPrerequisitos(MateriaDTO dto, Materia materiaExistente) {
        if (dto.getPrerequisitos() == null || dto.getPrerequisitos().isEmpty()) return;

        Set<Long> prereqIds = new HashSet<>(dto.getPrerequisitos());

        // Validar que no se apunte a sí misma
        if (dto.getId() != null && prereqIds.contains(dto.getId())) {
            throw new ValidationException("Una materia no puede ser prerequisito de sí misma.");
        }

        // Cargar materias desde la BD
        List<Materia> prerequisitos = materiaRepository.findAllById(prereqIds);

        // Usar lógica de detección de ciclos
        Materia simulada = materiaExistente != null
                ? materiaExistente
                : new Materia(dto.getId(), dto.getNombreMateria(), dto.getCodigoUnico());

        simulada.setPrerequisitos(prerequisitos);

        for (Materia prereq : prerequisitos) {
            if (simulada.formariaCirculo(prereq.getId())) {
                throw new ValidationException("La relación de prerequisitos generaría un ciclo con la materia: " + prereq.getNombreMateria());
            }
        }
    }
}
