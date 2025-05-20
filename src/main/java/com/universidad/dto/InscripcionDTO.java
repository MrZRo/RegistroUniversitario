package com.universidad.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InscripcionDTO implements Serializable {

    /** Identificador único de la inscripción */
    private Long id;

    /** Identificador del estudiante (no nulo y positivo) */
    @NotNull(message = "El ID del estudiante es obligatorio")
    @Positive(message = "El ID del estudiante debe ser un valor positivo")
    private Long estudianteId;

    /** Identificador de la materia (no nulo y positivo) */
    @NotNull(message = "El ID de la materia es obligatorio")
    @Positive(message = "El ID de la materia debe ser un valor positivo")
    private Long materiaId;

    /** Fecha de inscripción (no nula y presente o futura) */
    @NotNull(message = "La fecha de inscripción es obligatoria")
    @PastOrPresent(message = "La fecha de inscripción no puede ser futura")
    private LocalDate fechaInscripcion;
}