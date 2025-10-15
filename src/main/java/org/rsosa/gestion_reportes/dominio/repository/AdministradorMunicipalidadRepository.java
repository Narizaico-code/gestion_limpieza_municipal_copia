package org.rsosa.gestion_reportes.dominio.repository;
import org.rsosa.gestion_reportes.dominio.dto.AdministradorMunicipalidadDto;

import java.util.List;
import java.util.Optional;

public interface AdministradorMunicipalidadRepository {
    List<AdministradorMunicipalidadDto> obtenerTodo();
    Optional<AdministradorMunicipalidadDto> obtenerPorMunicipalidad(Long codigoMunicipalidad);
    List<AdministradorMunicipalidadDto> obtenerPorAdministrador(Long codigoAdmin);
    AdministradorMunicipalidadDto asignarAdminAMunicipalidad(Long codigoAdmin, Long codigoMunicipalidad);
    void desasignarAdminDeMunicipalidad(Long codigoAdminMunicipalidad);
}