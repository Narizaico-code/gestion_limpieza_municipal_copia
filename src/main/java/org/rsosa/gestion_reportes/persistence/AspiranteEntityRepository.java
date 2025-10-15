package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.dominio.repository.AspiranteRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudAspiranteEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudContratacionEntity;
import org.rsosa.gestion_reportes.persistence.entity.Aspirante;
import org.rsosa.gestion_reportes.persistence.entity.Contratacion;
import org.rsosa.gestion_reportes.web.mapper.AspiranteMapper;
import org.springframework.stereotype.Repository;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
public class AspiranteEntityRepository implements AspiranteRepository {
    private final CrudAspiranteEntity crudAspiranteEntity;
    private final CrudContratacionEntity crudContratacionEntity;
    private final AspiranteMapper aspiranteMapper;

    public AspiranteEntityRepository(
            CrudAspiranteEntity crudAspiranteEntity,
            CrudContratacionEntity crudContratacionEntity,
            AspiranteMapper aspiranteMapper
    ) {
        this.crudAspiranteEntity = crudAspiranteEntity;
        this.crudContratacionEntity = crudContratacionEntity;
        this.aspiranteMapper = aspiranteMapper;
    }

    @Override
    public List<AspiranteDto> obtenerTodo() {
        return this.aspiranteMapper.toDto((List<Aspirante>) this.crudAspiranteEntity.findAll());
    }

    @Override
    public AspiranteDto obtenerPorCodigo(Long id) {
        Aspirante aspirante = this.crudAspiranteEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Aspirante con código " + id + " no existe."));
        return this.aspiranteMapper.toDto(aspirante);
    }

    @Override
    public AspiranteDto guardarAspirante(AspiranteDto aspiranteDto) {
        Aspirante aspirante = this.aspiranteMapper.toEntity(aspiranteDto);
        aspirante = this.crudAspiranteEntity.save(aspirante);
        return this.aspiranteMapper.toDto(aspirante);
    }

    @Override
    public AspiranteDto actualizarAspirante(Long id, AspiranteDto aspiranteDto) {
        Aspirante aspirante = this.crudAspiranteEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Aspirante con código " + id + " no existe."));
        this.aspiranteMapper.updateEntityFromDto(aspiranteDto, aspirante);
        aspirante = this.crudAspiranteEntity.save(aspirante);
        return this.aspiranteMapper.toDto(aspirante);
    }

    @Override
    public void eliminarAspirante(Long id) {
        Aspirante aspirante = this.crudAspiranteEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Aspirante con código " + id + " no existe."));
        this.crudAspiranteEntity.deleteById(id);
    }

    @Override
    public List<AspiranteDto> obtenerPorContratacion(Long codigoContratacion) {
        return this.aspiranteMapper.toDto(this.crudAspiranteEntity.findByContratacion_CodigoContratacion(codigoContratacion));
    }

    @Override
    @Transactional
    public AspiranteDto contratarAspirante(Long codigoAspirante) {
        Aspirante aspirante = this.crudAspiranteEntity.findById(codigoAspirante)
                .orElseThrow(() -> new CatalogoInvalidoException("Aspirante con código " + codigoAspirante + " no existe."));

        Contratacion contratacion = aspirante.getContratacion();

        if (contratacion == null) {
            throw new CatalogoInvalidoException("El aspirante no tiene una contratación asociada.");
        }

        if (contratacion.getVacantesDisponibles() == null || contratacion.getVacantesDisponibles() <= 0) {
            throw new CatalogoInvalidoException("No hay vacantes disponibles en esta contratación.");
        }

        // Decrementar vacantes
        contratacion.setVacantesDisponibles(contratacion.getVacantesDisponibles() - 1);
        this.crudContratacionEntity.save(contratacion);

        // Marcar aspirante como contratado (puedes agregar un campo "estado" si lo necesitas)
        this.crudAspiranteEntity.save(aspirante);

        return this.aspiranteMapper.toDto(aspirante);
    }
}