package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;

import java.util.List;

public interface VecinoRepository {
    List<VecinoDto> obtenerTodo();
    VecinoDto obtenerVecinoPorCodigo(Long id);
    VecinoDto guardarVecino(VecinoDto vecinoDto);
    VecinoDto actualizarVecino(Long id, VecinoDto vecinoDto);
    void eliminarVecino(Long id);
}
