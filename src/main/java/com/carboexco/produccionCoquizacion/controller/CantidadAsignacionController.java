package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.CantidadAsignacion;
import com.carboexco.produccionCoquizacion.repository.CantidadAsignacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/cantidades-asignaciones")
public class CantidadAsignacionController {

    @Autowired
    private CantidadAsignacionRepository cantidadAsignacionRepository;

    @GetMapping
    public List<CantidadAsignacion> getAllCantidadAsignaciones() {
        return cantidadAsignacionRepository.findAll();
    }

    @GetMapping("/{id}")
    public List<CantidadAsignacion> getCantidadAsignacionById(@PathVariable Integer asignacion,@PathVariable Integer tipoAsignacion) {
        return cantidadAsignacionRepository.findByIdAsignacionAndIdTipoAsignacion(asignacion,tipoAsignacion);
    }

    @PostMapping
    public ResponseEntity<CantidadAsignacion> createCantidadAsignacion(@RequestBody CantidadAsignacion cantidadAsignacion) {
        CantidadAsignacion createdCantidadAsignacion = cantidadAsignacionRepository.save(cantidadAsignacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCantidadAsignacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CantidadAsignacion> updateCantidadAsignacion(@PathVariable Integer id, @RequestBody CantidadAsignacion cantidadAsignacion) {
        Optional<CantidadAsignacion> cantidadAsignacionOptional = cantidadAsignacionRepository.findById(id);

        if (cantidadAsignacionOptional.isPresent()) {
            CantidadAsignacion existingCantidadAsignacion = cantidadAsignacionOptional.get();
            // Actualizar los campos necesarios de existingCantidadAsignacion con los valores de cantidadAsignacion recibidos en el body

            CantidadAsignacion updatedCantidadAsignacion = cantidadAsignacionRepository.save(existingCantidadAsignacion);
            return ResponseEntity.ok(updatedCantidadAsignacion);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCantidadAsignacion(@PathVariable Integer id) {
        Optional<CantidadAsignacion> cantidadAsignacionOptional = cantidadAsignacionRepository.findById(id);

        if (cantidadAsignacionOptional.isPresent()) {
            cantidadAsignacionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
