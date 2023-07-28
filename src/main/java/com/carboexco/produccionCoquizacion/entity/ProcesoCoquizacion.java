package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "proceso_coquizacion")
public class ProcesoCoquizacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_poceso_coquizacion", nullable = false)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_id_tipo_proceso", nullable = false)
    private TipoProceso idIdTipoProceso;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

}