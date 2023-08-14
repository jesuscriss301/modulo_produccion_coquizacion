package com.carboexco.produccionCoquizacion.controller;

import com.carboexco.produccionCoquizacion.entity.ProcesoBateria;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateriaId;
import com.carboexco.produccionCoquizacion.repository.ProcesoBateriaRepository;
import com.carboexco.produccionCoquizacion.security.TokenValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/procesos-bateria")
public class ProcesoBateriaController {

    @Autowired
    private ProcesoBateriaRepository procesoBateriaRepository;
    private final TokenValidationService authorizador = new TokenValidationService("");

    @GetMapping
    public ResponseEntity<?> getProcesoBateriaAll(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<ProcesoBateria> procesoBateriaList = procesoBateriaRepository.findAll();
            return ResponseEntity.ok(procesoBateriaList);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/{idBateria}/{idProceso}")
    public ResponseEntity<?> getProcesoBateriaById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idBateria, @PathVariable String idProceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
            Optional<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById(procesoBateriaId);

            if (procesoBateria.isPresent()) {
                return ResponseEntity.ok(procesoBateria.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
    @GetMapping("/{fecha}")
    public ResponseEntity<?> getProcesoBateriaByFecha(@RequestHeader("Authorization") String bearerToken, @PathVariable String fecha) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            List<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById_IdProcesoStartsWith(fecha);
            return ResponseEntity.ok(procesoBateria);

        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @GetMapping("/calendar")
    public ResponseEntity<?> getProcesoBateriaCalendar(@RequestHeader("Authorization") String bearerToken) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {

            List<List<Object>> matriz = new ArrayList<>();
            int añoActual = LocalDate.now().getYear();

            for (int diaDelAño = 1; diaDelAño <= 365; diaDelAño++) {
                LocalDate fechaDia = LocalDate.ofYearDay(añoActual, diaDelAño);
                String idProceso = fechaDia.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
                long cantidadHornos = procesoBateriaRepository.countById_IdProcesoStartsWith(idProceso);

                if (cantidadHornos > 0) {
                    List<Object> fila = new ArrayList<>(); // Fila para cada día
                    fila.add(fechaDia);
                    fila.add(cantidadHornos);
                    matriz.add(fila); // Agregar la fila a la matriz si la cantidad de hornos es mayor que cero
                }
            }
        return ResponseEntity.ok(matriz);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PostMapping
    public ResponseEntity<?> postProcesoBateria(@RequestHeader("Authorization") String bearerToken, @RequestBody ProcesoBateria procesoBateria) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            procesoBateriaRepository.save(procesoBateria);
            return ResponseEntity.status(HttpStatus.CREATED).body(procesoBateria);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }


    @PostMapping("/")
    public ResponseEntity<?> postListProcesoBateria(@RequestHeader("Authorization") String bearerToken, @RequestBody List<ProcesoBateria> procesoBateria) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            // El token de autorización es válido, guardar los datos en la base de datos
            List<ProcesoBateria> savedProcesoBateria = procesoBateriaRepository.saveAll(procesoBateria);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedProcesoBateria);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @PutMapping("/{idBateria}/{idProceso}")
    public ResponseEntity<?> putProcesoBateriaById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idBateria, @PathVariable String idProceso, @RequestBody ProcesoBateria procesoBateria) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
            Optional<ProcesoBateria> procesoBateriaCurrent = procesoBateriaRepository.findById(procesoBateriaId);

            if (procesoBateriaCurrent.isPresent()) {
                ProcesoBateria procesoBateriaReturn = procesoBateriaCurrent.get();
                procesoBateriaReturn.setTiempoCoquizacion(procesoBateria.getTiempoCoquizacion());

                procesoBateriaRepository.save(procesoBateriaReturn);
                return ResponseEntity.ok(procesoBateriaReturn);
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }

    @DeleteMapping("/{idBateria}/{idProceso}")
    public ResponseEntity<?> deleteProcesoBateriaById(@RequestHeader("Authorization") String bearerToken, @PathVariable String idBateria, @PathVariable String idProceso) {
        authorizador.setBearerToken(bearerToken);
        if (authorizador.callValidateTokenEndpoint().getStatusCodeValue() == 200) {
            ProcesoBateriaId procesoBateriaId = new ProcesoBateriaId(idBateria, idProceso);
            Optional<ProcesoBateria> procesoBateria = procesoBateriaRepository.findById(procesoBateriaId);

            if (procesoBateria.isPresent()) {
                procesoBateriaRepository.deleteById(procesoBateriaId);
                return ResponseEntity.ok(procesoBateria.get());
            } else {
                return ResponseEntity.notFound().build(); // ID no encontrado
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // Acceso no autorizado
        }
    }
}