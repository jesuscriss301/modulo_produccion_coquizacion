package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tipo_asignacion")
public class TipoAsignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_asignacion", nullable = false)
    private Integer id;

    @Column(name = "tipo_asignacion", nullable = false, length = 50)
    private String tipoAsignacion;

}