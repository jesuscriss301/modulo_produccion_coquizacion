package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "proceso_control")
public class ProcesoControl implements Serializable {
    private static final long serialVersionUID = -2196723568624175862L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "id_proceso", nullable = false)
    private Integer idProceso;

    @Column(name = "id_control_pila", nullable = false)
    private Integer idControlPila;

    public ProcesoControl(int idProceso, int idControlPila) {
        this.idProceso=idProceso;
        this.idControlPila=idControlPila;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProcesoControl entity = (ProcesoControl) o;
        return Objects.equals(this.idProceso, entity.idProceso) &&
                Objects.equals(this.idControlPila, entity.idControlPila);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProceso, idControlPila);
    }

}