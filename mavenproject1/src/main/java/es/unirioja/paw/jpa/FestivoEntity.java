package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Date;

@Entity
@Table(name = "festivo")
public class FestivoEntity {

    @Id
    @Basic
    @Column(name = "FECHA", nullable = false)
    private Date fecha;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        FestivoEntity that = (FestivoEntity) o;

        if (fecha != null ? !fecha.equals(that.fecha) : that.fecha != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return fecha != null ? fecha.hashCode() : 0;
    }
}
