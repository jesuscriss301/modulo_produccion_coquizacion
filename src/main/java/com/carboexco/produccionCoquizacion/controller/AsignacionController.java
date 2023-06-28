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
    public List<Asignacion> getAllAsignaciones(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return asignacionRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Asignacion getAsignacionById(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

            if (asignacionOptional.isPresent()) {
                Asignacion asignacion = asignacionOptional.get();
                return asignacion;
            }
        }
        return null;
    }

    @PostMapping
    public Asignacion createAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody Asignacion asignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Asignacion createdAsignacion = asignacionRepository.save(asignacion);
            return createdAsignacion;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Asignacion updateAsignacion(
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
                return updatedAsignacion;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Asignacion deleteAsignacion(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

            if (asignacionOptional.isPresent()) {
                asignacionRepository.deleteById(id);
                return asignacionOptional.get();
            }
        }
        return null;
    }
}