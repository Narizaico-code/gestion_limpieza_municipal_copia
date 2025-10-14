package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Estado;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CrudEstadoEntity extends CrudRepository<Estado, Long> {
    Optional<Estado> findByNombre(String nombre);
}