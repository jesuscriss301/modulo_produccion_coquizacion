package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.TipoAsignacion;
import com.carboexco.produccionCoquizacion.repository.TipoAsignacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoasignaciones")
public class TipoAsignacionController {

    @Autowired
    private TipoAsignacionRepository tipoAsignacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<TipoAsignacion> getAllTipoAsignaciones(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return tipoAsignacionRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public TipoAsignacion getTipoAsignacionById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<TipoAsignacion> tipoAsignacion = tipoAsignacionRepository.findById(id);
            return tipoAsignacion.orElse(null);
        }
        return null;
    }

    @PostMapping
    public TipoAsignacion createTipoAsignacion(@RequestHeader("Authorization") String bearerToken, @RequestBody TipoAsignacion tipoAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return tipoAsignacionRepository.save(tipoAsignacion);
        }
        return null;
    }

    @PutMapping("/{id}")
    public TipoAsignacion updateTipoAsignacion(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody TipoAsignacion tipoAsignacion) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<TipoAsignacion> tipoAsignacionCurrent = tipoAsignacionRepository.findById(id);

            if (tipoAsignacionCurrent.isPresent()) {
                TipoAsignacion tipoAsignacionToUpdate = tipoAsignacionCurrent.get();
                tipoAsignacionToUpdate.setTipoAsignacion(tipoAsignacion.getTipoAsignacion());

                return tipoAsignacionRepository.save(tipoAsignacionToUpdate);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public TipoAsignacion deleteTipoAsignacion(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<TipoAsignacion> tipoAsignacion = tipoAsignacionRepository.findById(id);

            if (tipoAsignacion.isPresent()) {
                TipoAsignacion tipoAsignacionToDelete = tipoAsignacion.get();
                tipoAsignacionRepository.delete(tipoAsignacionToDelete);
                return tipoAsignacionToDelete;
            }
        }
        return null;
    }
}
