package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.ProcesoCoquizacion;
import com.carboexco.produccionCoquizacion.repository.ProcesoBateriaRepository;
import com.carboexco.produccionCoquizacion.repository.ProcesoCoquizacionRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/procesos")
public class ProcesoCoquizacionController {

    @Autowired
    private ProcesoCoquizacionRepository procesoCoquizacionRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");
    @Autowired
    private ProcesoBateriaRepository procesoBateriaRepository;

    @GetMapping
    public ResponseEntity<?> getAllProcesos(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<ProcesoCoquizacion> procesos = procesoCoquizacionRepository.findAll();
            return ResponseEntity.ok(procesos);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProcesoById(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<ProcesoCoquizacion> proceso = procesoCoquizacionRepository.findById(id);
            if (proceso.isPresent()) {
                return ResponseEntity.ok(proceso.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> createProceso(@RequestHeader("Authorization") String bearerToken, @RequestBody ProcesoCoquizacion proceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            String n = String.valueOf((int) (procesoCoquizacionRepository.countByFecha(proceso.getFecha())+1));
            proceso.setIdPocesoCoquizacion(proceso.getIdPocesoCoquizacion() + "-" + n );
            System.out.println(n);
            ProcesoCoquizacion createdProceso = procesoCoquizacionRepository.save(proceso);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdProceso);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id, @RequestBody ProcesoCoquizacion proceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<ProcesoCoquizacion> procesoCurrent = procesoCoquizacionRepository.findById(id);
            if (procesoCurrent.isPresent()) {
                ProcesoCoquizacion procesoToUpdate = procesoCurrent.get();
                procesoToUpdate.setIdTipoProceso(proceso.getIdTipoProceso());
                procesoToUpdate.setIdProducto(proceso.getIdProducto());
                procesoToUpdate.setFecha(proceso.getFecha());
                ProcesoCoquizacion updatedProceso = procesoCoquizacionRepository.save(procesoToUpdate);
                return ResponseEntity.ok(updatedProceso);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProceso(@RequestHeader("Authorization") String bearerToken, @PathVariable int id) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            Optional<ProcesoCoquizacion> proceso = procesoCoquizacionRepository.findById(id);
            if (proceso.isPresent()) {
                procesoCoquizacionRepository.delete(proceso.get());
                return ResponseEntity.ok(proceso.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}