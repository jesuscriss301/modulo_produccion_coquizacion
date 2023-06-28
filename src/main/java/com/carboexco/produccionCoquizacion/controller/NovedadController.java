package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.Novedad;
import com.carboexco.produccionCoquizacion.repository.NovedadRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Novedad> getNovedadAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return novedadRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Novedad getNovedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Novedad> novedad = novedadRepository.findById(id);

            if (novedad.isPresent()) {
                return novedad.get();
            }
        }
        return null;
    }

    @PostMapping
    public Novedad postNovedad(@RequestHeader("Authorization") String bearerToken, @RequestBody Novedad novedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            novedadRepository.save(novedad);
            return novedad;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Novedad putNovedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody Novedad novedad) {
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
                return novedadReturn;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Novedad deleteNovedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Novedad> novedad = novedadRepository.findById(id);

            if (novedad.isPresent()) {
                Novedad novedadReturn = novedad.get();
                novedadRepository.deleteById(id);
                return novedadReturn;
            }
        }
        return null;
    }
}