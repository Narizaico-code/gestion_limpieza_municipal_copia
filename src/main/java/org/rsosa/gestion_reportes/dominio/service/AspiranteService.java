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

    public AspiranteDto obtenerPorCodigo(Long id) {
        return this.aspiranteRepository.obtenerPorCodigo(id);

    }

    public List<AspiranteDto> obtenerPorContratacion(Long codigo){
        return this.aspiranteRepository.obtenerPorContratacion(codigo);
    }

    public AspiranteDto contratarAspirante(Long codigo){
        return this.aspiranteRepository.contratarAspirante(codigo);
    }

    public AspiranteDto guardarAspirante(AspiranteDto aspiranteDto){
        return this.aspiranteRepository.guardarAspirante(aspiranteDto);
    }

    public AspiranteDto actualizarAspirante(Long codigo, AspiranteDto aspiranteDto){
        return this.aspiranteRepository.actualizarAspirante(codigo, aspiranteDto);
    }

    public void eliminarAspirante(Long codigo){
        this.aspiranteRepository.eliminarAspirante(codigo);
    }
}