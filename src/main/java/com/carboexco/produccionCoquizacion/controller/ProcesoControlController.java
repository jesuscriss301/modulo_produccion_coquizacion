package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.ProcesoControl;
import com.carboexco.produccionCoquizacion.repository.ProcesoControlRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/procesos-control")
public class ProcesoControlController {

    @Autowired
    private ProcesoControlRepository procesoControlRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getProcesoControlAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<ProcesoControl> procesoControlList = procesoControlRepository.findAll();
            return ResponseEntity.ok(procesoControlList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{idProceso}/{idControlPila}")
    public ResponseEntity<?> getProcesoControlById(@RequestHeader("Authorization") String bearerToken, @PathVariable int idProceso, @PathVariable int idControlPila) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
            Optional<ProcesoControl> procesoControl = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);
            if (procesoControl.isPresent()) {
                return ResponseEntity.ok(procesoControl.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> postProcesoControl(@RequestHeader("Authorization") String bearerToken, @RequestBody ProcesoControl procesoControl) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            procesoControlRepository.save(procesoControl);
            return ResponseEntity.status(HttpStatus.CREATED).body(procesoControl);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{idProceso}/{idControlPila}")
    public ResponseEntity<?> putProcesoControlById(@RequestHeader("Authorization") String bearerToken, @PathVariable int idProceso, @PathVariable int idControlPila, @RequestBody ProcesoControl procesoControl) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
            Optional<ProcesoControl> procesoControlCurrent = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);

            if (procesoControlCurrent.isPresent()) {
                ProcesoControl procesoControlReturn = procesoControlCurrent.get();
                procesoControlReturn.setIdProceso(procesoControl.getIdProceso());
                procesoControlReturn.setIdControlPila(procesoControl.getIdControlPila());

                procesoControlRepository.save(procesoControlReturn);
                return ResponseEntity.ok(procesoControlReturn);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{idProceso}/{idControlPila}")
    public ResponseEntity<?> deleteProcesoControlById(@RequestHeader("Authorization") String bearerToken, @PathVariable int idProceso, @PathVariable int idControlPila) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
            Optional<ProcesoControl> procesoControl = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);

            if (procesoControl.isPresent()) {
                procesoControlRepository.deleteByIdProcesoAndIdControlPila(idProceso, idControlPila);
                return ResponseEntity.ok(procesoControl.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}