package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "cargue")
public class Cargue {
    @EmbeddedId
    private CargueId id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_proceso", nullable = false)
    private ProcesoCoquizacion idProceso;


    @Column(name = "id_control_pila", nullable = false)
    private Integer idControlPila;

    @Column(name = "hora_inicio")
    private Instant horaInicio;

    @Column(name = "hora_final")
    private Instant horaFinal;

    @Column(name = "ton_carbon")
    private Float tonCarbon;

    @Column(name = "altura_boquilla")
    private Float alturaBoquilla;

    @Column(name = "tamanio_boquilla")
    private Float tamanioBoquilla;

    @Column(name = "altura_apisonado")
    private Float alturaApisonado;

    @Column(name = "temperatura_inicial")
    private Integer temperaturaInicial;

    @Column(name = "temperatura_final")
    private Integer temperaturaFinal;

    @Column(name = "hora_encendido")
    private Instant horaEncendido;

}