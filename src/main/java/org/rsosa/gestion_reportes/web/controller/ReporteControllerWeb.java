package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.*;
import org.rsosa.gestion_reportes.dominio.service.ReporteService;
import org.rsosa.gestion_reportes.dominio.service.TipoReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component("reporteControllerWeb")
@Scope("view")
@Data
public class ReporteControllerWeb implements Serializable {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private TipoReporteService tipoReporteService;

    private List<ReporteDto> misReportes;
    private ReporteDto reporteDetalle;

    // Para crear reporte
    private String descripcion;
    private String zonaSeleccionada;
    private Long tipoReporteSeleccionado;

    private List<String> zonasDisponibles;
    private List<TipoReporteDto> tiposReporte;

    // Para ver detalle
    private Long reporteId;

    @PostConstruct
    public void init() {
        cargarZonas();
        cargarTiposReporte();
    }

    private void cargarZonas() {
        this.zonasDisponibles = Arrays.asList(
                "Zona 1", "Zona 2", "Zona 3", "Zona 4", "Zona 5",
                "Zona 6", "Zona 7", "Zona 8", "Zona 9", "Zona 10",
                "Zona 11", "Zona 12", "Zona 13", "Zona 14", "Zona 15",
                "Zona 16", "Zona 17", "Zona 18", "Zona 19", "Zona 21"
        );
    }

    private void cargarTiposReporte() {
        this.tiposReporte = tipoReporteService.obtenerTodo();
    }

    public void cargarMisReportes() {
        VecinoDto vecino = getVecinoLogueado();
        if (vecino != null) {
            this.misReportes = reporteService.obtenerReportesPorVecino(vecino.name_neighbor());
        }
    }

    public String crearReporte() {
        VecinoDto vecino = getVecinoLogueado();
        if (vecino == null) {
            return "/vecino/login?faces-redirect=true";
        }

        try {
            ReporteDto dto = new ReporteDto(
                    null,
                    descripcion,
                    zonaSeleccionada,
                    null,
                    new VecinoDto(vecino.neighbor_id(), null, null, null),
                    null,
                    new TipoReporteDto(tipoReporteSeleccionado, null)
            );

            reporteService.guardarReporte(dto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reporte creado exitosamente");
            return "/vecino/dashboard?faces-redirect=true";
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            return null;
        }
    }

    public void cargarDetalle() {
        this.reporteDetalle = reporteService.obtenerReportePorCodigo(reporteId);
    }

    public String getSeverity(String estado) {
        if (estado == null) return "secondary";
        return switch (estado.toUpperCase()) {
            case "PENDIENTE" -> "warning";
            case "ASIGNADO" -> "info";
            case "RESUELTO" -> "success";
            default -> "secondary";
        };
    }

    public String verDetalle(Long id) {
        return "/vecino/reporteDetalle?faces-redirect=true&id=" + id;
    }

    private VecinoDto getVecinoLogueado() {
        return (VecinoDto) FacesContext.getCurrentInstance()
                .getExternalContext()
                .getSessionMap()
                .get("vecinoLogueado");
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}