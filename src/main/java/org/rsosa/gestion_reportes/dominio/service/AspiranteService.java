package org.rsosa.gestion_reportes.dominio.service;

import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.dominio.repository.AspiranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AspiranteService {

    private final AspiranteRepository aspiranteRepository;

    public AspiranteService(AspiranteRepository aspiranteRepository) {
        this.aspiranteRepository = aspiranteRepository;
    }

    public List<AspiranteDto> obtenerTodo() {
        return this.aspiranteRepository.obtenerTodo();
    }

    public AspiranteDto obtenerAspirantePorId(Long id) {
        return this.aspiranteRepository.obtenerAspirantePorId(id);
    }

    public AspiranteDto guardarAspirante(AspiranteDto aspiranteDto) {
        return this.aspiranteRepository.guardarAspirante(aspiranteDto);
    }

    public AspiranteDto actualizarAspirante(Long id, AspiranteDto aspiranteDto) {
        return this.aspiranteRepository.actualizarAspirante(id, aspiranteDto);
    }

    public void eliminarAspirante(Long id) {
        this.aspiranteRepository.eliminarAspirante(id);
    }
}
