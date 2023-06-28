package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.Apagado;
import com.carboexco.produccionCoquizacion.repository.ApagadoRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apagados")
public class ApagadoController {

    @Autowired
    private ApagadoRepository apagadoRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<Apagado> getAllApagados(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return apagadoRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apagado> getApagadoById(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

            if (apagadoOptional.isPresent()) {
                Apagado apagado = apagadoOptional.get();
                return ResponseEntity.ok(apagado);
            } else {
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping
    public ResponseEntity<Apagado> createApagado(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody Apagado apagado) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Apagado createdApagado = apagadoRepository.save(apagado);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdApagado);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{id}")
    public Apagado updateApagado(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id,
            @RequestBody Apagado apagado) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

            if (apagadoOptional.isPresent()) {
                Apagado existingApagado = apagadoOptional.get();
                // Actualizar los campos necesarios de existingApagado con los valores de apagado recibidos en el body

                Apagado updatedApagado = apagadoRepository.save(existingApagado);
                return updatedApagado;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Apagado deleteApagado(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

            if (apagadoOptional.isPresent()) {
                apagadoRepository.deleteById(id);
                return apagadoOptional.get();
            }
        }
        return null;
    }
}