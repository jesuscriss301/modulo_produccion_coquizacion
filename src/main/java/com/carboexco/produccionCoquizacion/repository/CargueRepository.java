package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.Cargue;
import com.carboexco.produccionCoquizacion.entity.CargueId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CargueRepository extends JpaRepository<Cargue, CargueId> {
}