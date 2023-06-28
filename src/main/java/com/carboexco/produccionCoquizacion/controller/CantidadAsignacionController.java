package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.CantidadAsignacion;
import com.carboexco.produccionCoquizacion.repository.CantidadAsignacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cantidades-asignaciones")
public class CantidadAsignacionController {

    @Autowired
    private CantidadAsignacionRepository cantidadAsignacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAllCantidadAsignaciones(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<CantidadAsignacion> cantidadAsignacionList = cantidadAsignacionRepository.findAll();
            return ResponseEntity.ok(cantidadAsignacionList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{asignacion}/{tipoAsignacion}")
    public ResponseEntity<?> getCantidadAsignacionById(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer asignacion,
            @PathVariable Integer tipoAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<CantidadAsignacion> cantidadAsignacionList = cantidadAsignacionRepository.findByIdAsignacionAndIdTipoAsignacion(asignacion, tipoAsignacion);
            return ResponseEntity.ok(cantidadAsignacionList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createCantidadAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody CantidadAsignacion cantidadAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            CantidadAsignacion createdCantidadAsignacion = cantidadAsignacionRepository.save(cantidadAsignacion);
            return ResponseEntity.ok(createdCantidadAsignacion);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCantidadAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id,
            @RequestBody CantidadAsignacion cantidadAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<CantidadAsignacion> cantidadAsignacionOptional = cantidadAsignacionRepository.findById(id);

            if (cantidadAsignacionOptional.isPresent()) {
                CantidadAsignacion existingCantidadAsignacion = cantidadAsignacionOptional.get();
                // Actualizar los campos necesarios de existingCantidadAsignacion con los valores de cantidadAsignacion recibidos en el body

                CantidadAsignacion updatedCantidadAsignacion = cantidadAsignacionRepository.save(existingCantidadAsignacion);
                return ResponseEntity.ok(updatedCantidadAsignacion);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCantidadAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<CantidadAsignacion> cantidadAsignacionOptional = cantidadAsignacionRepository.findById(id);

            if (cantidadAsignacionOptional.isPresent()) {
                cantidadAsignacionRepository.deleteById(id);
                return ResponseEntity.ok(cantidadAsignacionOptional.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}
