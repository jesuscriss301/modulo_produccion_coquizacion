package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "propiedad_coque")
public class PropiedadCoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_propiedad", nullable = false)
    private Integer id;

    @Column(name = "compactacion", nullable = false, length = 20)
    private String compactacion;

    @Column(name = "dureza", nullable = false, length = 20)
    private String dureza;

    @Column(name = "color_coque", nullable = false, length = 20)
    private String colorCoque;

}