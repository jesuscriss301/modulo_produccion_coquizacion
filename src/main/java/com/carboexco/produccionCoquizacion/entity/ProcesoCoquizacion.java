package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "proceso_coquizacion")
public class ProcesoCoquizacion {


    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_poceso_coquizacion", nullable = false, length = 11)
    private String idPocesoCoquizacion;

    @ManyToOne()
    @JoinColumn(name = "id_tipo_proceso", nullable = false)
    private TipoProceso idTipoProceso;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

}