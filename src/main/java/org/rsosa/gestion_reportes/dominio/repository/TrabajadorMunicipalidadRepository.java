package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;

import java.util.List;

public interface TrabajadorMunicipalidadRepository {
    List<TrabajadorMunicipalDto> obtenerTodo();
    TrabajadorMunicipalDto obtenerPorCodigo(Long id);
    TrabajadorMunicipalDto guardarTrabajadorMunicipalidad(TrabajadorMunicipalDto trabajadorMunicipalDto);
    TrabajadorMunicipalDto actualizarTrabajadorMunicipalidad(Long id, TrabajadorMunicipalDto trabajadorMunicipalDto);
    void eliminarTrabajadorMunicipalidad(Long id);
    List<TrabajadorMunicipalDto> obtenerPorPersonal(Long codigoPersonal);
    List<TrabajadorMunicipalDto> obtenerPorMunicipalidad(Long codigoMunicipalidad);
    List<TrabajadorMunicipalDto> obtenerPorZona(String zona);
}