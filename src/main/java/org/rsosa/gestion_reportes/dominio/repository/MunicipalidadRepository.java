// src/main/java/org/rsosa/gestion_reportes/dominio/repository/MunicipalidadRepository.java
package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import java.util.List;

public interface MunicipalidadRepository {
    List<MunicipalidadDto> obtenerTodo();
    MunicipalidadDto obtenerMunicipalidadPorId(Long id);
    MunicipalidadDto guardarMunicipalidad(MunicipalidadDto municipalidadDto);
    MunicipalidadDto actualizarMunicipalidad(Long id, MunicipalidadDto municipalidadDto);
    void eliminarMunicipalidad(Long id);
}
