package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.ProcesoCoquizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ProcesoCoquizacionRepository extends JpaRepository<ProcesoCoquizacion, Integer> {
    @Query("select count(p) from ProcesoCoquizacion p where p.fecha = ?1")
    long countByFecha(LocalDateTime fecha);


}