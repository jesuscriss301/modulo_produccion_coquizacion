package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.CantidadAsignacion;
import com.carboexco.produccionCoquizacion.entity.CantidadAsignacionId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CantidadAsignacionRepository extends JpaRepository<CantidadAsignacion, CantidadAsignacionId> {
}