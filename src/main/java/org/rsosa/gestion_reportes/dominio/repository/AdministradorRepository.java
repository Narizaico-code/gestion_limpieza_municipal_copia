package org.rsosa.gestion_reportes.dominio.repository;

import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AdministradorRepository {
    List<AdministradorDto> obtenerTodo();
    AdministradorDto obtenerAdministradorPorId(Long id);
    AdministradorDto guardarAdministrador(AdministradorDto administradorDto);
    AdministradorDto actualizarAdministrador(Long id, AdministradorDto administradorDto);
    void eliminarAdministrador(Long id);
}


