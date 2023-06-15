package com.carboexco.produccionCoquizacion.controller;


import com.carboexco.produccionCoquizacion.entity.PropiedadCoque;
import com.carboexco.produccionCoquizacion.repository.PropiedadCoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/propiedades")
public class PropiedadCoqueController {

    @Autowired
    private PropiedadCoqueRepository propiedadCoqueRepository;

    @GetMapping
    public List<PropiedadCoque> getAllPropiedades() {
        return propiedadCoqueRepository.findAll();
    }

    @GetMapping("/{id}")
    public PropiedadCoque getPropiedadById(@PathVariable int id) {
        Optional<PropiedadCoque> propiedad = propiedadCoqueRepository.findById(id);
        return propiedad.orElse(null);
    }

    @PostMapping
    public PropiedadCoque createPropiedad(@RequestBody PropiedadCoque propiedad) {
        return propiedadCoqueRepository.save(propiedad);
    }

    @PutMapping("/{id}")
    public PropiedadCoque updatePropiedad(@PathVariable int id, @RequestBody PropiedadCoque propiedad) {
        Optional<PropiedadCoque> propiedadCurrent = propiedadCoqueRepository.findById(id);

        if (propiedadCurrent.isPresent()) {
            PropiedadCoque propiedadToUpdate = propiedadCurrent.get();
            propiedadToUpdate.setCompactacion(propiedad.getCompactacion());
            propiedadToUpdate.setDureza(propiedad.getDureza());
            propiedadToUpdate.setColorCoque(propiedad.getColorCoque());

            return propiedadCoqueRepository.save(propiedadToUpdate);
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public PropiedadCoque deletePropiedad(@PathVariable int id) {
        Optional<PropiedadCoque> propiedad = propiedadCoqueRepository.findById(id);

        if (propiedad.isPresent()) {
            PropiedadCoque propiedadToDelete = propiedad.get();
            propiedadCoqueRepository.delete(propiedadToDelete);
            return propiedadToDelete;
        }

        return null;
    }
}

