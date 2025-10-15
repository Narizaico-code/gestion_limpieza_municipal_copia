// src/main/java/org/rsosa/gestion_reportes/dominio/repository/ContratacionRepository.java
package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import java.util.List;

public interface ContratacionRepository {
    List<ContratacionDto> obtenerTodo();
    ContratacionDto obtenerPorCodigo(Long id);
    ContratacionDto guardarContratacion(ContratacionDto contratacionDto);
    ContratacionDto actualizarContratacion(Long id, ContratacionDto contratacionDto);
    void eliminarContratacion(Long id);
    List<ContratacionDto> obtenerPorMunicipalidad(Long codigoMunicipalidad);
}