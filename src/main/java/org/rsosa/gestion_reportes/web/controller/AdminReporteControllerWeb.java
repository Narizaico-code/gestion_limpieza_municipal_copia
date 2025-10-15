
// ============================================
// 4. AdminReporteControllerWeb.java
// ============================================
package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.*;
import org.rsosa.gestion_reportes.dominio.service.ReporteService;
import org.rsosa.gestion_reportes.persistence.TrabajadorMunicipalidadEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Component("adminReporteControllerWeb")
@Scope("view")
@Data
public class AdminReporteControllerWeb implements Serializable {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private TrabajadorMunicipalidadEntityRepository trabajadorRepository;

    private List<ReporteDto> reportes;
    private ReporteDto reporteSeleccionado;
    private ReporteDto reporteDetalle;

    private String filtroEstado;
    private String filtroVecino;
    private Long personalSeleccionado;
    private List<PersonalDto> personalDisponible;

    private Long reporteId;

    @PostConstruct
    public void init() {
        cargarReportes();
    }

    public void cargarReportes() {
        this.reportes = reporteService.obtenerTodo();
    }

    public void aplicarFiltros() {
        if (filtroEstado != null && !filtroEstado.isEmpty()) {
            this.reportes = reporteService.obtenerReportesPorEstado(filtroEstado);
        } else if (filtroVecino != null && !filtroVecino.isEmpty()) {
            this.reportes = reporteService.obtenerReportesPorVecino(filtroVecino);
        } else {
            cargarReportes();
        }
    }

    public void limpiarFiltros() {
        this.filtroEstado = null;
        this.filtroVecino = null;
        cargarReportes();
    }

    public void abrirDialogoAsignar(ReporteDto reporte) {
        this.reporteSeleccionado = reporte;

        List<TrabajadorMunicipalDto> trabajadores = trabajadorRepository.obtenerPorZona(reporte.zone());

        this.personalDisponible = trabajadores.stream()
                .map(TrabajadorMunicipalDto::staff)
                .filter(p -> "LIBRE".equalsIgnoreCase(p.state().name()))
                .collect(Collectors.toList());
    }

    public void asignarPersonal() {
        try {
            ModReporteDto modDto = new ModReporteDto(
                    null,
                    new PersonalDto(personalSeleccionado, null, null, null, null, null)
            );

            reporteService.actualizarReporte(reporteSeleccionado.report_id(), modDto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Personal asignado correctamente");
            aplicarFiltros();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public void cargarDetalle() {
        this.reporteDetalle = reporteService.obtenerReportePorCodigo(reporteId);
    }

    public String verDetalle(Long id) {
        return "/admin/reporteDetalle?faces-redirect=true&id=" + id;
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}

