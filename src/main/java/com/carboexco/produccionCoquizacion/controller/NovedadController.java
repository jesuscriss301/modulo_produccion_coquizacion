package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.Novedad;
import com.carboexco.produccionCoquizacion.repository.NovedadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/novedades")
public class NovedadController {

    @Autowired
    NovedadRepository novedadRepository;

    @GetMapping
    public List<Novedad> getNovedadAll() {
        return novedadRepository.findAll();
    }

    @GetMapping("/{id}")
    public Novedad getNovedadById(@PathVariable int id) {
        Optional<Novedad> novedad = novedadRepository.findById(id);

        if (novedad.isPresent()) {
            return novedad.get();
        }

        return null;
    }

    @PostMapping
    public Novedad postNovedad(@RequestBody Novedad novedad) {
        novedadRepository.save(novedad);
        return novedad;
    }

    @PutMapping("/{id}")
    public Novedad putNovedadById(@PathVariable int id, @RequestBody Novedad novedad) {
        Optional<Novedad> novedadCurrent = novedadRepository.findById(id);

        if (novedadCurrent.isPresent()) {
            Novedad novedadReturn = novedadCurrent.get();

            novedadReturn.setIdUsuario(novedad.getIdUsuario());
            novedadReturn.setIdProceso(novedad.getIdProceso());
            novedadReturn.setFechaHora(novedad.getFechaHora());
            novedadReturn.setObservacion(novedad.getObservacion());
            novedadReturn.setIdFotografia(novedad.getIdFotografia());

            novedadRepository.save(novedadReturn);
            return novedadReturn;
        }

        return null;
    }

    @DeleteMapping("/{id}")
    public Novedad deleteNovedadById(@PathVariable int id) {
        Optional<Novedad> novedad = novedadRepository.findById(id);

        if (novedad.isPresent()) {
            Novedad novedadReturn = novedad.get();
            novedadRepository.deleteById(id);
            return novedadReturn;
        }

        return null;
    }
}

