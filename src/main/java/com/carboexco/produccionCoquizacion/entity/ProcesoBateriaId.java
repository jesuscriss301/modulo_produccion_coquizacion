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
public class ProcesoBateriaId implements Serializable {
    private static final long serialVersionUID = 1991692299509839360L;
    @Column(name = "id_proceso", nullable = false)
    private Integer idProceso;

    @Column(name = "id_horno", nullable = false)
    private Integer idHorno;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProcesoBateriaId entity = (ProcesoBateriaId) o;
        return Objects.equals(this.idProceso, entity.idProceso) &&
                Objects.equals(this.idHorno, entity.idHorno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProceso, idHorno);
    }

}