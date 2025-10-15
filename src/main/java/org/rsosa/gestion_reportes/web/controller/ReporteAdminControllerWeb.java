package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpSession;
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

@Component("reporteAdminControllerWeb")
@Scope("view")
@Data
public class ReporteAdminControllerWeb implements Serializable {

    @Autowired
    private ReporteService reporteService;

    @Autowired
    private TrabajadorMunicipalidadEntityRepository trabajadorRepository;

    private AdministradorDto adminLogueado;
    private String zonaAdmin;

    private List<ReporteDto> reportes;
    private ReporteDto reporteSeleccionado;

    private String filtroEstado;
    private String filtroVecino;

    private Long personalSeleccionado;
    private List<PersonalDto> personalDisponible;

    @PostConstruct
    public void init() {
        cargarAdminLogueado();
        cargarReportes();
    }

    private void cargarAdminLogueado() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        if (session != null) {
            this.adminLogueado = (AdministradorDto) session.getAttribute("adminLogueado");
            this.zonaAdmin = (String) session.getAttribute("zonaAdmin");
        }
    }

    public void cargarReportes() {
        if (zonaAdmin != null) {
            this.reportes = reporteService.obtenerTodo().stream()
                    .filter(r -> r.zone().equals(zonaAdmin))
                    .collect(Collectors.toList());
        } else {
            this.reportes = reporteService.obtenerTodo();
        }
    }

    public void aplicarFiltros() {
        List<ReporteDto> todos = reporteService.obtenerTodo();

        if (zonaAdmin != null) {
            todos = todos.stream()
                    .filter(r -> r.zone().equals(zonaAdmin))
                    .collect(Collectors.toList());
        }

        if (filtroEstado != null && !filtroEstado.isEmpty()) {
            todos = todos.stream()
                    .filter(r -> r.state().name().equals(filtroEstado))
                    .collect(Collectors.toList());
        }

        if (filtroVecino != null && !filtroVecino.isEmpty()) {
            todos = todos.stream()
                    .filter(r -> r.neighbor() != null &&
                            r.neighbor().nameNeighbor().contains(filtroVecino))
                    .collect(Collectors.toList());
        }

        this.reportes = todos;
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

            reporteService.actualizarReporte(reporteSeleccionado.reportId(), modDto);
            addMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Personal asignado correctamente");
            cargarReportes();
        } catch (Exception e) {
            addMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
        }
    }

    public boolean puedeAsignar(ReporteDto reporte) {
        return "PENDIENTE".equals(reporte.state().name());
    }

    protected void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance()
                .addMessage(null, new FacesMessage(severity, summary, detail));
    }
}