package com.universidad.repository;

import com.universidad.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Lock;
import jakarta.persistence.LockModeType;
import java.util.Optional;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {
    

    Boolean existsByEmail(String email);


    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Docente> findById(Long id);


    Optional<Docente> findByNroEmpleado(String nroEmpleado);


}
