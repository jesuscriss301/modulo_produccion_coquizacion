package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.AsignacionTipoAsignacion;
import com.carboexco.produccionCoquizacion.repository.AsignacionTipoAsignacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/asignaciones_tipo")
public class AsignacionTipoAsignacionController {

    @Autowired
    private AsignacionTipoAsignacionRepository asignacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAsignacionAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<AsignacionTipoAsignacion> asignacionList = asignacionRepository.findAll();
            return ResponseEntity.ok(asignacionList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAsignacionById(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<AsignacionTipoAsignacion> asignacion = asignacionRepository.findById(id);

            if (asignacion.isPresent()) {
                return ResponseEntity.ok(asignacion.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> postAsignacion(@RequestHeader("Authorization") String bearerToken, @RequestBody AsignacionTipoAsignacion asignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            asignacionRepository.save(asignacion);
            return ResponseEntity.status(HttpStatus.CREATED).body(asignacion);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putAsignacionById(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer id, @RequestBody AsignacionTipoAsignacion asignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<AsignacionTipoAsignacion> asignacionCurrent = asignacionRepository.findById(id);

            if (asignacionCurrent.isPresent()) {
                AsignacionTipoAsignacion asignacionReturn = asignacionCurrent.get();

                // Aquí debes actualizar los atributos de la asignación según tus necesidades
                asignacionReturn.setIdTipoAsignacion(asignacion.getIdTipoAsignacion());
                asignacionReturn.setIdProceso(asignacion.getIdProceso());
                asignacionReturn.setIdAsignado(asignacion.getIdAsignado());

                asignacionRepository.save(asignacionReturn);
                return ResponseEntity.ok(asignacionReturn);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAsignacionById(@RequestHeader("Authorization") String bearerToken, @PathVariable Integer id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<AsignacionTipoAsignacion> asignacion = asignacionRepository.findById(id);

            if (asignacion.isPresent()) {
                asignacionRepository.deleteById(id);
                return ResponseEntity.ok(asignacion.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}