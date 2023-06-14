package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Apagado;
import com.carboexco.produccionCoquizacion.repository.ApagadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/apagados")
public class ApagadoController {

    @Autowired
    private ApagadoRepository apagadoRepository;

    @GetMapping
    public List<Apagado> getAllApagados() {
        return apagadoRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Apagado> getApagadoById(@PathVariable Integer id) {
        Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

        if (apagadoOptional.isPresent()) {
            Apagado apagado = apagadoOptional.get();
            return ResponseEntity.ok(apagado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Apagado> createApagado(@RequestBody Apagado apagado) {
        Apagado createdApagado = apagadoRepository.save(apagado);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdApagado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Apagado> updateApagado(@PathVariable Integer id, @RequestBody Apagado apagado) {
        Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

        if (apagadoOptional.isPresent()) {
            Apagado existingApagado = apagadoOptional.get();
            // Actualizar los campos necesarios de existingApagado con los valores de apagado recibidos en el body

            Apagado updatedApagado = apagadoRepository.save(existingApagado);
            return ResponseEntity.ok(updatedApagado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteApagado(@PathVariable Integer id) {
        Optional<Apagado> apagadoOptional = apagadoRepository.findById(id);

        if (apagadoOptional.isPresent()) {
            apagadoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

