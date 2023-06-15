package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateria;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateriaId;
import com.carboexco.produccionCoquizacion.repository.ProcesoBateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/procesos-bateria")
public class ProcesoBateriaController {

    @Autowired
    ProcesoBateriaRepository procesoBateriaRepository;

    @GetMapping
    public List<ProcesoBateria> getProcesoBateriaAll() {
        return procesoBateriaRepository.findAll();
    }

    @GetMapping("/{idBateria}/{idProceso}")
    public ProcesoBateria getProcesoBateriaById(@PathVariable String idBateria, @PathVariable int idProceso) {
        ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
        Optional<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById(procesoBateriaId);

        if (procesoBateria.isPresent()) {
            return procesoBateria.get();
        }

        return null;
    }

    @PostMapping
    public ProcesoBateria postProcesoBateria(@RequestBody ProcesoBateria procesoBateria) {
        procesoBateriaRepository.save(procesoBateria);
        return procesoBateria;
    }

    @PutMapping("/{idBateria}/{idProceso}")
    public ProcesoBateria putProcesoBateriaById(@PathVariable String idBateria, @PathVariable int idProceso, @RequestBody ProcesoBateria procesoBateria) {
        ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
        Optional<ProcesoBateria> procesoBateriaCurrent = procesoBateriaRepository.findById(procesoBateriaId);

        if (procesoBateriaCurrent.isPresent()) {
            ProcesoBateria procesoBateriaReturn = procesoBateriaCurrent.get();

            procesoBateriaReturn.setTiempoCoquizacion(procesoBateria.getTiempoCoquizacion());

            procesoBateriaRepository.save(procesoBateriaReturn);
            return procesoBateriaReturn;
        }

        return null;
    }

    @DeleteMapping("/{idBateria}/{idProceso}")
    public ProcesoBateria deleteProcesoBateriaById(@PathVariable String idBateria, @PathVariable int idProceso) {
        ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
        Optional<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById(procesoBateriaId);

        if (procesoBateria.isPresent()) {
            ProcesoBateria procesoBateriaReturn = procesoBateria.get();
            procesoBateriaRepository.deleteById(procesoBateriaId);
            return procesoBateriaReturn;
        }

        return null;
    }
}

