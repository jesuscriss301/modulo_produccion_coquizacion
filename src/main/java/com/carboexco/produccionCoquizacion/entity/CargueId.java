package com.carboexco.produccionCoquizacion.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@Embeddable
public class CargueId implements Serializable {
    private static final long serialVersionUID = -7931199127975659174L;
    @Column(name = "id_cargue", nullable = false)
    private Integer idCargue;

    @Column(name = "id_usuario", nullable = false)
    private Integer idUsuario;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CargueId entity = (CargueId) o;
        return Objects.equals(this.idCargue, entity.idCargue) &&
                Objects.equals(this.idUsuario, entity.idUsuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCargue, idUsuario);
    }

}