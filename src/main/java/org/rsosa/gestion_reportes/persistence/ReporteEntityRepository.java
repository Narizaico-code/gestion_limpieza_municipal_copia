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
public class ReporteEntityRepository implements ReporteRepository{
    private final CrudReporteEntity crudReporteEntity;
    private final CrudPersonalEntity crudPersonalEntity;
    private final CrudContratacionEntity crudContratacionEntity;
    private final CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalEntity;
    private final ReporteMapper reporteMapper;
    private final CrudEstadoEntity crudEstadoEntity;
    private final CrudVecinoEntity crudVecinoEntity;
    private final CrudTipoReporteEntity crudTipoReporteEntity;
    private final CrudMunicipalidadEntity crudMunicipalidadEntity; // Necesario para buscar la zona
    private final CrudAdministradorEntity crudAdministradorEntity; // Necesario para buscar el Administrador
    private final CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalidadEntity; // Para buscar el Administrador en la zona

    public ReporteEntityRepository(
            CrudReporteEntity crudReporteEntity,
            CrudPersonalEntity crudPersonalEntity,
            CrudContratacionEntity crudContratacionEntity,
            CrudTrabajadorMunicipalEntity crudTrabajadorMunicipalEntity,
            ReporteMapper reporteMapper,
            CrudEstadoEntity crudEstadoEntity,
            CrudVecinoEntity crudVecinoEntity,
            CrudTipoReporteEntity crudTipoReporteEntity,
            CrudMunicipalidadEntity crudMunicipalidadEntity, // Nueva inyección
            CrudAdministradorEntity crudAdministradorEntity // Nueva inyección
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
        this.crudTrabajadorMunicipalidadEntity = crudTrabajadorMunicipalEntity;
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

        Reporte reporte = this.reporteMapper.toEntity(reporteDto);

        if (reporteDto.neighbor() != null && reporteDto.neighbor().neighbor_id() != null) {
            Vecino vecinoCompleto = this.crudVecinoEntity.findById(reporteDto.neighbor().neighbor_id())
                    .orElseThrow(() -> new VecinoNoExisteException("Vecino con ID " + reporteDto.neighbor().neighbor_id() + " no encontrado."));
            reporte.setVecino(vecinoCompleto);
        } else {
            throw new RuntimeException("El ID del Vecino es obligatorio para crear un reporte.");
        }

        if (reporteDto.report_type() != null && reporteDto.report_type().report_type_id() != null) {
            TipoReporte tipoReporteCompleto = this.crudTipoReporteEntity.findById(reporteDto.report_type().report_type_id())
                    .orElseThrow(() -> new CatalogoInvalidoException("Tipo de Reporte con ID " + reporteDto.report_type().report_type_id() + " no encontrado."));
            reporte.setTipoReporte(tipoReporteCompleto);
        } else {
            throw new RuntimeException("El ID del Tipo de Reporte es obligatorio para crear un reporte.");
        }

        Estado estadoPendiente = this.crudEstadoEntity.findByNombre("PENDIENTE")
                .orElseThrow(() -> new CatalogoInvalidoException("El estado inicial 'PENDIENTE' no existe en el catálogo."));
        reporte.setEstado(estadoPendiente);

        String zona = reporte.getZona();
        Municipalidad municipalidad = crudMunicipalidadEntity.findByZona(zona)
                .orElseThrow(() -> new ZonaInvalidaException("No se encontró Municipalidad asignada a la zona: " + zona));

        Optional<TrabajadorMunicipalidad> adminTrabajadorOpt = crudTrabajadorMunicipalidadEntity
                .findByMunicipalidad_CodigoMunicipalidad(municipalidad.getCodigoMunicipalidad())
                .stream()
                .filter(tm -> tm.getPersonal() != null && crudAdministradorEntity.existsById(tm.getPersonal().getCodigoPersonal()))
                .findFirst();

        if (adminTrabajadorOpt.isPresent()) {
            Long codigoAdmin = adminTrabajadorOpt.get().getPersonal().getCodigoPersonal();
            Administrador administradorAsignado = crudAdministradorEntity.findById(codigoAdmin)
                    .orElse(null);

            reporte.setAdministradorAsignado(administradorAsignado);
        } else {
            System.out.println("ADVERTENCIA: No se encontró Administrador asignado a la Municipalidad de la zona " + reporte.getZona() + ". El campo quedará NULL.");
        }

        reporte = this.crudReporteEntity.save(reporte);
        return this.reporteMapper.toDto(reporte);
    }

    @Override
    @Transactional
    public ReporteDto actualizarReporte(Long id, ModReporteDto modReporteDto) {
        Reporte reporte = this.crudReporteEntity.findById(id)
                .orElseThrow(() -> new ReporteNoExisteException(id));

        if (modReporteDto.staff() != null && modReporteDto.staff().personal_id() != null) {
            Long codigoNuevoPersonal = modReporteDto.staff().personal_id();

            Personal nuevoPersonal = crudPersonalEntity.findById(codigoNuevoPersonal)
                    .orElseThrow(() -> new PersonalNoExisteException(codigoNuevoPersonal));

            TrabajadorMunicipalidad relacionTrabajador = crudTrabajadorMunicipalEntity.findByPersonal_CodigoPersonal(codigoNuevoPersonal)
                    .orElseThrow(() -> new TrabajadorSinZonaException(codigoNuevoPersonal));

            String zonaPersonal = relacionTrabajador.getMunicipalidad().getZona();

            if (!zonaPersonal.equalsIgnoreCase(reporte.getZona())) {
                throw new ZonaNoCoincideException("El personal ('" + zonaPersonal + "') no pertenece a la zona del reporte ('" + reporte.getZona() + "').");
            }

            if (!nuevoPersonal.getEstado().equalsIgnoreCase("Libre")) {
                Iterable<Contratacion> vacanteActivaOpt = crudContratacionEntity.findByMunicipalidad_ZonaAndVacantesDisponiblesGreaterThan(reporte.getZona(), 0);

                if (vacanteActivaOpt == null) {
                    Municipalidad municipalidadZona = crudMunicipalidadEntity.findByZona(reporte.getZona())
                            .orElseThrow(() -> new CatalogoInvalidoException("Municipalidad no encontrada para la zona: " + reporte.getZona()));

                    Contratacion nuevaContratacion = new Contratacion();
                    nuevaContratacion.setSalario("Pendiente");
                    nuevaContratacion.setVacante("Técnico de Campo");
                    nuevaContratacion.setMunicipalidad(municipalidadZona);
                    nuevaContratacion.setVacantesDisponibles(1); // Configuramos 1 vacante

                    this.crudContratacionEntity.save(nuevaContratacion); // Guardar la nueva entidad de Contratación

                    throw new PersonalOcupadoException("ASIGNACIÓN FALLIDA: Personal ocupado. SE HA CREADO UNA NUEVA VACANTE EN LA ZONA. Avise a RRHH.");
                } else {
                    throw new PersonalOcupadoException("ASIGNACIÓN FALLIDA: El personal seleccionado ya está 'Asignado'. Existe una vacante activa en la zona, espere a la contratación.");
                }
            }

            nuevoPersonal.setEstado("Asignado");
            this.crudPersonalEntity.save(nuevoPersonal);

            reporte.setPersonalAsignado(nuevoPersonal);
            Estado estadoReporteAsignado = this.crudEstadoEntity.findByNombre("ASIGNADO")
                    .orElseThrow(() -> new CatalogoInvalidoException("El estado 'ASIGNADO' no existe en el catálogo."));
            reporte.setEstado(estadoReporteAsignado);
        }

        if (modReporteDto.state() != null && modReporteDto.state().state_id() != null) {
            Estado nuevoEstado = this.crudEstadoEntity.findById(modReporteDto.state().state_id())
                    .orElseThrow(() -> new EstadoInvalidoException("El código de Estado de Reporte " + modReporteDto.state().state_id() + " es inválido. Verifique el catálogo."));

            reporte.setEstado(nuevoEstado);
        }

        Reporte reporteActualizado = this.crudReporteEntity.save(reporte);
        return this.reporteMapper.toDto(reporteActualizado);
    }

    @Override
    public void eliminarReporte(Long id) {
        Reporte reporte = this.crudReporteEntity.findById(id).orElse(null);
        if (reporte == null){
            throw new ReporteNoExisteException(id);
        }else {
            this.crudReporteEntity.deleteById(id);
        }
    }
}