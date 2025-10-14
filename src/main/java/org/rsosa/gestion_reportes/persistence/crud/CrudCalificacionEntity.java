package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Calificacion;
import org.springframework.data.repository.CrudRepository;

public interface CrudCalificacionEntity extends CrudRepository<Calificacion, Long> {
}