package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Deshorrne;
import com.carboexco.produccionCoquizacion.repository.DeshorrneRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/deshorrnes")
public class DeshorrneController {

    @Autowired
    private DeshorrneRepository deshorrneRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getDeshorrneAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Deshorrne> deshorrneList = deshorrneRepository.findAll();
            return ResponseEntity.ok(deshorrneList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDeshorrneById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Deshorrne> deshorrne = deshorrneRepository.findById(id);

            if (deshorrne.isPresent()) {
                return ResponseEntity.ok(deshorrne.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> postDeshorrne(@RequestHeader("Authorization") String bearerToken, @RequestBody Deshorrne deshorrne) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            deshorrneRepository.save(deshorrne);
            return ResponseEntity.status(HttpStatus.CREATED).body(deshorrne);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> putDeshorrneById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody Deshorrne deshorrne) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Deshorrne> deshorrneCurrent = deshorrneRepository.findById(id);

            if (deshorrneCurrent.isPresent()) {
                Deshorrne deshorrneReturn = deshorrneCurrent.get();

                deshorrneReturn.setIdAsignacion(deshorrne.getIdAsignacion());
                deshorrneReturn.setIdProceso(deshorrne.getIdProceso());
                deshorrneReturn.setIdPropiedades(deshorrne.getIdPropiedades());
                deshorrneReturn.setFechaHoraInicio(deshorrne.getFechaHoraInicio());
                deshorrneReturn.setFechaHoraFinal(deshorrne.getFechaHoraFinal());
                deshorrneReturn.setTonelajeCoque(deshorrne.getTonelajeCoque());

                deshorrneRepository.save(deshorrneReturn);
                return ResponseEntity.ok(deshorrneReturn);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDeshorrneById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Deshorrne> deshorrne = deshorrneRepository.findById(id);

            if (deshorrne.isPresent()) {
                deshorrneRepository.deleteById(id);
                return ResponseEntity.ok(deshorrne.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}