package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "proceso_bateria")
public class ProcesoBateria {
    @EmbeddedId
    private ProcesoBateriaId id;

    @Column(name = "tiempo_coquizacion", nullable = false)
    private Integer tiempoCoquizacion;

}