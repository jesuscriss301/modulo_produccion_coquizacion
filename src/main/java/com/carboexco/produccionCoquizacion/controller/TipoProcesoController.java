package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.TipoProceso;
import com.carboexco.produccionCoquizacion.repository.TipoProcesoRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.ConnectException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoprocesos")
public class TipoProcesoController {

    @Autowired
    private TipoProcesoRepository tipoProcesoRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<TipoProceso> getAllTipoProcesos(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return tipoProcesoRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public TipoProceso getTipoProcesoById(@RequestHeader("Authorization") String bearerToken,@PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<TipoProceso> tipoProceso = tipoProcesoRepository.findById(id);
            return tipoProceso.orElse(null);
        }
        return null;
    }

    @PostMapping
    public TipoProceso createTipoProceso(@RequestHeader("Authorization") String bearerToken,@RequestBody TipoProceso tipoProceso) {

        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return tipoProcesoRepository.save(tipoProceso);
        }
        return null;


    }

    @PutMapping("/{id}")
    public TipoProceso updateTipoProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody TipoProceso tipoProceso) {

        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoProceso> tipoProcesoCurrent = tipoProcesoRepository.findById(id);

            if (tipoProcesoCurrent.isPresent()) {
                TipoProceso tipoProcesoToUpdate = tipoProcesoCurrent.get();
                tipoProcesoToUpdate.setNombre(tipoProceso.getNombre());
                return tipoProcesoRepository.save(tipoProcesoToUpdate);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public TipoProceso deleteTipoProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<TipoProceso> tipoProceso = tipoProcesoRepository.findById(id);

            if (tipoProceso.isPresent()) {
                TipoProceso tipoProcesoToDelete = tipoProceso.get();
                tipoProcesoRepository.delete(tipoProcesoToDelete);
                return tipoProcesoToDelete;
            }
        }
        return null;
    }
}
