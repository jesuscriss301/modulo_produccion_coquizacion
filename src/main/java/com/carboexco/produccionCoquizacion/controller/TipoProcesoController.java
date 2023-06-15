package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.TipoProceso;
import com.carboexco.produccionCoquizacion.repository.TipoProcesoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tipoprocesos")
public class TipoProcesoController {

    @Autowired
    private TipoProcesoRepository tipoProcesoRepository;

    @GetMapping
    public List<TipoProceso> getAllTipoProcesos() {
        return tipoProcesoRepository.findAll();
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
