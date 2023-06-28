package com.carboexco.produccionCoquizacion.controller;import com.carboexco.produccionCoquizacion.entity.ProcesoCoquizacion;
import com.carboexco.produccionCoquizacion.repository.ProcesoCoquizacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/procesos")
public class ProcesoCoquizacionController {

    @Autowired
    private ProcesoCoquizacionRepository procesoCoquizacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public List<ProcesoCoquizacion> getAllProcesos(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return procesoCoquizacionRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{id}")
    public ProcesoCoquizacion getProcesoById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<ProcesoCoquizacion> proceso = procesoCoquizacionRepository.findById(id);
            return proceso.orElse(null);
        }
        return null;
    }

    @PostMapping
    public ProcesoCoquizacion createProceso(@RequestHeader("Authorization") String bearerToken, @RequestBody ProcesoCoquizacion proceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            return procesoCoquizacionRepository.save(proceso);
        }
        return null;
    }

    @PutMapping("/{id}")
    public ProcesoCoquizacion updateProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody ProcesoCoquizacion proceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<ProcesoCoquizacion> procesoCurrent = procesoCoquizacionRepository.findById(id);

            if (procesoCurrent.isPresent()) {
                ProcesoCoquizacion procesoToUpdate = procesoCurrent.get();
                procesoToUpdate.setIdIdTipoProceso(proceso.getIdIdTipoProceso());
                procesoToUpdate.setIdProducto(proceso.getIdProducto());
                procesoToUpdate.setFecha(proceso.getFecha());

                return procesoCoquizacionRepository.save(procesoToUpdate);
            }
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ProcesoCoquizacion deleteProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200){
            Optional<ProcesoCoquizacion> proceso = procesoCoquizacionRepository.findById(id);

            if (proceso.isPresent()) {
                ProcesoCoquizacion procesoToDelete = proceso.get();
                procesoCoquizacionRepository.delete(procesoToDelete);
                return procesoToDelete;
            }
        }
        return null;
    }
}