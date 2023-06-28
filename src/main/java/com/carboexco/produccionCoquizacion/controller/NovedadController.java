package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Novedad;
import com.carboexco.produccionCoquizacion.repository.NovedadRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/novedades")
public class NovedadController {

    @Autowired
    private NovedadRepository novedadRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getNovedadAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Novedad> novedadList = novedadRepository.findAll();
            return ResponseEntity.ok(novedadList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNovedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Novedad> novedad = novedadRepository.findById(id);

            if (novedad.isPresent()) {
                return ResponseEntity.ok(novedad.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> postNovedad(@RequestHeader("Authorization") String bearerToken, @RequestBody Novedad novedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            novedadRepository.save(novedad);
            return ResponseEntity.status(HttpStatus.CREATED).body(novedad);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putNovedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody Novedad novedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Novedad> novedadCurrent = novedadRepository.findById(id);

            if (novedadCurrent.isPresent()) {
                Novedad novedadReturn = novedadCurrent.get();

                novedadReturn.setIdUsuario(novedad.getIdUsuario());
                novedadReturn.setIdProceso(novedad.getIdProceso());
                novedadReturn.setFechaHora(novedad.getFechaHora());
                novedadReturn.setObservacion(novedad.getObservacion());
                novedadReturn.setIdFotografia(novedad.getIdFotografia());

                novedadRepository.save(novedadReturn);
                return ResponseEntity.ok(novedadReturn);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNovedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Novedad> novedad = novedadRepository.findById(id);

            if (novedad.isPresent()) {
                novedadRepository.deleteById(id);
                return ResponseEntity.ok(novedad.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}