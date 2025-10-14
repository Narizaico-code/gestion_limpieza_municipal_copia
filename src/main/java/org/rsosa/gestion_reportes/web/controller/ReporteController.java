package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.ModReporteDto;
import org.rsosa.gestion_reportes.dominio.dto.ReporteDto;
import org.rsosa.gestion_reportes.dominio.exception.CatalogoInvalidoException;
import org.rsosa.gestion_reportes.dominio.exception.EstadoInvalidoException;
import org.rsosa.gestion_reportes.dominio.exception.PersonalNoExisteException;
import org.rsosa.gestion_reportes.dominio.exception.PersonalOcupadoException;
import org.rsosa.gestion_reportes.dominio.exception.ReporteNoExisteException;
import org.rsosa.gestion_reportes.dominio.exception.TrabajadorSinZonaException;
import org.rsosa.gestion_reportes.dominio.exception.ZonaNoCoincideException;
import org.rsosa.gestion_reportes.persistence.ReporteEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/reportes")
public class ReporteController {

    private final ReporteEntityRepository reporteRepository;

    public ReporteController(ReporteEntityRepository reporteRepository) {
        this.reporteRepository = reporteRepository;
    }

    @PostMapping
    public ResponseEntity<ReporteDto> guardarReporte(@RequestBody @Valid ReporteDto reporteDto){
        ReporteDto nuevoReporte = this.reporteRepository.guardarReporte(reporteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoReporte);
    }

    @GetMapping
    public ResponseEntity<List<ReporteDto>> obtenerTodo(){
        return ResponseEntity.ok(this.reporteRepository.obtenerTodo());
    }

    @GetMapping("/codigo/{id}")
    public ResponseEntity<?> obtenerReportePorCodigo(@PathVariable Long id){
        ResponseEntity<?> response;

        try {
            response = ResponseEntity.ok(this.reporteRepository.obtenerReportePorCodigo(id));
        } catch (ReporteNoExisteException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }

        return response;
    }

    @GetMapping("/vecino/{vecinoNombre}")
    public ResponseEntity<List<ReporteDto>> obtenerReportesPorVecino(@PathVariable String vecinoNombre){
        return ResponseEntity.ok(this.reporteRepository.obtenerReportesPorVecino(vecinoNombre));
    }

    @GetMapping("/estado/{estadoNombre}")
    public ResponseEntity<List<ReporteDto>> obtenerReportesPorEstado(@PathVariable String estadoNombre){
        return ResponseEntity.ok(this.reporteRepository.obtenerReportesPorEstado(estadoNombre));
    }

    @GetMapping("/tipoReporte/{tipoReporteNombre}")
    public ResponseEntity<List<ReporteDto>> obtenerReportesPorTipoReporte(@PathVariable String tipoReporteNombre){
        return ResponseEntity.ok(this.reporteRepository.obtenerReportesPorTipoReporte(tipoReporteNombre));
    }

    @GetMapping("/personal/{personaNombre}")
    public ResponseEntity<List<ReporteDto>> obtenerReportesPorPersonal(@PathVariable String personaNombre){
        return ResponseEntity.ok(this.reporteRepository.obtenerReportesPorPersonal(personaNombre));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarReporte(@PathVariable Long id, @RequestBody @Valid ModReporteDto modReporteDto){
        ResponseEntity<?> response;

        try {
            ReporteDto reporteActualizado = this.reporteRepository.actualizarReporte(id, modReporteDto);
            response = ResponseEntity.ok(reporteActualizado);
        }
        catch (ReporteNoExisteException | PersonalNoExisteException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch (TrabajadorSinZonaException | ZonaNoCoincideException | CatalogoInvalidoException | EstadoInvalidoException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (PersonalOcupadoException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }

        return response;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarReporte(@PathVariable Long id){
        ResponseEntity<?> response;

        try {
            this.reporteRepository.eliminarReporte(id);
            response = ResponseEntity.noContent().build(); // 204 No Content
        } catch (ReporteNoExisteException e) {
            response = new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND); // 404
        }

        return response;
    }
}