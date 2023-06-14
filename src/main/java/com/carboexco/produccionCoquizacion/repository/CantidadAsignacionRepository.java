package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.CantidadAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CantidadAsignacionRepository extends JpaRepository<CantidadAsignacion, CantidadAsignacionId> {
}