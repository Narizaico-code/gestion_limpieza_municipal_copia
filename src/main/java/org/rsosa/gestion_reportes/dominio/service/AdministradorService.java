package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.AdministradorDto;
import org.rsosa.gestion_reportes.dominio.repository.AdministradorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministradorService {

    private final AdministradorRepository administradorRepository;

    public AdministradorService(AdministradorRepository administradorRepository) {
        this.administradorRepository = administradorRepository;
    }

    public List<AdministradorDto> obtenerTodo() {
        return this.administradorRepository.obtenerTodo();
    }

    public AdministradorDto obtenerAdministradorPorId(Long id) {
        return this.administradorRepository.obtenerAdministradorPorId(id);
    }

    public AdministradorDto guardarAdministrador(AdministradorDto administradorDto) {
        return this.administradorRepository.guardarAdministrador(administradorDto);
    }

    public AdministradorDto actualizarAdministrador(Long id, AdministradorDto administradorDto) {
        return this.administradorRepository.actualizarAdministrador(id, administradorDto);
    }

    public void eliminarAdministrador(Long id) {
        this.administradorRepository.eliminarAdministrador(id);
    }
}
