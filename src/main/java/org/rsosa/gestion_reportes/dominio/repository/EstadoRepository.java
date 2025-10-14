package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.EstadoDto;

import java.util.List;

public interface EstadoRepository {
    List<EstadoDto> obtenerTodo();
    EstadoDto obtenerEstadoPorCodigo(Long id);
    EstadoDto obtenerEstadoPorNombre(String nombre);
    EstadoDto guardarEstado(EstadoDto estadoDto);
    EstadoDto actualizarEstado(Long id, EstadoDto estadoDto);
    void eliminarEstado(Long id);
}