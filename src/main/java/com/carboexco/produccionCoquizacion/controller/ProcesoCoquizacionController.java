package com.carboexco.produccionCoquizacion.controller;


import com.carboexco.produccionCoquizacion.entity.ProcesoCoquizacion;
import com.carboexco.produccionCoquizacion.repository.ProcesoCoquizacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/procesos")
public class ProcesoCoquizacionController {

    @Autowired
    private ProcesoCoquizacionRepository procesoCoquizacionRepository;

    @GetMapping
    public List<ProcesoCoquizacion> getAllProcesos() {
        return procesoCoquizacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProcesoCoquizacion getProcesoById(@PathVariable int id) {
        Optional<ProcesoCoquizacion> proceso = procesoCoquizacionRepository.findById(id);
        return proceso.orElse(null);
    }

    @PostMapping
    public ProcesoCoquizacion createProceso(@RequestBody ProcesoCoquizacion proceso) {
        return procesoCoquizacionRepository.save(proceso);
    }

    @PutMapping("/{id}")
    public ProcesoCoquizacion updateProceso(@PathVariable int id, @RequestBody ProcesoCoquizacion proceso) {
        Optional<ProcesoCoquizacion> procesoCurrent = procesoCoquizacionRepository.findById(id);

        if (procesoCurrent.isPresent()) {
            ProcesoCoquizacion procesoToUpdate = procesoCurrent.get();
            procesoToUpdate.setIdIdTipoProceso(proceso.getIdIdTipoProceso());
            procesoToUpdate.setIdProducto(proceso.getIdProducto());
            procesoToUpdate.setFecha(proceso.getFecha());

            return procesoCoquizacionRepository.save(procesoToUpdate);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public ProcesoCoquizacion deleteProceso(@PathVariable int id) {
        Optional<ProcesoCoquizacion> proceso = procesoCoquizacionRepository.findById(id);

        if (proceso.isPresent()) {
            ProcesoCoquizacion procesoToDelete = proceso.get();
            procesoCoquizacionRepository.delete(procesoToDelete);
            return procesoToDelete;
        }

        return null;
    }
}

