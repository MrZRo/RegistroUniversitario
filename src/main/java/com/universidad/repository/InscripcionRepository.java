package com.universidad.repository;

import com.universidad.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {
    List<Inscripcion> findByEstudianteId(Long estudianteId);
    List<Inscripcion> findByMateriaId(Long materiaId);
    // Método para verificar si ya existe una inscripción para un estudiante y materia
    boolean existsByEstudianteIdAndMateriaId(Long estudianteId, Long materiaId);
}