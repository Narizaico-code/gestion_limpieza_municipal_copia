package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Administrador;
import org.springframework.data.repository.CrudRepository;

public interface CrudAdministradorEntity extends CrudRepository<Administrador, Long> {
    Administrador findFirstByNombreCompleto(String nombreCompleto);
}