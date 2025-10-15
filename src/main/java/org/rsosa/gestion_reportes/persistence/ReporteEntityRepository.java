package org.rsosa.gestion_reportes.persistence;

import jakarta.transaction.Transactional;
import org.rsosa.gestion_reportes.dominio.dto.ModReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;
import org.rsosa.gestion_reportes.dominio.exception.*;
import org.rsosa.gestion_reportes.dominio.repository.ReporteRepository;
import org.rsosa.gestion_reportes.persistence.crud.*;
import org.rsosa.gestion_reportes.persistence.entity.*;
import org.rsosa.gestion_reportes.web.mapper.ReporteMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ReporteEntityRepository implements ReporteRepository {
    private final CrudReporteEntity crudReporteEntity;
    private final CrudPersonalEntity crudPersonalEntity;
    private final CrudContratacionEntity crudContratacionEntity;
    private final CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalEntity;
    private final ReporteMapper reporteMapper;
    private final CrudEstadoEntity crudEstadoEntity;
    private final CrudVecinoEntity crudVecinoEntity;
    private final CrudTipoReporteEntity crudTipoReporteEntity;
    private final CrudMunicipalidadEntity crudMunicipalidadEntity;
    private final CrudAdministradorEntity crudAdministradorEntity;
    private final CrudAdministradorMunicipalidadEntity crudAdminMunicipalidadEntity;

    public ReporteEntityRepository(
            CrudReporteEntity crudReporteEntity,
            CrudPersonalEntity crudPersonalEntity,
            CrudContratacionEntity crudContratacionEntity,
            CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalEntity,
            ReporteMapper reporteMapper,
            CrudEstadoEntity crudEstadoEntity,
            CrudVecinoEntity crudVecinoEntity,
            CrudTipoReporteEntity crudTipoReporteEntity,
            CrudMunicipalidadEntity crudMunicipalidadEntity,
            CrudAdministradorEntity crudAdministradorEntity,
            CrudAdministradorMunicipalidadEntity crudAdminMunicipalidadEntity
    ) {
        this.crudReporteEntity = crudReporteEntity;
        this.crudPersonalEntity = crudPersonalEntity;
        this.crudContratacionEntity = crudContratacionEntity;
        this.crudTrabajadorMunicipalEntity = crudTrabajadorMunicipalEntity;
        this.reporteMapper = reporteMapper;
        this.crudEstadoEntity = crudEstadoEntity;
        this.crudVecinoEntity = crudVecinoEntity;
        this.crudTipoReporteEntity = crudTipoReporteEntity;
        this.crudMunicipalidadEntity = crudMunicipalidadEntity;
        this.crudAdministradorEntity = crudAdministradorEntity;
        this.crudAdminMunicipalidadEntity = crudAdminMunicipalidadEntity;
    }

    @Override
    public List<ReporteDto> obtenerTodo() {
        return this.reporteMapper.toDto(this.crudReporteEntity.findAll());
    }

    @Override
    public List<ReporteDto> obtenerReportesPorVecino(String vecino) {
        return this.reporteMapper.toDto(this.crudReporteEntity.findAllByVecino_Nombre(vecino));
    }

    @Override
    public List<ReporteDto> obtenerReportesPorEstado(String estado) {
        return this.reporteMapper.toDto(this.crudReporteEntity.findAllByEstado_Nombre(estado));
    }

    @Override
    public List<ReporteDto> obtenerReportesPorTipoReporte(String tipoReporte) {
        return this.reporteMapper.toDto(this.crudReporteEntity.findAllByTipoReporte_Nombre(tipoReporte));
    }

    @Override
    public List<ReporteDto> obtenerReportesPorPersonal(String persona) {
        return this.reporteMapper.toDto(this.crudReporteEntity.findAllByPersonalAsignado_Nombre(persona));
    }

    @Override
    public ReporteDto obtenerReportePorCodigo(Long id) {
        Reporte reporte = this.crudReporteEntity.findById(id)
                .orElseThrow(() -> new ReporteNoExisteException(id));
        return this.reporteMapper.toDto(reporte);
    }

    @Override
    @Transactional
    public ReporteDto guardarReporte(ReporteDto reporteDto) {
        if (reporteDto.zone() == null || reporteDto.zone().isBlank()) {
            throw new ZonaInvalidaException("La zona del reporte es obligatoria y no puede estar vacía.");
        }

        Reporte reporte = this.reporteMapper.toEntity(reporteDto);

        if (reporteDto.neighbor() != null && reporteDto.neighbor().neighborId() != null) {
            Vecino vecinoCompleto = this.crudVecinoEntity.findById(reporteDto.neighbor().neighborId())
                    .orElseThrow(() -> new VecinoNoExisteException("Vecino con ID " + reporteDto.neighbor().neighborId() + " no encontrado."));
            reporte.setVecino(vecinoCompleto);
        } else {
            throw new RuntimeException("El ID del Vecino es obligatorio para crear un reporte.");
        }

        if (reporteDto.reportType() != null && reporteDto.reportType().reportTypeId() != null) {
            TipoReporte tipoReporteCompleto = this.crudTipoReporteEntity.findById(reporteDto.reportType().reportTypeId())
                    .orElseThrow(() -> new CatalogoInvalidoException("Tipo de Reporte con ID " + reporteDto.reportType().reportTypeId() + " no encontrado."));
            reporte.setTipoReporte(tipoReporteCompleto);
        } else {
            throw new RuntimeException("El ID del Tipo de Reporte es obligatorio para crear un reporte.");
        }

        Estado estadoPendiente = this.crudEstadoEntity.findByNombre("PENDIENTE")
                .orElseThrow(() -> new CatalogoInvalidoException("El estado inicial 'PENDIENTE' no existe en el catálogo."));
        reporte.setEstado(estadoPendiente);

        String zona = reporte.getZona();
        Municipalidad municipalidad = this.crudMunicipalidadEntity.findByZona(zona)
                .orElseThrow(() -> new ZonaInvalidaException("No se encontró Municipalidad asignada a la zona: " + zona));

        Optional<AdministradorMunicipalidad> adminMunicipalidadOpt = this.crudAdminMunicipalidadEntity
                .findByMunicipalidad_CodigoMunicipalidad(municipalidad.getCodigoMunicipalidad());

        if (adminMunicipalidadOpt.isPresent()) {
            Administrador administrador = adminMunicipalidadOpt.get().getAdministrador();
            reporte.setAdministradorAsignado(administrador);
        }

        reporte = this.crudReporteEntity.save(reporte);
        return this.reporteMapper.toDto(reporte);
    }

    @Override
    @Transactional
    public ReporteDto actualizarReporte(Long id, ModReporteDto modReporteDto) {
        Reporte reporte = this.crudReporteEntity.findById(id)
                .orElseThrow(() -> new ReporteNoExisteException(id));

        if (modReporteDto.staff() != null && modReporteDto.staff().personalId() != null) {
            Long codigoNuevoPersonal = modReporteDto.staff().personalId();

            Personal nuevoPersonal = this.crudPersonalEntity.findById(codigoNuevoPersonal)
                    .orElseThrow(() -> new PersonalNoExisteException(codigoNuevoPersonal));

            // DEBUG: Obtener todos los TrabajadorMunicipalidad del Personal
            List<TrabajadorMunicipalidad> trabajadoresDelPersonal = this.crudTrabajadorMunicipalEntity
                    .findByPersonal_CodigoPersonal(codigoNuevoPersonal);

            System.out.println("====== DEBUG ======");
            System.out.println("Personal ID: " + codigoNuevoPersonal);
            System.out.println("Zona del Reporte: '" + reporte.getZona() + "'");
            System.out.println("Total TrabajadorMunicipalidad encontrados: " + trabajadoresDelPersonal.size());

            trabajadoresDelPersonal.forEach(tm -> {
                System.out.println("  - Zona en BD: '" + tm.getMunicipalidad().getZona() + "'");
                System.out.println("    ¿Coincide? " + tm.getMunicipalidad().getZona().equalsIgnoreCase(reporte.getZona()));
            });
            System.out.println("==================");

            // Buscar el que coincida con la zona
            TrabajadorMunicipalidad relacionTrabajador = trabajadoresDelPersonal
                    .stream()
                    .filter(tm -> tm.getMunicipalidad().getZona().equalsIgnoreCase(reporte.getZona()))
                    .findFirst()
                    .orElseThrow(() -> new TrabajadorSinZonaException(codigoNuevoPersonal));

            String zonaPersonal = relacionTrabajador.getMunicipalidad().getZona();

            if (!zonaPersonal.equalsIgnoreCase(reporte.getZona())) {
                throw new ZonaNoCoincideException("El personal ('" + zonaPersonal + "') no pertenece a la zona del reporte ('" + reporte.getZona() + "').");
            }

            if (!nuevoPersonal.getEstado().equalsIgnoreCase("Libre")) {
                Municipalidad municipalidad = this.crudMunicipalidadEntity.findByZona(reporte.getZona())
                        .orElseThrow(() -> new CatalogoInvalidoException("Municipalidad no encontrada para la zona: " + reporte.getZona()));

                Contratacion nuevaContratacion = new Contratacion();
                nuevaContratacion.setSalario(600.00);
                nuevaContratacion.setVacante("Técnico de Campo");
                nuevaContratacion.setMunicipalidad(municipalidad);
                nuevaContratacion.setVacantesDisponibles(1);

                this.crudContratacionEntity.save(nuevaContratacion);

                throw new PersonalOcupadoException("ASIGNACIÓN FALLIDA: El personal está ocupado. SE HA CREADO UNA NUEVA VACANTE AUTOMÁTICAMENTE. Avise a RRHH para contratar aspirantes.");
            }

            nuevoPersonal.setEstado("Asignado");
            this.crudPersonalEntity.save(nuevoPersonal);

            reporte.setPersonalAsignado(nuevoPersonal);

            Estado estadoAsignado = this.crudEstadoEntity.findByNombre("ASIGNADO")
                    .orElseThrow(() -> new CatalogoInvalidoException("El estado 'ASIGNADO' no existe en el catálogo."));
            reporte.setEstado(estadoAsignado);
        }

        if (modReporteDto.state() != null && modReporteDto.state().stateId() != null) {
            Estado nuevoEstado = this.crudEstadoEntity.findById(modReporteDto.state().stateId())
                    .orElseThrow(() -> new EstadoInvalidoException("El código de Estado " + modReporteDto.state().stateId() + " es inválido."));
            reporte.setEstado(nuevoEstado);
        }

        Reporte reporteActualizado = this.crudReporteEntity.save(reporte);
        return this.reporteMapper.toDto(reporteActualizado);
    }

    @Override
    public void eliminarReporte(Long id) {
        Reporte reporte = this.crudReporteEntity.findById(id)
                .orElseThrow(() -> new ReporteNoExisteException(id));
        this.crudReporteEntity.deleteById(id);
    }
}