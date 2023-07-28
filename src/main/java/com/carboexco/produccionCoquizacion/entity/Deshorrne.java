package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "deshorrne")
public class Deshorrne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_deshorne", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proceso", nullable = false)
    private ProcesoCoquizacion idProceso;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_propiedades")
    private PropiedadCoque idPropiedades;

    @Column(name = "fecha_hora_inicio")
    private LocalDateTime fechaHoraInicio;

    @Column(name = "fecha_hora_final")
    private LocalDateTime fechaHoraFinal;

    @Column(name = "tonelaje_coque")
    private Float tonelajeCoque;

}