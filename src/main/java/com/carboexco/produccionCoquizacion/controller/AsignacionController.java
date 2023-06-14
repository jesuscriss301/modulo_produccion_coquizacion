package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Asignacion;
import com.carboexco.produccionCoquizacion.repository.AsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/asignaciones")
public class AsignacionController {

    @Autowired
    private AsignacionRepository asignacionRepository;

    @GetMapping
    public List<Asignacion> getAllAsignaciones() {
        return asignacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asignacion> getAsignacionById(@PathVariable Integer id) {
        Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

        if (asignacionOptional.isPresent()) {
            Asignacion asignacion = asignacionOptional.get();
            return ResponseEntity.ok(asignacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Asignacion> createAsignacion(@RequestBody Asignacion asignacion) {
        Asignacion createdAsignacion = asignacionRepository.save(asignacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAsignacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asignacion> updateAsignacion(@PathVariable Integer id, @RequestBody Asignacion asignacion) {
        Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

        if (asignacionOptional.isPresent()) {
            Asignacion existingAsignacion = asignacionOptional.get();
            // Actualizar los campos necesarios de existingAsignacion con los valores de asignacion recibidos en el body

            Asignacion updatedAsignacion = asignacionRepository.save(existingAsignacion);
            return ResponseEntity.ok(updatedAsignacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAsignacion(@PathVariable Integer id) {
        Optional<Asignacion> asignacionOptional = asignacionRepository.findById(id);

        if (asignacionOptional.isPresent()) {
            asignacionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

