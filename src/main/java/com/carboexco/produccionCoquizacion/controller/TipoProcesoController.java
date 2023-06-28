package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.TipoProceso;
import com.carboexco.produccionCoquizacion.repository.TipoProcesoRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoprocesos")
public class TipoProcesoController {

    @Autowired
    private TipoProcesoRepository tipoProcesoRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAllTipoProcesos(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<TipoProceso> tipoProcesos = tipoProcesoRepository.findAll();
            return ResponseEntity.ok(tipoProcesos);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getTipoProcesoById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoProceso> tipoProceso = tipoProcesoRepository.findById(id);
            if (tipoProceso.isPresent()) {
                return ResponseEntity.ok(tipoProceso.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createTipoProceso(@RequestHeader("Authorization") String bearerToken, @RequestBody TipoProceso tipoProceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            TipoProceso createdTipoProceso = tipoProcesoRepository.save(tipoProceso);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTipoProceso);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTipoProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody TipoProceso tipoProceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoProceso> tipoProcesoCurrent = tipoProcesoRepository.findById(id);
            if (tipoProcesoCurrent.isPresent()) {
                TipoProceso tipoProcesoToUpdate = tipoProcesoCurrent.get();
                tipoProcesoToUpdate.setNombre(tipoProceso.getNombre());
                TipoProceso updatedTipoProceso = tipoProcesoRepository.save(tipoProcesoToUpdate);
                return ResponseEntity.ok(updatedTipoProceso);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTipoProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoProceso> tipoProceso = tipoProcesoRepository.findById(id);
            if (tipoProceso.isPresent()) {
                TipoProceso tipoProcesoToDelete = tipoProceso.get();
                tipoProcesoRepository.delete(tipoProcesoToDelete);
                return ResponseEntity.ok(tipoProcesoToDelete);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}
