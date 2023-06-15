package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.ProcesoControl;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProcesoControlRepository extends JpaRepository<ProcesoControl, Long> {
    Optional<ProcesoControl> findFirstByIdProcesoAndIdControlPilaOrderByIdProcesoAsc(Integer idProceso, Integer idControlPila);

    void deleteByIdProcesoAndIdControlPila(Integer idProceso, Integer idControlPila);




}