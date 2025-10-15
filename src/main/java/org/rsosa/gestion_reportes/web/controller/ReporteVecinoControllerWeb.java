package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.*;
import org.rsosa.gestion_reportes.dominio.service.CalificacionService;
import org.rsosa.gestion_reportes.dominio.service.ReporteService;
import org.rsosa.gestion_reportes.dominio.service.TipoReporteService;
import org.rsosa.gestion_reportes.persistence.ReporteEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component("reporteVecinoControllerWeb")
@Scope("view")
@Data
public class ReporteVecinoControllerWeb implements Serializable {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private TipoReporteService tipoReporteService;

    @Autowired
    private CalificacionService calificacionService;

    private final ReporteEntityRepository reporteEntityRepository;

    private VecinoDto vecinoLogueado;

    private List<ReporteDto> misReportes;
    private List<ReporteDto> reportesZona;
    private List<TipoReporteDto> tiposReporte;
    private List<String> zonasDisponibles;

    private String descripcionNuevo;
    private String zonaNuevo;
    private Long tipoReporteNuevo;

    private ReporteDto reporteCalificar;
    private Integer valorCalificacion;

    @PostConstruct
    public void init() {
        cargarVecinoLogueado();
        cargarZonas();
        cargarTiposReporte();
        cargarMisReportes();
    }

    private void cargarVecinoLogueado() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null) {
            this.vecinoLogueado = (VecinoDto) session.getAttribute("vecinoLogueado");
        }
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
        if (vecinoLogueado != null) {
            this.misReportes = reporteEntityRepository.obtenerReportesPorVecino(vecinoLogueado.nameNeighbor());
        }
    }

    public void cargarReportesZona() {
        // Obtener la zona del primer reporte del vecino
        if (misReportes != null && !misReportes.isEmpty()) {
            String zona = misReportes.get(0).zone();
            this.reportesZona = reporteService.obtenerTodo().stream()
                    .filter(r -> r.zone().equals(zona))
                    .collect(Collectors.toList());
        }
    }

    public void abrirModalCrear() {
        this.descripcionNuevo = null;
        this.zonaNuevo = null;
        this.tipoReporteNuevo = null;
    }

    public void crearReporte() {
        if (vecinoLogueado == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "Debe iniciar sesión");
            return;
        }

        try {
            ReporteDto dto = new ReporteDto(
                    null,
                    descripcionNuevo,
                    zonaNuevo,
                    null,
                    vecinoLogueado,
                    null,
                    new TipoReporteDto(tipoReporteNuevo, null)
            );

            reporteService.guardarReporte(dto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Reporte creado exitosamente");
            cargarMisReportes();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public void abrirModalCalificar(ReporteDto reporte) {
        System.out.println("Abriendo modal para reporte: " + (reporte != null ? reporte.reportId() : "null"));
        this.reporteCalificar = reporte;
        this.valorCalificacion = null;
    }

    public void guardarCalificacion() {
        if (reporteCalificar == null) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "No hay reporte seleccionado");
            return;
        }

        if (valorCalificacion == null || valorCalificacion < 1 || valorCalificacion > 5) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", "La calificación debe ser entre 1 y 5");
            return;
        }

        try {
            CalificacionDto dto = new CalificacionDto(
                    null,
                    valorCalificacion,
                    reporteCalificar,
                    vecinoLogueado
            );

            calificacionService.guardarCalificacion(dto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Calificación guardada exitosamente");
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean puedeCalificar(ReporteDto reporte) {
        // Removemos la validación por ahora para debug
        return true;
        // return reporte != null && reporte.state() != null && "RESUELTO".equals(reporte.state().name());
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

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}