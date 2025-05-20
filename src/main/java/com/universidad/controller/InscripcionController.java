package com.universidad.controller;
import com.universidad.dto.InscripcionDTO;
import com.universidad.service.impl.InscripcionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionServiceImpl inscripcionService;

    @PostMapping
    public ResponseEntity<String> crearInscripcion(@RequestBody InscripcionDTO inscripcionDTO) {
        inscripcionService.crearInscripcion(inscripcionDTO);
        return ResponseEntity.ok("Inscripción creada exitosamente");
    }

    @GetMapping
    public ResponseEntity<List<InscripcionDTO>> listarInscripciones() {
        return ResponseEntity.ok(inscripcionService.listarInscripciones());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.ok("Inscripción eliminada exitosamente");
    }
}