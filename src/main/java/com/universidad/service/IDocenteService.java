package com.universidad.service;

import com.universidad.dto.DocenteDTO;
import com.universidad.model.Docente;


import java.util.List;

public interface IDocenteService {

    /**
     * Obtiene todos los docentes.
     * @return Lista de DocenteDTO.
     */
    List<DocenteDTO> obtenerTodosLosDocentes();

    /**
     * Crea un nuevo docente.
     * @param docenteDTO DTO del docente a crear.
     * @return DocenteDTO creado.
     */
    DocenteDTO crearDocente(DocenteDTO docenteDTO);

    /**
     * Actualiza un docente existente.
     * @param id ID del docente a actualizar.
     * @param docenteDTO DTO con los datos actualizados.
     * @return DocenteDTO actualizado.
     * @throws RuntimeException si no se encuentra el docente.
     */
    DocenteDTO actualizarDocente(Long id, DocenteDTO docenteDTO);

    /**
     * Elimina físicamente un docente por su ID.
     * @param id ID del docente a eliminar.
     * @param docenteDTO DTO con datos del docente a eliminar (opcionalmente para retorno).
     * @return DocenteDTO del docente eliminado.
     */
    // Cambiar firma para eliminar solo con el id
    void eliminarDocente(Long id);


    /**
     * Obtiene un docente por su número de empleado.
     * 
     * @param nroEmpleado Número de empleado del docente a buscar.
     * @return DocenteDTO correspondiente al número de empleado.
     */
    DocenteDTO obtenerDocentePorNroEmpleado(String nroEmpleado);


}
