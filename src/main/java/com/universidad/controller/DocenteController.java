package com.universidad.controller;

import com.universidad.dto.DocenteDTO;
import com.universidad.service.IDocenteService;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/docentes")
@Validated
public class DocenteController {

    private final IDocenteService docenteService;
    private static final Logger logger = LoggerFactory.getLogger(DocenteController.class);

    @Autowired
    public DocenteController(IDocenteService docenteService) {
        this.docenteService = docenteService;
    }

    @GetMapping
    public ResponseEntity<List<DocenteDTO>> obtenerTodosLosDocentes() {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio obtenerTodosLosDocentes: {}", inicio);

        List<DocenteDTO> docentes = docenteService.obtenerTodosLosDocentes();

        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin obtenerTodosLosDocentes: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(docentes);
    }

    @GetMapping("/{nroEmpleado}")
    public ResponseEntity<DocenteDTO> obtenerPorNroEmpleado(@PathVariable String nroEmpleado) {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio obtenerPorNroEmpleado: {}", inicio);

        DocenteDTO docente = docenteService.obtenerDocentePorNroEmpleado(nroEmpleado);

        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin obtenerPorNroEmpleado: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.ok(docente);
    }

    @PostMapping
    public ResponseEntity<DocenteDTO> crearDocente(@RequestBody DocenteDTO docenteDTO) {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio crearDocente: {}", docenteDTO.getId());

        DocenteDTO creado = docenteService.crearDocente(docenteDTO);

        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin crearDocente: {} (Duración: {} ms)", fin, (fin - inicio));
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DocenteDTO> actualizarDocente(
            @PathVariable Long id,
            @Valid @RequestBody DocenteDTO docenteDTO) {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio actualizarDocente para ID {}: {}", id, inicio);

        DocenteDTO actualizado = docenteService.actualizarDocente(id, docenteDTO);

        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin actualizarDocente para ID {}: {} (Duración: {} ms)", id, fin, (fin - inicio));
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}/eliminar")
    @Transactional
    public ResponseEntity<Void> eliminarDocente(@PathVariable Long id) {
        long inicio = System.currentTimeMillis();
        logger.info("[DOCENTE] Inicio eliminarDocente para ID {}: {}", id, inicio);

        docenteService.eliminarDocente(id);

        long fin = System.currentTimeMillis();
        logger.info("[DOCENTE] Fin eliminarDocente para ID {}: {} (Duración: {} ms)", id, fin, (fin - inicio));
        return ResponseEntity.noContent().build();
    }
}
