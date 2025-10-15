package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.TrabajadorMunicipalDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.dominio.repository.TrabajadorMunicipalidadRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudMunicipalidadEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudPersonalEntity;
import org.rsosa.gestion_reportes.persistence.crud.CrudTrabajadorMunicipalEntity;
import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;
import org.rsosa.gestion_reportes.persistence.entity.Personal;
import org.rsosa.gestion_reportes.persistence.entity.TrabajadorMunicipalidad;
import org.rsosa.gestion_reportes.web.mapper.TrabajadorMunicipalidadMapper;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;

@Repository
public class TrabajadorMunicipalidadEntityRepository implements TrabajadorMunicipalidadRepository {
    private final CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalEntity;
    private final CrudPersonalEntity crudPersonalEntity;
    private final CrudMunicipalidadEntity crudMunicipalidadEntity;
    private final TrabajadorMunicipalidadMapper trabajadorMunicipalidadMapper;

    public TrabajadorMunicipalidadEntityRepository(
            CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalEntity,
            CrudPersonalEntity crudPersonalEntity,
            CrudMunicipalidadEntity crudMunicipalidadEntity,
            TrabajadorMunicipalidadMapper trabajadorMunicipalidadMapper
    ) {
        this.crudTrabajadorMunicipalEntity = crudTrabajadorMunicipalEntity;
        this.crudPersonalEntity = crudPersonalEntity;
        this.crudMunicipalidadEntity = crudMunicipalidadEntity;
        this.trabajadorMunicipalidadMapper = trabajadorMunicipalidadMapper;
    }

    @Override
    public List<TrabajadorMunicipalDto> obtenerTodo() {
        return this.trabajadorMunicipalidadMapper.toDto(this.crudTrabajadorMunicipalEntity.findAll());
    }

    @Override
    public TrabajadorMunicipalDto obtenerPorCodigo(Long id) {
        TrabajadorMunicipalidad trabajadorMunicipalidad = this.crudTrabajadorMunicipalEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Trabajador Municipal con código " + id + " no existe."));
        return this.trabajadorMunicipalidadMapper.toDto(trabajadorMunicipalidad);
    }

    @Override
    public TrabajadorMunicipalDto guardarTrabajadorMunicipalidad(TrabajadorMunicipalDto trabajadorMunicipalDto) {
        // Validar que Personal existe
        Personal personal = this.crudPersonalEntity.findById(trabajadorMunicipalDto.staff().personal_id())
                .orElseThrow(() -> new CatalogoInvalidoException("Personal con código " + trabajadorMunicipalDto.staff().personal_id() + " no existe."));

        // Validar que Municipalidad existe
        Municipalidad municipalidad = this.crudMunicipalidadEntity.findById(trabajadorMunicipalDto.municipality().municipality_id())
                .orElseThrow(() -> new CatalogoInvalidoException("Municipalidad con código " + trabajadorMunicipalDto.municipality().municipality_id() + " no existe."));

        TrabajadorMunicipalidad trabajadorMunicipalidad = this.trabajadorMunicipalidadMapper.toEntity(trabajadorMunicipalDto);
        trabajadorMunicipalidad.setPersonal(personal);
        trabajadorMunicipalidad.setMunicipalidad(municipalidad);

        trabajadorMunicipalidad = this.crudTrabajadorMunicipalEntity.save(trabajadorMunicipalidad);
        return this.trabajadorMunicipalidadMapper.toDto(trabajadorMunicipalidad);
    }

    @Override
    public TrabajadorMunicipalDto actualizarTrabajadorMunicipalidad(Long id, TrabajadorMunicipalDto trabajadorMunicipalDto) {
        TrabajadorMunicipalidad trabajadorMunicipalidad = this.crudTrabajadorMunicipalEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Trabajador Municipal con código " + id + " no existe."));

        // Validar y actualizar Personal si se proporciona
        if (trabajadorMunicipalDto.staff() != null && trabajadorMunicipalDto.staff().personal_id() != null) {
            Personal personal = this.crudPersonalEntity.findById(trabajadorMunicipalDto.staff().personal_id())
                    .orElseThrow(() -> new CatalogoInvalidoException("Personal con código " + trabajadorMunicipalDto.staff().personal_id() + " no existe."));
            trabajadorMunicipalidad.setPersonal(personal);
        }

        // Validar y actualizar Municipalidad si se proporciona
        if (trabajadorMunicipalDto.municipality() != null && trabajadorMunicipalDto.municipality().municipality_id() != null) {
            Municipalidad municipalidad = this.crudMunicipalidadEntity.findById(trabajadorMunicipalDto.municipality().municipality_id())
                    .orElseThrow(() -> new CatalogoInvalidoException("Municipalidad con código " + trabajadorMunicipalDto.municipality().municipality_id() + " no existe."));
            trabajadorMunicipalidad.setMunicipalidad(municipalidad);
        }

        trabajadorMunicipalidad = this.crudTrabajadorMunicipalEntity.save(trabajadorMunicipalidad);
        return this.trabajadorMunicipalidadMapper.toDto(trabajadorMunicipalidad);
    }

    @Override
    public void eliminarTrabajadorMunicipalidad(Long id) {
        TrabajadorMunicipalidad trabajadorMunicipalidad = this.crudTrabajadorMunicipalEntity.findById(id)
                .orElseThrow(() -> new CatalogoInvalidoException("Trabajador Municipal con código " + id + " no existe."));
        this.crudTrabajadorMunicipalEntity.deleteById(id);
    }

    @Override
    public List<TrabajadorMunicipalDto> obtenerPorPersonal(Long codigoPersonal) {
        return this.trabajadorMunicipalidadMapper.toDto(this.crudTrabajadorMunicipalEntity.findByPersonal_CodigoPersonal(codigoPersonal));
    }

    @Override
    public List<TrabajadorMunicipalDto> obtenerPorMunicipalidad(Long codigoMunicipalidad) {
        return this.trabajadorMunicipalidadMapper.toDto(this.crudTrabajadorMunicipalEntity.findByMunicipalidad_CodigoMunicipalidad(codigoMunicipalidad));
    }

    @Override
    public List<TrabajadorMunicipalDto> obtenerPorZona(String zona) {
        return this.trabajadorMunicipalidadMapper.toDto(this.crudTrabajadorMunicipalEntity.findByMunicipalidad_Zona(zona));
    }
}