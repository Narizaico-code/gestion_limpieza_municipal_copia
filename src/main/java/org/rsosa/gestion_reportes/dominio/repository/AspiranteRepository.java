// src/main/java/org/rsosa/gestion_reportes/dominio/repository/AspiranteRepository.java
package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import java.util.List;

public interface AspiranteRepository {
    List<AspiranteDto> obtenerTodo();
    AspiranteDto obtenerAspirantePorId(Long id);
    AspiranteDto guardarAspirante(AspiranteDto aspiranteDto);
    AspiranteDto actualizarAspirante(Long id, AspiranteDto aspiranteDto);
    void eliminarAspirante(Long id);
}
