package com.carboexco.produccionCoquizacion.repository;

import com.carboexco.produccionCoquizacion.entity.ProcesoBateria;
import com.carboexco.produccionCoquizacion.entity.ProcesoBateriaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProcesoBateriaRepository extends JpaRepository<ProcesoBateria, ProcesoBateriaId> {
    List<ProcesoBateria> findById_IdProceso(String idProceso);

    @Query("select p from ProcesoBateria p where p.id.idProceso like concat(?1, '%')")
    List<ProcesoBateria> findById_IdProcesoStartsWith(String idProceso);


}