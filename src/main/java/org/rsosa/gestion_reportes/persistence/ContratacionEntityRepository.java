package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.ContratacionDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.dominio.repository.ContratacionRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudContratacionEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudMunicipalidadEntity;
import org.rsosa.gestion_reportes.persistence.entity.Contratacion;
import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;
import org.rsosa.gestion_reportes.web.mapper.ContratacionMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContratacionEntityRepository implements ContratacionRepository {
    private final CrudContratacionEntity crudContratacionEntity;
    private final CrudMunicipalidadEntity crudMunicipalidadEntity;
    private final ContratacionMapper contratacionMapper;

    public ContratacionEntityRepository(CrudContratacionEntity crudContratacionEntity, CrudMunicipalidadEntity crudMunicipalidadEntity, ContratacionMapper contratacionMapper) {
        this.crudContratacionEntity = crudContratacionEntity;
        this.crudMunicipalidadEntity = crudMunicipalidadEntity;
        this.contratacionMapper = contratacionMapper;
    }

    @Override
    public List<ContratacionDto> obtenerTodo() {
        return this.contratacionMapper.toDto(this.crudContratacionEntity.findAll());
    }

    @Override
    public ContratacionDto obtenerPorCodigo(Long id) {
        Contratacion contratacion = this.crudContratacionEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Contratación con código " + id + " no existe."));
        return this.contratacionMapper.toDto(contratacion);
    }

    @Override
    public ContratacionDto guardarContratacion(ContratacionDto contratacionDto) {
        Long idMunicipalidad = contratacionDto.municipality().municipality_id();
        Municipalidad municipalidad = this.crudMunicipalidadEntity.findById(idMunicipalidad).orElseThrow(() -> new RuntimeException("La municipalidad con id " + idMunicipalidad + " no sirve"));
        Contratacion contratacion = this.contratacionMapper.toEntity(contratacionDto);
        contratacion.setMunicipalidad(municipalidad);
        contratacion = this.crudContratacionEntity.save(contratacion);
        return this.contratacionMapper.toDto(contratacion);
    }

    @Override
    public ContratacionDto actualizarContratacion(Long id, ContratacionDto contratacionDto) {
        Contratacion contratacion = this.crudContratacionEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Contratación con código " + id + " no existe."));
        this.contratacionMapper.updateEntityFromDto(contratacionDto, contratacion);
        Long idMunicipalidad = contratacionDto.municipality().municipality_id();
        Municipalidad municipalidad = this.crudMunicipalidadEntity.findById(idMunicipalidad).orElseThrow(() -> new RuntimeException("La municipalidad con id " + idMunicipalidad + " no sirve"));
        contratacion.setMunicipalidad(municipalidad);
        contratacion = this.crudContratacionEntity.save(contratacion);
        return this.contratacionMapper.toDto(contratacion);
    }

    @Override
    public void eliminarContratacion(Long id) {
        Contratacion contratacion = this.crudContratacionEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Contratación con código " + id + " no existe."));
        this.crudContratacionEntity.deleteById(id);
    }

    @Override
    public List<ContratacionDto> obtenerPorMunicipalidad(Long codigoMunicipalidad) {
        return this.contratacionMapper.toDto(this.crudContratacionEntity.findByMunicipalidad_CodigoMunicipalidad(codigoMunicipalidad));
    }
}