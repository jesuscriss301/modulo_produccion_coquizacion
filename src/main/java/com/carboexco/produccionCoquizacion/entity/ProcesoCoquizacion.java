package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "proceso_coquizacion")
public class ProcesoCoquizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poceso_coquizacion", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_id_tipo_proceso", nullable = false)
    private TipoProceso idIdTipoProceso;

    @Column(name = "id_producto", nullable = false)
    private Integer idProducto;

    @Column(name = "fecha", nullable = false)
    private Instant fecha;

}