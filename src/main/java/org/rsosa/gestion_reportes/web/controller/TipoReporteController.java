package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.TipoReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.VecinoDto;
import org.rsosa.gestion_reportes.dominio.service.TipoReporteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

@RestController
@RequestMapping("/v1/tiposReporte")
public class TipoReporteController {
    private final TipoReporteService tipoReporteService;

    public TipoReporteController(TipoReporteService tipoReporteService) {
        this.tipoReporteService = tipoReporteService;
    }
    @GetMapping
    public ResponseEntity<List<TipoReporteDto>> obtenerTodo(){
        return ResponseEntity.ok(this.tipoReporteService.obtenerTodo());
    }
    @GetMapping("{id}")
    public ResponseEntity<?> obtenerTipoReportePorCodigo(Long id){
        return ResponseEntity.ok(this.tipoReporteService.obtenerTipoReportePorCodigo(id));
    }
    @PostMapping
    public ResponseEntity<TipoReporteDto> guardarTipoReporte(@RequestBody @Valid  TipoReporteDto tipoReporteDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(this.tipoReporteService.guardarTipoReporte(tipoReporteDto));
    }
    @PutMapping("{id}")
    public ResponseEntity<TipoReporteDto> actualizarTipoReporte(@PathVariable Long id, @RequestBody TipoReporteDto tipoReporteDto){
        return ResponseEntity.ok(this.tipoReporteService.actualizarTipoReporte(id, tipoReporteDto));
    }
    @DeleteMapping("{id}")
    public ResponseEntity<TipoReporteDto> eliminarTipoReporte(@PathVariable Long id) {
        this.tipoReporteService.eliminarTipoReporte(id);
        return ResponseEntity.ok().build();
    }
}
