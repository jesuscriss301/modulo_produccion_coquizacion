package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateria;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateriaId;
import com.carboexco.produccionCoquizacion.repository.ProcesoBateriaRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/procesos-bateria")
public class ProcesoBateriaController {

    @Autowired
    private ProcesoBateriaRepository procesoBateriaRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<ProcesoBateria> getProcesoBateriaAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return procesoBateriaRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{idBateria}/{idProceso}")
    public ProcesoBateria getProcesoBateriaById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idBateria, @PathVariable int idProceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
            Optional<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById(procesoBateriaId);

            if (procesoBateria.isPresent()) {
                return procesoBateria.get();
            }
        }
        return null;
    }

    @PostMapping
    public ProcesoBateria postProcesoBateria(@RequestHeader("Authorization") String bearerToken, @RequestBody ProcesoBateria procesoBateria) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            procesoBateriaRepository.save(procesoBateria);
            return procesoBateria;
        }
        return null;
    }

    @PutMapping("/{idBateria}/{idProceso}")
    public ProcesoBateria putProcesoBateriaById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idBateria, @PathVariable int idProceso, @RequestBody ProcesoBateria procesoBateria) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
            Optional<ProcesoBateria> procesoBateriaCurrent = procesoBateriaRepository.findById(procesoBateriaId);

            if (procesoBateriaCurrent.isPresent()) {
                ProcesoBateria procesoBateriaReturn = procesoBateriaCurrent.get();
                procesoBateriaReturn.setTiempoCoquizacion(procesoBateria.getTiempoCoquizacion());

                procesoBateriaRepository.save(procesoBateriaReturn);
                return procesoBateriaReturn;
            }
        }
        return null;
    }

    @DeleteMapping("/{idBateria}/{idProceso}")
    public ProcesoBateria deleteProcesoBateriaById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idBateria, @PathVariable int idProceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
            Optional<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById(procesoBateriaId);

            if (procesoBateria.isPresent()) {
                ProcesoBateria procesoBateriaReturn = procesoBateria.get();
                procesoBateriaRepository.deleteById(procesoBateriaId);
                return procesoBateriaReturn;
            }
        }
        return null;
    }
}