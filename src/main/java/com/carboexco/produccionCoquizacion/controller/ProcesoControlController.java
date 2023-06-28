package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.ProcesoControl;
import com.carboexco.produccionCoquizacion.repository.ProcesoControlRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<ProcesoControl> getProcesoControlAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            return procesoControlRepository.findAll();
        }
        return Collections.emptyList();
    }

    @GetMapping("/{idProceso}/{idControlPila}")
    public ProcesoControl getProcesoControlById(@RequestHeader("Authorization") String bearerToken, @PathVariable int idProceso, @PathVariable int idControlPila) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
            Optional<ProcesoControl> procesoControl = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);
            if (procesoControl.isPresent()) {
                return procesoControl.get();
            }
        }
        return null;
    }

    @PostMapping
    public ProcesoControl postProcesoControl(@RequestHeader("Authorization") String bearerToken, @RequestBody ProcesoControl procesoControl) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            procesoControlRepository.save(procesoControl);
            return procesoControl;
        }
        return null;
    }

    @PutMapping("/{idProceso}/{idControlPila}")
    public ProcesoControl putProcesoControlById(@RequestHeader("Authorization") String bearerToken, @PathVariable int idProceso, @PathVariable int idControlPila, @RequestBody ProcesoControl procesoControl) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
            Optional<ProcesoControl> procesoControlCurrent = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);

            if (procesoControlCurrent.isPresent()) {
                ProcesoControl procesoControlReturn = procesoControlCurrent.get();
                procesoControlReturn.setIdProceso(procesoControl.getIdProceso());
                procesoControlReturn.setIdControlPila(procesoControl.getIdControlPila());

                procesoControlRepository.save(procesoControlReturn);
                return procesoControlReturn;
            }
        }
        return null;
    }

    @DeleteMapping("/{idProceso}/{idControlPila}")
    public ProcesoControl deleteProcesoControlById(@RequestHeader("Authorization") String bearerToken, @PathVariable int idProceso, @PathVariable int idControlPila) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
            Optional<ProcesoControl> procesoControl = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);

            if (procesoControl.isPresent()) {
                ProcesoControl procesoControlReturn = procesoControl.get();
                procesoControlRepository.deleteByIdProcesoAndIdControlPila(idProceso, idControlPila);
                return procesoControlReturn;
            }
        }
        return null;
    }
}