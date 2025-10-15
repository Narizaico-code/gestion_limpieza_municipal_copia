package org.rsosa.gestion_reportes.web.controller;

import jakarta.validation.Valid;
import org.rsosa.gestion_reportes.dominio.dto.CalificacionDto;
import org.rsosa.gestion_reportes.dominio.exception.ReporteNoExisteException;
import org.rsosa.gestion_reportes.dominio.exception.VecinoNoExisteException;

import org.rsosa.gestion_reportes.dominio.service.CalificacionService;
import org.rsosa.gestion_reportes.persistence.CalificacionEntityRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;
    private final CalificacionEntityRepository calificacionEntityRepository;

    public CalificacionController(CalificacionService calificacionService, CalificacionEntityRepository calificacionEntityRepository) {
        this.calificacionService = calificacionService;
        this.calificacionEntityRepository = calificacionEntityRepository;
    }

    @GetMapping
    public ResponseEntity<List<CalificacionDto>> obtenerTodo(){
        return ResponseEntity.ok(this.calificacionEntityRepository.obtenerTodo());
    }
    @PostMapping
    public ResponseEntity<?> guardarCalificacion(@RequestBody @Valid CalificacionDto calificacionDto) {
        try {
            CalificacionDto nuevaCalificacion = this.calificacionService.guardarCalificacion(calificacionDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaCalificacion);
        } catch (ReporteNoExisteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        } catch (VecinoNoExisteException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}