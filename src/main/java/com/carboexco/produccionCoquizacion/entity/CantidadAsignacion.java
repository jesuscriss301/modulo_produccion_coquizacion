package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cantidad_asignacion")
public class CantidadAsignacion {
    @EmbeddedId
    private CantidadAsignacionId id;

    //TODO [JPA Buddy] generate columns from DB
}