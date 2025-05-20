package com.universidad.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO que representa los datos de un docente para transferencia entre capas.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocenteDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El apellido es obligatorio")
    @Size(min = 3, max = 50, message = "El apellido debe tener entre 3 y 50 caracteres")
    private String apellido;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El email no es válido")
    @Size(max = 100, message = "El email no puede tener más de 100 caracteres")
    private String email;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser anterior a la fecha actual")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El número de empleado es obligatorio")
    @Size(min = 5, max = 20, message = "El número de empleado debe tener entre 5 y 20 caracteres")
    private String nroEmpleado;

    @NotBlank(message = "El departamento es obligatorio")
    @Size(min = 3, max = 100, message = "El departamento debe tener entre 3 y 100 caracteres")
    private String departamento;
}
