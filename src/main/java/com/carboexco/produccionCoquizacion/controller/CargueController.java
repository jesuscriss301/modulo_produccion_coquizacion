package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Cargue;
import com.carboexco.produccionCoquizacion.entity.CargueId;
import com.carboexco.produccionCoquizacion.repository.CargueRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargues")
public class CargueController {

    @Autowired
    private CargueRepository cargueRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getAllCargues(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Cargue> cargueList = cargueRepository.findAll();
            return ResponseEntity.ok(cargueList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{idCargue}/{idUsuario}")
    public ResponseEntity<?> getCargueById_IdCargueAndId_IdUsuarioOrderByIdProceso_FechaDesc(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer idCargue,
            @PathVariable Integer idUsuario) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargue> cargueOptional = cargueRepository.findById_IdCargueAndId_IdUsuarioOrderByIdProceso_FechaDesc(idCargue, idUsuario);

            if (cargueOptional.isPresent()) {
                Cargue cargue = cargueOptional.get();
                return ResponseEntity.ok(cargue);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{idCargue}")
    public ResponseEntity<?> getCargueById_IdCargueOrderByIdProceso_FechaAsc(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer idCargue) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Cargue> cargueList = cargueRepository.findById_IdCargueOrderByIdProceso_FechaAsc(idCargue);
            return ResponseEntity.ok(cargueList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{idUsuario}")
    public ResponseEntity<?> getCargueById_IdUsuarioOrderByIdProceso_FechaDesc(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer idUsuario) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<Cargue> cargueList = cargueRepository.findById_IdUsuarioOrderByIdProceso_FechaDesc(idUsuario);
            return ResponseEntity.ok(cargueList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createCargue(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody Cargue cargue) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Cargue createdCargue = cargueRepository.save(cargue);
            return ResponseEntity.ok(createdCargue);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCargue(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable CargueId id,
            @RequestBody Cargue cargue) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargue> cargueOptional = cargueRepository.findById(id);

            if (cargueOptional.isPresent()) {
                Cargue existingCargue = cargueOptional.get();
                // Actualizar los campos necesarios de existingCargue con los valores de cargue recibidos en el body

                Cargue updatedCargue = cargueRepository.save(existingCargue);
                return ResponseEntity.ok(updatedCargue);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCargue(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable CargueId id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargue> cargueOptional = cargueRepository.findById(id);

            if (cargueOptional.isPresent()) {
                cargueRepository.deleteById(id);
                return ResponseEntity.ok(cargueOptional.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}