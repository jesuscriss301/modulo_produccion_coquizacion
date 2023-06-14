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
@Table(name = "cantidad_asignacion")
public class CantidadAsignacion implements Serializable {
    private static final long serialVersionUID = 2938135034729026791L;

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "id_asignacion", nullable = false)
    private Integer idAsignacion;

    @Column(name = "id_tipo_asignacion", nullable = false)
    private Integer idTipoAsignacion;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CantidadAsignacion entity = (CantidadAsignacion) o;
        return Objects.equals(this.idAsignacion, entity.idAsignacion) &&
                Objects.equals(this.idTipoAsignacion, entity.idTipoAsignacion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAsignacion, idTipoAsignacion);
    }

}