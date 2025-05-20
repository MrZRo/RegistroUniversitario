package com.universidad.controller; // Define el paquete al que pertenece esta clase

import com.universidad.dto.EstudianteDTO; // Importa la clase EstudianteDTO del paquete dto
import com.universidad.model.Materia;
import com.universidad.model.Estudiante;
import com.universidad.service.IEstudianteService; // Importa la interfaz IEstudianteService del paquete service

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired; // Importa la anotación Autowired de Spring
import org.springframework.http.ResponseEntity; // Importa la clase ResponseEntity de Spring para manejar respuestas HTTP
import org.springframework.validation.annotation.Validated;
import org.springframework.http.HttpStatus; // Importa la clase HttpStatus de Spring para manejar códigos de estado HTTP
import org.springframework.web.bind.annotation.*; // Importa las anotaciones de Spring para controladores web
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List; // Importa la interfaz List para manejar listas

@RestController
@RequestMapping("/api/estudiantes")
@Validated
public class EstudianteController {

    private final IEstudianteService estudianteService;
    private static final Logger logger = LoggerFactory.getLogger(EstudianteController.class);

    @Autowired
    public EstudianteController(IEstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

    @GetMapping
    public ResponseEntity<List<EstudianteDTO>> obtenerTodosLosEstudiantes() {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerTodosLosEstudiantes: {}", inicio);
        
        List<EstudianteDTO> estudiantes = estudianteService.obtenerTodosLosEstudiantes();
        
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerTodosLosEstudiantes: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping("/inscripcion/{numeroInscripcion}")
    public ResponseEntity<EstudianteDTO> obtenerEstudiantePorNumeroInscripcion(@PathVariable String numeroInscripcion) {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerEstudiantePorNumeroInscripcion para {}: {}", numeroInscripcion, inicio);
        
        EstudianteDTO estudiante = estudianteService.obtenerEstudiantePorNumeroInscripcion(numeroInscripcion);
        
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerEstudiantePorNumeroInscripcion para {}: {} (Duración: {} ms)", numeroInscripcion, fin, (fin - inicio));
        return ResponseEntity.ok(estudiante);
    }

    @GetMapping("/{id}/materias")
    public ResponseEntity<List<Materia>> obtenerMateriasDeEstudiante(@PathVariable("id") Long estudianteId) {
        logger.info("[ESTUDIANTE] Inicio obtenerMateriasDeEstudiante para ID: {}", estudianteId);
        
        List<Materia> materias = estudianteService.obtenerMateriasDeEstudiante(estudianteId);
        
        logger.info("[ESTUDIANTE] Fin obtenerMateriasDeEstudiante para ID: {}", estudianteId);
        return ResponseEntity.ok(materias);
    }

    @GetMapping("/{id}/lock")
    public ResponseEntity<Estudiante> getEstudianteConBloqueo(@PathVariable Long id) {
        logger.info("[ESTUDIANTE] Inicio getEstudianteConBloqueo para ID: {}", id);
        
        Estudiante estudiante = estudianteService.obtenerEstudianteConBloqueo(id);
        
        logger.info("[ESTUDIANTE] Fin getEstudianteConBloqueo para ID: {}", id);
        return ResponseEntity.ok(estudiante);
    }

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<EstudianteDTO> crearEstudiante(@Valid @RequestBody EstudianteDTO estudianteDTO) {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio crearEstudiante: {}", inicio);
        
        EstudianteDTO nuevoEstudiante = estudianteService.crearEstudiante(estudianteDTO);
        
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin crearEstudiante: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.status(201).body(nuevoEstudiante);
    }

    @PutMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EstudianteDTO> actualizarEstudiante(
            @PathVariable Long id,
            @RequestBody EstudianteDTO estudianteDTO) {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio actualizarEstudiante para ID {}: {}", id, inicio);
        
        EstudianteDTO estudianteActualizado = estudianteService.actualizarEstudiante(id, estudianteDTO);
        
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin actualizarEstudiante para ID {}: {} (Duración: {} ms)", id, fin, (fin - inicio));
        return ResponseEntity.ok(estudianteActualizado);
    }

    @PutMapping("/{id}/baja")
    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<EstudianteDTO> eliminarEstudiante(
            @PathVariable Long id,
            @RequestBody EstudianteDTO estudianteDTO) {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio eliminarEstudiante para ID {}: {}", id, inicio);
        
        EstudianteDTO estudianteEliminado = estudianteService.eliminarEstudiante(id, estudianteDTO);
        
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin eliminarEstudiante para ID {}: {} (Duración: {} ms)", id, fin, (fin - inicio));
        return ResponseEntity.ok(estudianteEliminado);
    }

    @GetMapping("/activos")
    public ResponseEntity<List<EstudianteDTO>> obtenerEstudianteActivo() {
        long inicio = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Inicio obtenerEstudianteActivo: {}", inicio);
        
        List<EstudianteDTO> estudiantesActivos = estudianteService.obtenerEstudianteActivo();
        
        long fin = System.currentTimeMillis();
        logger.info("[ESTUDIANTE] Fin obtenerEstudianteActivo: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(estudiantesActivos);
    }
    
/**
     * Elimina (lógicamente) un estudiante por su ID.
     * @param id ID del estudiante.
     * @param estudianteDTO DTO que contiene el motivo de baja.
     * @return EstudianteDTO actualizado con estado inactivo.
     */
    @DeleteMapping("/{id}")
    public EstudianteDTO eliminaEstudiante(@PathVariable Long id, @RequestBody EstudianteDTO estudianteDTO) {
        return estudianteService.eliminaEstudiante(id, estudianteDTO);
    }
}
