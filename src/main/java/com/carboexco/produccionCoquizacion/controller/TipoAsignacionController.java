package com.carboexco.produccionCoquizacion.controller;
import com.carboexco.produccionCoquizacion.entity.TipoAsignacion;
import com.carboexco.produccionCoquizacion.repository.TipoAsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/tipoasignaciones")
public class TipoAsignacionController {

    @Autowired
    private TipoAsignacionRepository tipoAsignacionRepository;

    @GetMapping
    public List<TipoAsignacion> getAllTipoAsignaciones() {
        return tipoAsignacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public TipoAsignacion getTipoAsignacionById(@PathVariable int id) {
        Optional<TipoAsignacion> tipoAsignacion = tipoAsignacionRepository.findById(id);
        return tipoAsignacion.orElse(null);
    }

    @PostMapping
    public TipoAsignacion createTipoAsignacion(@RequestBody TipoAsignacion tipoAsignacion) {
        return tipoAsignacionRepository.save(tipoAsignacion);
    }

    @PutMapping("/{id}")
    public TipoAsignacion updateTipoAsignacion(@PathVariable int id, @RequestBody TipoAsignacion tipoAsignacion) {
        Optional<TipoAsignacion> tipoAsignacionCurrent = tipoAsignacionRepository.findById(id);

        if (tipoAsignacionCurrent.isPresent()) {
            TipoAsignacion tipoAsignacionToUpdate = tipoAsignacionCurrent.get();
            tipoAsignacionToUpdate.setTipoAsignacion(tipoAsignacion.getTipoAsignacion());

            return tipoAsignacionRepository.save(tipoAsignacionToUpdate);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public TipoAsignacion deleteTipoAsignacion(@PathVariable int id) {
        Optional<TipoAsignacion> tipoAsignacion = tipoAsignacionRepository.findById(id);

        if (tipoAsignacion.isPresent()) {
            TipoAsignacion tipoAsignacionToDelete = tipoAsignacion.get();
            tipoAsignacionRepository.delete(tipoAsignacionToDelete);
            return tipoAsignacionToDelete;
        }

        return null;
    }
}

