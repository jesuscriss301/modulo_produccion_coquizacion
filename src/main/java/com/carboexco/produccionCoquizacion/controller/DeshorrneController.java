package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Deshorrne;
import com.carboexco.produccionCoquizacion.repository.DeshorrneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/deshorrnes")
public class DeshorrneController {

    @Autowired
    DeshorrneRepository deshorrneRepository;

    @GetMapping
    public List<Deshorrne> getDeshorrneAll() {
        return deshorrneRepository.findAll();
    }

    @GetMapping("/{id}")
    public Deshorrne getDeshorrneById(@PathVariable int id) {
        Optional<Deshorrne> deshorrne = deshorrneRepository.findById(id);

        if (deshorrne.isPresent()) {
            return deshorrne.get();
        }

        return null;
    }

    @PostMapping
    public Deshorrne postDeshorrne(@RequestBody Deshorrne deshorrne) {
        deshorrneRepository.save(deshorrne);
        return deshorrne;
    }

    @PutMapping("/{id}")
    public Deshorrne putDeshorrneById(@PathVariable int id, @RequestBody Deshorrne deshorrne) {
        Optional<Deshorrne> deshorrneCurrent = deshorrneRepository.findById(id);

        if (deshorrneCurrent.isPresent()) {
            Deshorrne deshorrneReturn = deshorrneCurrent.get();

            deshorrneReturn.setIdAsignacion(deshorrne.getIdAsignacion());
            deshorrneReturn.setIdProceso(deshorrne.getIdProceso());
            deshorrneReturn.setIdPropiedades(deshorrne.getIdPropiedades());
            deshorrneReturn.setFechaHoraInicio(deshorrne.getFechaHoraInicio());
            deshorrneReturn.setFechaHoraFinal(deshorrne.getFechaHoraFinal());
            deshorrneReturn.setTonelajeCoque(deshorrne.getTonelajeCoque());

            deshorrneRepository.save(deshorrneReturn);
            return deshorrneReturn;
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Deshorrne deleteDeshorrneById(@PathVariable int id) {
        Optional<Deshorrne> deshorrne = deshorrneRepository.findById(id);

        if (deshorrne.isPresent()) {
            Deshorrne deshorrneReturn = deshorrne.get();
            deshorrneRepository.deleteById(id);
            return deshorrneReturn;
        }

        return null;
    }
}
