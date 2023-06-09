package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.Novedad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NovedadRepository extends JpaRepository<Novedad, Integer> {
}