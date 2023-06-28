package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Asignacion;
import com.carboexco.produccionCoquizacion.repository.AsignacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asignaciones")
public class AsignacionController {

    @Autowired
    private AsignacionRepository asignacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAllAsignaciones(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Asignacion> asignacionList = asignacionRepository.findAll();
            return ResponseEntity.ok(asignacionList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAsignacionById(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

            if (asignacionOptional.isPresent()) {
                Asignacion asignacion = asignacionOptional.get();
                return ResponseEntity.ok(asignacion);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody Asignacion asignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Asignacion createdAsignacion = asignacionRepository.save(asignacion);
            return ResponseEntity.ok(createdAsignacion);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id,
            @RequestBody Asignacion asignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

            if (asignacionOptional.isPresent()) {
                Asignacion existingAsignacion = asignacionOptional.get();
                // Actualizar los campos necesarios de existingAsignacion con los valores de asignacion recibidos en el body

                Asignacion updatedAsignacion = asignacionRepository.save(existingAsignacion);
                return ResponseEntity.ok(updatedAsignacion);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

            if (asignacionOptional.isPresent()) {
                asignacionRepository.deleteById(id);
                return ResponseEntity.ok(asignacionOptional.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}