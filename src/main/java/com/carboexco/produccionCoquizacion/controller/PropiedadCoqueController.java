package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.PropiedadCoque;
import com.carboexco.produccionCoquizacion.repository.PropiedadCoqueRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/propiedades")
public class PropiedadCoqueController {

    @Autowired
    private PropiedadCoqueRepository propiedadCoqueRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<PropiedadCoque> getAllPropiedades(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return propiedadCoqueRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public PropiedadCoque getPropiedadById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<PropiedadCoque> propiedad = propiedadCoqueRepository.findById(id);
            return propiedad.orElse(null);
        }
        return null;
    }

    @PostMapping
    public PropiedadCoque createPropiedad(@RequestHeader("Authorization") String bearerToken, @RequestBody PropiedadCoque propiedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return propiedadCoqueRepository.save(propiedad);
        }
        return null;
    }

    @PutMapping("/{id}")
    public PropiedadCoque updatePropiedad(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody PropiedadCoque propiedad) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<PropiedadCoque> propiedadCurrent = propiedadCoqueRepository.findById(id);

            if (propiedadCurrent.isPresent()) {
                PropiedadCoque propiedadToUpdate = propiedadCurrent.get();
                propiedadToUpdate.setCompactacion(propiedad.getCompactacion());
                propiedadToUpdate.setDureza(propiedad.getDureza());
                propiedadToUpdate.setColorCoque(propiedad.getColorCoque());

                return propiedadCoqueRepository.save(propiedadToUpdate);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public PropiedadCoque deletePropiedad(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<PropiedadCoque> propiedad = propiedadCoqueRepository.findById(id);

            if (propiedad.isPresent()) {
                PropiedadCoque propiedadToDelete = propiedad.get();
                propiedadCoqueRepository.delete(propiedadToDelete);
                return propiedadToDelete;
            }
        }
        return null;
    }
}
