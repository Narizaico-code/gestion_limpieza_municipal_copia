package org.rsosa.gestion_reportes.web.controller;

import jakarta.annotation.PostConstruct;
import lombok.Data;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;
import org.rsosa.gestion_reportes.dominio.service.ReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.List;

@Component("dashboardControllerWeb")
@Scope("view")
@Data
public class DashboardControllerWeb implements Serializable {

    @Autowired
    private ReporteService reporteService;

    private Integer reportesPendientes;
    private Integer reportesAsignados;
    private Integer reportesResueltos;
    private Integer personalLibre;
    private List<ReporteDto> ultimosReportes;

    @PostConstruct
    public void init() {
        cargarEstadisticas();
    }

    private void cargarEstadisticas() {
        List<ReporteDto> todos = reporteService.obtenerTodo();

        this.reportesPendientes = (int) todos.stream()
                .filter(r -> "PENDIENTE".equals(r.state().name()))
                .count();

        this.reportesAsignados = (int) todos.stream()
                .filter(r -> "ASIGNADO".equals(r.state().name()))
                .count();

        this.reportesResueltos = (int) todos.stream()
                .filter(r -> "RESUELTO".equals(r.state().name()))
                .count();

        // Últimos 5 reportes
        this.ultimosReportes = todos.stream()
                .limit(5)
                .toList();
    }
}