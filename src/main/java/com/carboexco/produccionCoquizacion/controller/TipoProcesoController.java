package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.TipoProceso;
import com.carboexco.produccionCoquizacion.repository.TipoProcesoRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoprocesos")
public class TipoProcesoController {

    @Autowired
    private TipoProcesoRepository tipoProcesoRepository;

    @GetMapping
    public List<TipoProceso> getAllTipoProcesos(@RequestHeader("Authorization") String bearerToken) {
        System.out.println(bearerToken);
        int validacion = callValidateTokenEndpoint(bearerToken);
        if (validacion==200){
            return tipoProcesoRepository.findAll();
        }
        return Collections.emptyList();
    }

    public int callValidateTokenEndpoint(String bearerToken) {
        RestTemplate restTemplate = new RestTemplate();

        // Construye los encabezados de la solicitud HTTP
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", bearerToken);

        // Realiza la llamada al endpoint /validate
        ResponseEntity<String> response = restTemplate.exchange("http://localhost:8080/validate",
                HttpMethod.GET, null, String.class, headers);

        // Obtiene la respuesta de la validación del token
        String responseBody = response.getBody();
        int statusCode = response.getStatusCodeValue();

        return statusCode;
    }

    @GetMapping("/{id}")
    public TipoProceso getTipoProcesoById(@PathVariable int id) {

        Optional<TipoProceso> tipoProceso = tipoProcesoRepository.findById(id);
        return tipoProceso.orElse(null);
    }

    @PostMapping
    public TipoProceso createTipoProceso(@RequestBody TipoProceso tipoProceso) {
        return tipoProcesoRepository.save(tipoProceso);
    }

    @PutMapping("/{id}")
    public TipoProceso updateTipoProceso(@PathVariable int id, @RequestBody TipoProceso tipoProceso) {
        Optional<TipoProceso> tipoProcesoCurrent = tipoProcesoRepository.findById(id);

        if (tipoProcesoCurrent.isPresent()) {
            TipoProceso tipoProcesoToUpdate = tipoProcesoCurrent.get();
            tipoProcesoToUpdate.setNombre(tipoProceso.getNombre());

            return tipoProcesoRepository.save(tipoProcesoToUpdate);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public TipoProceso deleteTipoProceso(@PathVariable int id) {
        Optional<TipoProceso> tipoProceso = tipoProcesoRepository.findById(id);

        if (tipoProceso.isPresent()) {
            TipoProceso tipoProcesoToDelete = tipoProceso.get();
            tipoProcesoRepository.delete(tipoProcesoToDelete);
            return tipoProcesoToDelete;
        }

        return null;
    }
}
