package com.universidad.service.impl;

import com.universidad.dto.InscripcionDTO;
import com.universidad.model.Estudiante;
import com.universidad.model.Inscripcion;
import com.universidad.model.Materia;
import com.universidad.repository.EstudianteRepository;
import com.universidad.repository.InscripcionRepository;
import com.universidad.repository.MateriaRepository;
import com.universidad.validation.InscripcionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class InscripcionServiceImpl {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Autowired
    private MateriaRepository materiaRepository;

    @Autowired
    private InscripcionValidator inscripcionValidator;

    /**
     * Crea una inscripción en la base de datos validando la existencia de Estudiante y Materia.
     * Esta transacción permite escritura para evitar errores de "read-only".
     */
    @Transactional(readOnly = false)
    public InscripcionDTO crearInscripcion(InscripcionDTO inscripcionDTO) {
        // ✅ Validar que los IDs no sean nulos
        if (inscripcionDTO.getEstudianteId() == null || inscripcionDTO.getMateriaId() == null) {
            throw new RuntimeException("❌ Los IDs de Estudiante y Materia no pueden ser nulos");
        }

        // ✅ Validar existencia del estudiante
        Estudiante estudiante = estudianteRepository.findById(inscripcionDTO.getEstudianteId())
                .orElseThrow(() -> new RuntimeException("❌ Estudiante con ID " + inscripcionDTO.getEstudianteId() + " no encontrado"));

        // ✅ Validar existencia de la materia
        Materia materia = materiaRepository.findById(inscripcionDTO.getMateriaId())
                .orElseThrow(() -> new RuntimeException("❌ Materia con ID " + inscripcionDTO.getMateriaId() + " no encontrada"));

        // ✅ Validar que el estudiante no esté ya inscrito en esa materia (opcional)
        if (inscripcionRepository.existsByEstudianteIdAndMateriaId(estudiante.getId(), materia.getId())) {
            throw new RuntimeException("❌ El estudiante ya está inscrito en esta materia");
        }

        // ✅ Crear inscripción
        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setEstudiante(estudiante);
        inscripcion.setMateria(materia);
        inscripcion.setFechaInscripcion(LocalDate.now());

        // ✅ Guardar en la base de datos
        inscripcionRepository.save(inscripcion);

        return convertToDTO(inscripcion);
    }

    /**
     * Lista todas las inscripciones.
     * Esta transacción es de solo lectura para optimizar el rendimiento.
     */
    @Transactional(readOnly = true)
    public List<InscripcionDTO> listarInscripciones() {
        return inscripcionRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Elimina una inscripción de la base de datos.
     */
    @Transactional
    public void eliminarInscripcion(Long id) {
        if (!inscripcionRepository.existsById(id)) {
            throw new RuntimeException("❌ Inscripción no encontrada");
        }
        inscripcionRepository.deleteById(id);
    }

    /**
     * Convierte una entidad Inscripcion a un DTO.
     */
    private InscripcionDTO convertToDTO(Inscripcion inscripcion) {
        return new InscripcionDTO(inscripcion.getId(),
                                  inscripcion.getEstudiante().getId(),
                                  inscripcion.getMateria().getId(),
                                  inscripcion.getFechaInscripcion());
    }
}