package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.TipoAsignacion;
import com.carboexco.produccionCoquizacion.repository.TipoAsignacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoasignaciones")
public class TipoAsignacionController {

    @Autowired
    private TipoAsignacionRepository tipoAsignacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAllTipoAsignaciones(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<TipoAsignacion> tipoAsignaciones = tipoAsignacionRepository.findAll();
            return ResponseEntity.ok(tipoAsignaciones);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTipoAsignacionById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoAsignacion> tipoAsignacion = tipoAsignacionRepository.findById(id);
            if (tipoAsignacion.isPresent()) {
                return ResponseEntity.ok(tipoAsignacion.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createTipoAsignacion(@RequestHeader("Authorization") String bearerToken, @RequestBody TipoAsignacion tipoAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            TipoAsignacion createdTipoAsignacion = tipoAsignacionRepository.save(tipoAsignacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTipoAsignacion);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipoAsignacion(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody TipoAsignacion tipoAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoAsignacion> tipoAsignacionCurrent = tipoAsignacionRepository.findById(id);
            if (tipoAsignacionCurrent.isPresent()) {
                TipoAsignacion tipoAsignacionToUpdate = tipoAsignacionCurrent.get();
                tipoAsignacionToUpdate.setTipoAsignacion(tipoAsignacion.getTipoAsignacion());
                TipoAsignacion updatedTipoAsignacion = tipoAsignacionRepository.save(tipoAsignacionToUpdate);
                return ResponseEntity.ok(updatedTipoAsignacion);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoAsignacion(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoAsignacion> tipoAsignacion = tipoAsignacionRepository.findById(id);
            if (tipoAsignacion.isPresent()) {
                TipoAsignacion tipoAsignacionToDelete = tipoAsignacion.get();
                tipoAsignacionRepository.delete(tipoAsignacionToDelete);
                return ResponseEntity.ok(tipoAsignacionToDelete);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}