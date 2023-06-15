package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.ProcesoControl;
import com.carboexco.produccionCoquizacion.repository.ProcesoControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/procesos-control")
public class ProcesoControlController {

    @Autowired
    ProcesoControlRepository procesoControlRepository;

    @GetMapping
    public List<ProcesoControl> getProcesoControlAll() {
        return procesoControlRepository.findAll();
    }

    @GetMapping("/{idProceso}/{idControlPila}")
    public ProcesoControl getProcesoControlById(@PathVariable int idProceso, @PathVariable int idControlPila) {
        ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
        Optional<ProcesoControl> procesoControl = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso, idControlPila);
        if (procesoControl.isPresent()) {
            return procesoControl.get();
        }

        return null;
    }

    @PostMapping
    public ProcesoControl postProcesoControl(@RequestBody ProcesoControl procesoControl) {
        procesoControlRepository.save(procesoControl);
        return procesoControl;
    }

    @PutMapping("/{idProceso}/{idControlPila}")
    public ProcesoControl putProcesoControlById(@PathVariable int idProceso, @PathVariable int idControlPila, @RequestBody ProcesoControl procesoControl) {
        ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
        Optional<ProcesoControl> procesoControlCurrent = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso,idControlPila);

        if (procesoControlCurrent.isPresent()) {
            ProcesoControl procesoControlReturn = procesoControlCurrent.get();

            procesoControlRepository.save(procesoControlReturn);
            return procesoControlReturn;
        }

        return null;
    }

    @DeleteMapping("/{idProceso}/{idControlPila}")
    public ProcesoControl deleteProcesoControlById(@PathVariable int idProceso, @PathVariable int idControlPila) {
        ProcesoControl procesoControlId = new ProcesoControl(idProceso, idControlPila);
        Optional<ProcesoControl> procesoControl = procesoControlRepository.findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(idProceso,idControlPila);

        if (procesoControl.isPresent()) {
            ProcesoControl procesoControlReturn = procesoControl.get();
            procesoControlRepository.deleteByIdProcesoAndIdControlPila(idProceso,idControlPila);
            return procesoControlReturn;
        }

        return null;
    }
}

