package org.rsosa.gestion_reportes.persistence.crud;

import org.rsosa.gestion_reportes.persistence.entity.Vecino;
import org.springframework.data.repository.CrudRepository;

public interface CrudVecinoEntity extends CrudRepository<Vecino, Long> {
    Vecino findFirstByCorreoElectronico(String correoElectronico);
}