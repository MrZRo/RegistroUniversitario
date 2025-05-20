package com.universidad.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "docente")
@EqualsAndHashCode(callSuper = true)
public class Docente extends Persona {

    @Column(name = "nro_empleado", nullable = false, unique = true)
    private String nroEmpleado;

    @Column(name = "departamento", nullable = false)
    private String departamento;

    @OneToMany(mappedBy = "docente", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EvaluacionDocente> evaluaciones;

    // Puedes agregar un constructor sin evaluaciones para facilitar la creación desde DTO
    public Docente(Long id, String nombre, String apellido, String email, 
                   LocalDate fechaNacimiento, String nroEmpleado, String departamento) {
        super(id, null, nombre, apellido, email, fechaNacimiento);
        this.nroEmpleado = nroEmpleado;
        this.departamento = departamento;
    }
}
