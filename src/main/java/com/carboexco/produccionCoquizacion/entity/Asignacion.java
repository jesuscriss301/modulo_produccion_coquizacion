package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "asignacion")
public class Asignacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_asignacion", nullable = false)
    private Integer id;

    @Column(name = "id_empleado", nullable = false, length = 7)
    private String idEmpleado;

    @Column(name = "id_horno", nullable = false)
    private Integer idHorno;

    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column(name = "cumplimiento_asignacion")
    private Boolean cumplimientoAsignacion;

    @Column(name = "observacion", length = 500)
    private String observacion;

    @Column(name = "cantidad", nullable = false)
    private Integer cantidad;

}