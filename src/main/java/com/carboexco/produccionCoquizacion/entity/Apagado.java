package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "apagado")
public class Apagado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_apagado", nullable = false)
    private Integer id;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proceso", nullable = false)
    private ProcesoCoquizacion idProceso;

    @Column(name = "temperatura_inicial")
    private Integer temperaturaInicial;

    @Column(name = "temperatura_final")
    private Integer temperaturaFinal;

    @Column(name = "fecha_hora_inicio")
    private Instant fechaHoraInicio;

    @Column(name = "fecha_hora_final")
    private Instant fechaHoraFinal;

}