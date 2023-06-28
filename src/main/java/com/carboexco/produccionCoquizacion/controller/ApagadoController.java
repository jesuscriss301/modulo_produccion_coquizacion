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
    public ResponseEntity<?> getAllApagados(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Apagado> apagadoList = apagadoRepository.findAll();
            return ResponseEntity.ok(apagadoList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getApagadoById(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

            if (apagadoOptional.isPresent()) {
                Apagado apagado = apagadoOptional.get();
                return ResponseEntity.ok(apagado);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createApagado(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody Apagado apagado) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Apagado createdApagado = apagadoRepository.save(apagado);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdApagado);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateApagado(
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
                return ResponseEntity.ok(updatedApagado);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteApagado(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

            if (apagadoOptional.isPresent()) {
                apagadoRepository.deleteById(id);
                return ResponseEntity.ok(apagadoOptional.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}