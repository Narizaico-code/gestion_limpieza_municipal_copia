package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Personal;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CrudPersonalEntity extends CrudRepository<Personal, Long> {
    Optional<Personal> findFirstByCorreo(String email);
    List<Personal> findAllByEstado(String estado);
}