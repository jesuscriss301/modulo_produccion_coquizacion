package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.PropiedadCoque;
import com.carboexco.produccionCoquizacion.repository.PropiedadCoqueRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propiedades")
public class PropiedadCoqueController {

    @Autowired
    private PropiedadCoqueRepository propiedadCoqueRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAllPropiedades(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<PropiedadCoque> propiedades = propiedadCoqueRepository.findAll();
            return ResponseEntity.ok(propiedades);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPropiedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<PropiedadCoque> propiedad = propiedadCoqueRepository.findById(id);
            if (propiedad.isPresent()) {
                return ResponseEntity.ok(propiedad.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createPropiedad(@RequestHeader("Authorization") String bearerToken, @RequestBody PropiedadCoque propiedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            PropiedadCoque createdPropiedad = propiedadCoqueRepository.save(propiedad);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdPropiedad);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePropiedad(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody PropiedadCoque propiedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<PropiedadCoque> propiedadCurrent = propiedadCoqueRepository.findById(id);
            if (propiedadCurrent.isPresent()) {
                PropiedadCoque propiedadToUpdate = propiedadCurrent.get();
                propiedadToUpdate.setCompactacion(propiedad.getCompactacion());
                propiedadToUpdate.setDureza(propiedad.getDureza());
                propiedadToUpdate.setColorCoque(propiedad.getColorCoque());
                PropiedadCoque updatedPropiedad = propiedadCoqueRepository.save(propiedadToUpdate);
                return ResponseEntity.ok(updatedPropiedad);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePropiedad(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<PropiedadCoque> propiedad = propiedadCoqueRepository.findById(id);
            if (propiedad.isPresent()) {
                propiedadCoqueRepository.delete(propiedad.get());
                return ResponseEntity.ok(propiedad.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}