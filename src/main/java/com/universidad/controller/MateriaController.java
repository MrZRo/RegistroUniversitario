package com.universidad.controller;

import com.universidad.model.Materia;
import com.universidad.service.IMateriaService;
import com.universidad.dto.MateriaDTO;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@RestController
@RequestMapping("/api/materias")
public class MateriaController {

    private final IMateriaService materiaService;
    private static final Logger logger = LoggerFactory.getLogger(MateriaController.class);

    @Autowired
    public MateriaController(IMateriaService materiaService) {
        this.materiaService = materiaService;
    }

    @GetMapping
    public ResponseEntity<List<MateriaDTO>> obtenerTodasLasMaterias() {
        long inicio = System.currentTimeMillis();
        logger.info("[MATERIA] Inicio obtenerTodasLasMaterias: {}", inicio);
        List<MateriaDTO> result = materiaService.obtenerTodasLasMaterias();
        long fin = System.currentTimeMillis();
        logger.info("[MATERIA] Fin obtenerTodasLasMaterias: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MateriaDTO> obtenerMateriaPorId(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[MATERIA] Inicio obtenerMateriaPorId: ID={}", id);
        MateriaDTO materia = materiaService.obtenerMateriaPorId(id);
        long fin = System.currentTimeMillis();
        if (materia == null) {
            logger.warn("[MATERIA] Materia no encontrada: ID={}", id);
            return ResponseEntity.notFound().build();
        }
        logger.info("[MATERIA] Fin obtenerMateriaPorId: ID={} (Duración: {} ms)", id, (fin - inicio));
        return ResponseEntity.ok(materia);
    }

    @GetMapping("/codigo/{codigoUnico}")
    public ResponseEntity<MateriaDTO> obtenerMateriaPorCodigoUnico(@PathVariable String codigoUnico) {
        logger.info("[MATERIA] Buscar por código único: {}", codigoUnico);
        MateriaDTO materia = materiaService.obtenerMateriaPorCodigoUnico(codigoUnico);
        if (materia == null) {
            logger.warn("[MATERIA] No encontrada por código único: {}", codigoUnico);
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(materia);
    }

    @PostMapping
    public ResponseEntity<MateriaDTO> crearMateria(@RequestBody MateriaDTO materia) {
        logger.info("[MATERIA] Inicio crearMateria: código={}", materia.getCodigoUnico());
        MateriaDTO nueva = materiaService.crearMateria(materia);
        logger.info("[MATERIA] Fin crearMateria: ID={} código={}", nueva.getId(), nueva.getCodigoUnico());
        return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MateriaDTO> actualizarMateria(@PathVariable Long id, @RequestBody MateriaDTO materia) {
        logger.info("[MATERIA] Inicio actualizarMateria: ID={}", id);
        MateriaDTO actualizadaDTO = materiaService.actualizarMateria(id, materia);
        logger.info("[MATERIA] Fin actualizarMateria: ID={}", id);
        return ResponseEntity.ok(actualizadaDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Long id) {
        logger.info("[MATERIA] Eliminar materia: ID={}", id);
        materiaService.eliminarMateria(id);
        logger.info("[MATERIA] Materia eliminada: ID={}", id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/formaria-circulo/{materiaId}/{prerequisitoId}")
    @Transactional
    public ResponseEntity<Boolean> formariaCirculo(@PathVariable Long materiaId, @PathVariable Long prerequisitoId) {
        logger.info("[MATERIA] Verificando ciclo: materiaId={} prerequisitoId={}", materiaId, prerequisitoId);
        MateriaDTO materiaDTO = materiaService.obtenerMateriaPorId(materiaId);
        if (materiaDTO == null) {
            logger.warn("[MATERIA] Materia no encontrada al verificar ciclo: ID={}", materiaId);
            return ResponseEntity.notFound().build();
        }

        Materia materia = new Materia(materiaDTO.getId(), materiaDTO.getNombreMateria(), materiaDTO.getCodigoUnico());
        boolean circulo = materia.formariaCirculo(prerequisitoId);
        if (circulo) {
            logger.warn("[MATERIA] Ciclo detectado al intentar agregar prerequisitoId={} a materiaId={}", prerequisitoId, materiaId);
            return ResponseEntity.badRequest().body(true);
        }
        logger.info("[MATERIA] No se detectó ciclo al agregar prerequisitoId={} a materiaId={}", prerequisitoId, materiaId);
        return ResponseEntity.ok(false);
    }
}
