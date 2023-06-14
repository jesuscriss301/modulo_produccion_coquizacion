package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.CantidadAsignacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CantidadAsignacionRepository extends JpaRepository<CantidadAsignacion, Integer> {
    List<CantidadAsignacion> findByIdAsignacionAndIdTipoAsignacion(Integer idAsignacion, Integer idTipoAsignacion);

    @Override
    Optional<CantidadAsignacion> findById(Integer integer);
}