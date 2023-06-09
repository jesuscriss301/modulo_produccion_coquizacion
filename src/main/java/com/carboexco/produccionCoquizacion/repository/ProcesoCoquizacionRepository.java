package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.ProcesoCoquizacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProcesoCoquizacionRepository extends JpaRepository<ProcesoCoquizacion, Integer> {
}