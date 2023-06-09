package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.ProcesoBateria;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateriaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesoBateriaRepository extends JpaRepository<ProcesoBateria, ProcesoBateriaId> {
}