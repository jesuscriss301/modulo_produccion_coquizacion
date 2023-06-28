package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Deshorrne;
import com.carboexco.produccionCoquizacion.repository.DeshorrneRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Deshorrne> getDeshorrneAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return deshorrneRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public Deshorrne getDeshorrneById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Deshorrne> deshorrne = deshorrneRepository.findById(id);

            if (deshorrne.isPresent()) {
                return deshorrne.get();
            }
        }
        return null;
    }

    @PostMapping
    public Deshorrne postDeshorrne(@RequestHeader("Authorization") String bearerToken, @RequestBody Deshorrne deshorrne) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            deshorrneRepository.save(deshorrne);
            return deshorrne;
        }
        return null;
    }

    @PutMapping("/{id}")
    public Deshorrne putDeshorrneById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody Deshorrne deshorrne) {
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
                return deshorrneReturn;
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Deshorrne deleteDeshorrneById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<Deshorrne> deshorrne = deshorrneRepository.findById(id);

            if (deshorrne.isPresent()) {
                Deshorrne deshorrneReturn = deshorrne.get();
                deshorrneRepository.deleteById(id);
                return deshorrneReturn;
            }
        }
        return null;
    }
}