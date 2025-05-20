package com.universidad.controller;

import com.universidad.model.EvaluacionDocente;
import com.universidad.service.IEvaluacionDocenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/evaluaciones-docente")
public class EvaluacionDocenteController {

    private static final Logger logger = LoggerFactory.getLogger(EvaluacionDocenteController.class);

    @Autowired
    private IEvaluacionDocenteService evaluacionDocenteService;

    @PostMapping
    public ResponseEntity<EvaluacionDocente> crearEvaluacion(@RequestBody EvaluacionDocente evaluacion) {
        long inicio = System.currentTimeMillis();
        logger.info("[EVALUACION] Inicio crearEvaluacion para docenteId={} - timestamp={}", evaluacion.getDocente().getId(), inicio);

        EvaluacionDocente nueva = evaluacionDocenteService.crearEvaluacion(evaluacion);

        long fin = System.currentTimeMillis();
        logger.info("[EVALUACION] Fin crearEvaluacion - ID generada={} - Duración: {} ms", nueva.getId(), (fin - inicio));
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @GetMapping("/docente/{docenteId}")
    public ResponseEntity<List<EvaluacionDocente>> obtenerEvaluacionesPorDocente(@PathVariable Long docenteId) {
        long inicio = System.currentTimeMillis();
        logger.info("[EVALUACION] Inicio obtenerEvaluacionesPorDocente para docenteId={}", docenteId);

        List<EvaluacionDocente> evaluaciones = evaluacionDocenteService.obtenerEvaluacionesPorDocente(docenteId);

        long fin = System.currentTimeMillis();
        logger.info("[EVALUACION] Fin obtenerEvaluacionesPorDocente para docenteId={} - Cantidad: {} - Duración: {} ms",
                docenteId, evaluaciones.size(), (fin - inicio));
        return ResponseEntity.ok(evaluaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EvaluacionDocente> obtenerEvaluacionPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[EVALUACION] Inicio obtenerEvaluacionPorId para ID={}", id);

        EvaluacionDocente evaluacion = evaluacionDocenteService.obtenerEvaluacionPorId(id);
        if (evaluacion == null) {
            logger.warn("[EVALUACION] Evaluación no encontrada para ID={}", id);
            return ResponseEntity.notFound().build();
        }

        long fin = System.currentTimeMillis();
        logger.info("[EVALUACION] Fin obtenerEvaluacionPorId para ID={} - Duración: {} ms", id, (fin - inicio));
        return ResponseEntity.ok(evaluacion);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarEvaluacion(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[EVALUACION] Inicio eliminarEvaluacion para ID={}", id);

        evaluacionDocenteService.eliminarEvaluacion(id);

        long fin = System.currentTimeMillis();
        logger.info("[EVALUACION] Fin eliminarEvaluacion para ID={} - Duración: {} ms", id, (fin - inicio));
        return ResponseEntity.noContent().build();
    }
}
