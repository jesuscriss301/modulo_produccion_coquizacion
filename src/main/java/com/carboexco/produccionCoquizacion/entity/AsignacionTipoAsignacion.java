package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "asignacion_tipo_asignacion")
public class AsignacionTipoAsignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_tipo_asignacion", nullable = false)
    private TipoAsignacion idTipoAsignacion;

    @Column(name = "id_proceso", nullable = false)
    private Integer idProceso;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_asignado", nullable = false)
    private Asignacion idAsignado;

}