package org.rsosa.gestion_reportes.persistence;

import org.rsosa.gestion_reportes.dominio.dto.AspiranteDto;
import org.rsosa.gestion_reportes.dominio.repository.AspiranteRepository;
import org.rsosa.gestion_reportes.persistence.crud.CrudAspiranteEntity;
import org.rsosa.gestion_reportes.web.mapper.AspiranteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class AspiranteEntityRepository implements AspiranteRepository {
    private final CrudAspiranteEntity crudAspiranteEntity;
    private final AspiranteMapper aspiranteMapper;

    public AspiranteEntityRepository(CrudAspiranteEntity crudAspiranteEntity, AspiranteMapper aspiranteMapper) {
        this.crudAspiranteEntity = crudAspiranteEntity;
        this.aspiranteMapper = aspiranteMapper;
    }

    @Override
    public List<AspiranteDto> obtenerTodo() {
        List<org.rsosa.gestion_reportes.persistence.entity.Aspirante> aspirantes =
                StreamSupport.stream(this.crudAspiranteEntity.findAll().spliterator(), false)
                        .collect(Collectors.toList());
        return this.aspiranteMapper.toDto(aspirantes);
    }

    @Override
    public AspiranteDto obtenerAspirantePorId(Long id) {
        return this.aspiranteMapper.toDto(this.crudAspiranteEntity.findById(id).orElse(null));
    }

    @Override
    public AspiranteDto guardarAspirante(AspiranteDto aspiranteDto) {
        var aspirante = this.aspiranteMapper.toEntity(aspiranteDto);
        aspirante = this.crudAspiranteEntity.save(aspirante);
        return this.aspiranteMapper.toDto(aspirante);
    }

    @Override
    public AspiranteDto actualizarAspirante(Long id, AspiranteDto aspiranteDto) {
        var aspirante = this.crudAspiranteEntity.findById(id).orElse(null);
        if (aspirante == null) {
            return null;
        } else {
            this.aspiranteMapper.updateEntityFromDto(aspiranteDto, aspirante);
            aspirante = this.crudAspiranteEntity.save(aspirante);
            return this.aspiranteMapper.toDto(aspirante);
        }
    }

    @Override
    public void eliminarAspirante(Long id) {
        this.crudAspiranteEntity.deleteById(id);
    }
}
