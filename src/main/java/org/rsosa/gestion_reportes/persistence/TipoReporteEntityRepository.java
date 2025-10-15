package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.dominio.repository.TipoReporteRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudTipoReporteEntity;
import org.rsosa.gestion_reportes.persistence.entity.TipoReporte;
import org.rsosa.gestion_reportes.web.mapper.TipoReporteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TipoReporteEntityRepository implements TipoReporteRepository {
    private final CrudTipoReporteEntity crudTipoReporteEntity;
    private final TipoReporteMapper tipoReporteMapper;

    public TipoReporteEntityRepository(CrudTipoReporteEntity crudTipoReporteEntity, TipoReporteMapper tipoReporteMapper) {
        this.crudTipoReporteEntity = crudTipoReporteEntity;
        this.tipoReporteMapper = tipoReporteMapper;
    }

    @Override
    public List<TipoReporteDto> obtenerTodo() {
        return this.tipoReporteMapper.toDto(this.crudTipoReporteEntity.findAll());
    }

    @Override
    public TipoReporteDto obtenerTipoReportePorCodigo(Long id) {
        TipoReporte tipoReporte = this.crudTipoReporteEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Tipo de Reporte con código " + id + " no existe"));
        return this.tipoReporteMapper.toDto(tipoReporte);
    }

    @Override
    public TipoReporteDto guardarTipoReporte(TipoReporteDto tipoReporteDto) {
        TipoReporte tipoReporte = this.tipoReporteMapper.toEntity(tipoReporteDto);
        tipoReporte = this.crudTipoReporteEntity.save(tipoReporte);
        return this.tipoReporteMapper.toDto(tipoReporte);
    }

    @Override
    public TipoReporteDto actualizarTipoReporte(Long id, TipoReporteDto tipoReporteDto) {
        TipoReporte tipoReporte = this.crudTipoReporteEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Tipo de Reporte con código " + id + " no existe"));
        this.tipoReporteMapper.updateEntityFromDto(tipoReporteDto, tipoReporte);
        tipoReporte = this.crudTipoReporteEntity.save(tipoReporte);
        return this.tipoReporteMapper.toDto(tipoReporte);
    }

    @Override
    public void eliminarTipoReporte(Long id) {
        TipoReporte tipoReporte = this.crudTipoReporteEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Tipo de Reporte con código " + id + " no existe"));
        this.crudTipoReporteEntity.deleteById(id);
    }
}