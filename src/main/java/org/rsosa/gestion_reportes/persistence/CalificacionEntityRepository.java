package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.CalificacionDto;
import org.rsosa.gestion_reportes.dominio.exception.ReporteNoExisteException;
import org.rsosa.gestion_reportes.dominio.exception.VecinoNoExisteException;
import org.rsosa.gestion_reportes.dominio.repository.CalificacionRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudCalificacionEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudReporteEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudVecinoEntity;
import org.rsosa.gestion_reportes.persistence.entity.Calificacion;
import org.rsosa.gestion_reportes.persistence.entity.Reporte;
import org.rsosa.gestion_reportes.persistence.entity.Vecino;
import org.rsosa.gestion_reportes.web.mapper.CalificacionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CalificacionEntityRepository implements CalificacionRepository {

    private final CrudCalificacionEntity crudCalificacionEntity;
    private final CrudReporteEntity crudReporteEntity;
    private final CrudVecinoEntity crudVecinoEntity;
    private final CalificacionMapper mapper;

    public CalificacionEntityRepository(CrudCalificacionEntity crudCalificacionEntity, CrudReporteEntity crudReporteEntity, CrudVecinoEntity crudVecinoEntity, CalificacionMapper mapper) {
        this.crudCalificacionEntity = crudCalificacionEntity;
        this.crudReporteEntity = crudReporteEntity;
        this.crudVecinoEntity = crudVecinoEntity;
        this.mapper = mapper;
    }

    @Override
    public CalificacionDto guardarCalificacion(CalificacionDto dto) {
        Long codigoReporte = dto.report().reportId();
        Reporte reporte = crudReporteEntity.findById(codigoReporte)
                .orElseThrow(() -> new ReporteNoExisteException(codigoReporte));

        Long codigoVecino = dto.neighbor().neighborId();
        Vecino vecino = crudVecinoEntity.findById(codigoVecino)
                .orElseThrow(() -> new VecinoNoExisteException("Vecino con ID " + codigoVecino + " no encontrado."));

        Calificacion calificacion = mapper.toEntity(dto);
        calificacion.setReporte(reporte);
        calificacion.setVecino(vecino);

        Calificacion calificacionGuardada = crudCalificacionEntity.save(calificacion);
        return mapper.toDto(calificacionGuardada);
    }

    @Override
    public List<CalificacionDto> obtenerTodo() {
        return this.mapper.toDto(this.crudCalificacionEntity.findAll());
    }
}