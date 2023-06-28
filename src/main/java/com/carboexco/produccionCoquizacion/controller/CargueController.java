package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Cargue;
import com.carboexco.produccionCoquizacion.entity.CargueId;
import com.carboexco.produccionCoquizacion.repository.CargueRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargues")
public class CargueController {

    @Autowired
    private CargueRepository cargueRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<Cargue> getAllCargues(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return cargueRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{idCargue}/{idUsuario}")
    public ResponseEntity<Cargue> getCargueById_IdCargueAndId_IdUsuarioOrderByIdProceso_FechaDesc(
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
                return ResponseEntity.notFound().build();
            }
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/{idCargue}")
    public List<Cargue> getCargueById_IdCargueOrderByIdProceso_FechaAsc(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer idCargue) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return cargueRepository.findById_IdCargueOrderByIdProceso_FechaAsc(idCargue);
        }
        return Collections.emptyList();
    }

    @GetMapping("/{idUsuario}")
    public List<Cargue> getCargueById_IdUsuarioOrderByIdProceso_FechaDesc(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable Integer idUsuario) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return cargueRepository.findById_IdUsuarioOrderByIdProceso_FechaDesc(idUsuario);
        }
        return Collections.emptyList();
    }

    @PostMapping
    public Cargue createCargue(
            @RequestHeader("Authorization") String bearerToken,
            @RequestBody Cargue cargue) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Cargue createdCargue = cargueRepository.save(cargue);
            return createdCargue;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Cargue updateCargue(
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
                return updatedCargue;
            } else {
                return null;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Cargue deleteCargue(
            @RequestHeader("Authorization") String bearerToken,
            @PathVariable CargueId id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Cargue> cargueOptional = cargueRepository.findById(id);

            if (cargueOptional.isPresent()) {
                cargueRepository.deleteById(id);
                return cargueOptional.get();
            } else {
                return null;
            }
        }
        return null;
    }
}