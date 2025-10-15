package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.dominio.service.ReporteService;
import org.rsosa.gestion_reportes.dominio.service.TipoReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Component("reporteAnonimoControllerWeb")
@Scope("view")
@Data
public class ReporteAnonimoControllerWeb implements Serializable {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private TipoReporteService tipoReporteService;

    private String descripcion;
    private String zona;
    private Long tipoReporteId;

    private List<String> zonasDisponibles;
    private List<TipoReporteDto> tiposReporte;

    private String mensajeExito;

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

    public void crearReporteAnonimo() {
        try {
            ReporteDto dto = new ReporteDto(
                    null,
                    descripcion,
                    zona,
                    null,
                    null,
                    null,
                    new TipoReporteDto(tipoReporteId, null)
            );

            reporteService.guardarReporte(dto);
            this.mensajeExito = "Reporte anónimo creado exitosamente. Código: #" + System.currentTimeMillis();
            PrimeFaces.current().executeScript("PF('modalExito').show()");
            limpiarFormulario();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    private void limpiarFormulario() {
        this.descripcion = null;
        this.zona = null;
        this.tipoReporteId = null;
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}