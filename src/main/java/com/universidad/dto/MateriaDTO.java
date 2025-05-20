package com.universidad.dto;

import java.io.Serializable;
import java.util.List;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MateriaDTO implements Serializable {

    private Long id;

    @NotBlank(message = "El nombre de la materia es obligatorio")
    @Size(max = 100, message = "El nombre de la materia no puede tener más de 100 caracteres")
    private String nombreMateria;

    @NotBlank(message = "El código único es obligatorio")
    @Size(max = 50, message = "El código único no puede tener más de 50 caracteres")
    private String codigoUnico;

    @NotNull(message = "Los créditos son obligatorios")
    @Min(value = 1, message = "Los créditos deben ser al menos 1")
    private Integer creditos;

    /**
     * Lista de IDs de materias que son prerequisitos para esta materia.
     */
    private List<Long> prerequisitos;

    /**
     * Lista de IDs de materias para las que esta materia es prerequisito.
     */
    private List<Long> esPrerequisitoDe;

}
