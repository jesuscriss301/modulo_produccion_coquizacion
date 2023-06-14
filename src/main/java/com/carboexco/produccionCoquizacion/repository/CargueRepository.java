package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.Cargue;
import com.carboexco.produccionCoquizacion.entity.CargueId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CargueRepository extends JpaRepository<Cargue, CargueId> {
    Optional<Cargue> findById_IdCargueAndId_IdUsuarioOrderByIdProceso_FechaDesc(Integer idCargue, Integer idUsuario);

    List<Cargue> findById_IdCargueOrderByIdProceso_FechaAsc(Integer idCargue);

    List<Cargue> findById_IdUsuarioOrderByIdProceso_FechaDesc(Integer idUsuario);


}