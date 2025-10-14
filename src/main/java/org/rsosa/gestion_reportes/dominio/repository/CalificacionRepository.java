package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.CalificacionDto;

import java.util.List;

public interface CalificacionRepository {
    CalificacionDto guardarCalificacion(CalificacionDto dto);
    List<CalificacionDto> obtenerTodo();
}