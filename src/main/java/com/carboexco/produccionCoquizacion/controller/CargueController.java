package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Cargue;
import com.carboexco.produccionCoquizacion.entity.CargueId;
import com.carboexco.produccionCoquizacion.repository.CargueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cargues")
public class CargueController {

    @Autowired
    private CargueRepository cargueRepository;

    @GetMapping
    public List<Cargue> getAllCargues() {
        return cargueRepository.findAll();
    }

    @GetMapping("/{idCargue}/{idUsuario}")
    public ResponseEntity<Cargue> getCargueById_IdCargueAndId_IdUsuarioOrderByIdProceso_FechaDesc(@PathVariable Integer idCargue, @PathVariable Integer idUsuario) {
        Optional<Cargue> cargueOptional = cargueRepository.findById_IdCargueAndId_IdUsuarioOrderByIdProceso_FechaDesc(idCargue,idUsuario);

        if (cargueOptional.isPresent()) {
            Cargue cargue = cargueOptional.get();
            return ResponseEntity.ok(cargue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{idCargue}")
    public List<Cargue> getCargueById_IdCargueOrderByIdProceso_FechaAsc(@PathVariable Integer idCargue) {

        return cargueRepository.findById_IdCargueOrderByIdProceso_FechaAsc(idCargue);
    }
    @GetMapping("/{idUsuario}")
    public List<Cargue> getCargueById_IdUsuarioOrderByIdProceso_FechaDesc(@PathVariable Integer idCargue) {

        return cargueRepository.findById_IdUsuarioOrderByIdProceso_FechaDesc(idCargue);
    }

    @PostMapping
    public ResponseEntity<Cargue> createCargue(@RequestBody Cargue cargue) {
        Cargue createdCargue = cargueRepository.save(cargue);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCargue);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cargue> updateCargue(@PathVariable CargueId id, @RequestBody Cargue cargue) {
        Optional<Cargue> cargueOptional = cargueRepository.findById(id);

        if (cargueOptional.isPresent()) {
            Cargue existingCargue = cargueOptional.get();
            // Actualizar los campos necesarios de existingCargue con los valores de cargue recibidos en el body

            Cargue updatedCargue = cargueRepository.save(existingCargue);
            return ResponseEntity.ok(updatedCargue);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCargue(@PathVariable CargueId id) {
        Optional<Cargue> cargueOptional = cargueRepository.findById(id);

        if (cargueOptional.isPresent()) {
            cargueRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

