package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.MunicipalidadDto;
import org.rsosa.gestion_reportes.dominio.exception.MunicipalidadYaExisteException;
import org.rsosa.gestion_reportes.dominio.repository.MunicipalidadRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudMunicipalidadEntity;
import org.rsosa.gestion_reportes.persistence.entity.Municipalidad;
import org.rsosa.gestion_reportes.web.mapper.MunicipalidadMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MunicipalidadEntityRepository implements MunicipalidadRepository {
    private final CrudMunicipalidadEntity crudMunicipalidadEntity;
    private final MunicipalidadMapper municipalidadMapper;

    public MunicipalidadEntityRepository(CrudMunicipalidadEntity crudMunicipalidadEntity, MunicipalidadMapper municipalidadMapper) {
        this.crudMunicipalidadEntity = crudMunicipalidadEntity;
        this.municipalidadMapper = municipalidadMapper;
    }


    @Override
    public List<MunicipalidadDto> obtenerTodo() {
        return this.municipalidadMapper.toDto((List<Municipalidad>) this.crudMunicipalidadEntity.findAll());
    }

    @Override
    public MunicipalidadDto obtenerMunicipalidadPorId(Long id) {
        return crudMunicipalidadEntity.findById(id)
                .map(municipalidadMapper::toDto)
                .orElse(null);
    }

    @Override
    public MunicipalidadDto guardarMunicipalidad(MunicipalidadDto municipalidadDto) {
        if (this.crudMunicipalidadEntity.findByZona(municipalidadDto.zone()).isPresent()) {
            throw new MunicipalidadYaExisteException(municipalidadDto.zone());
        }

        Municipalidad municipalidad = this.municipalidadMapper.toEntity(municipalidadDto);
        municipalidad = this.crudMunicipalidadEntity.save(municipalidad);
        return this.municipalidadMapper.toDto(municipalidad);
    }

    @Override
    public MunicipalidadDto actualizarMunicipalidad(Long id, MunicipalidadDto municipalidadDto) {
        Optional<Municipalidad> municipalidadExistente = crudMunicipalidadEntity.findById(id);

        if (municipalidadExistente.isEmpty()) {
            return null;
        }

        Municipalidad entidad = municipalidadExistente.get();
        municipalidadMapper.updateEntityFromDto(municipalidadDto, entidad);

        entidad.setCodigoMunicipalidad(id);

        Municipalidad actualizada = crudMunicipalidadEntity.save(entidad);
        return municipalidadMapper.toDto(actualizada);
    }

    @Override
    public void eliminarMunicipalidad(Long id) {
        crudMunicipalidadEntity.deleteById(id);
    }
}