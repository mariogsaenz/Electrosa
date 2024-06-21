package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "oferta")
public class OfertaEntity {

    @Basic
    @Id
    @Column(name = "codigoArticulo", nullable = false, length = 10)
    private String codigoArticulo;

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    @Basic
    @Column(name = "descuento", nullable = false, precision = 2)
    private BigDecimal descuento;

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Basic
    @Column(name = "fecha_inicio", nullable = false)
    private Date fechaInicio;

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Basic
    @Column(name = "fecha_fin", nullable = true)
    private Date fechaFin;

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @Basic
    @Column(name = "activa", nullable = true)
    private Byte activa;

    public Byte getActiva() {
        return activa;
    }

    public void setActiva(Byte activa) {
        this.activa = activa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        OfertaEntity that = (OfertaEntity) o;

        if (codigoArticulo != null ? !codigoArticulo.equals(that.codigoArticulo) : that.codigoArticulo != null) {
            return false;
        }
        if (descuento != null ? !descuento.equals(that.descuento) : that.descuento != null) {
            return false;
        }
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) {
            return false;
        }
        if (fechaFin != null ? !fechaFin.equals(that.fechaFin) : that.fechaFin != null) {
            return false;
        }
        if (activa != null ? !activa.equals(that.activa) : that.activa != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoArticulo != null ? codigoArticulo.hashCode() : 0;
        result = 31 * result + (descuento != null ? descuento.hashCode() : 0);
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (fechaFin != null ? fechaFin.hashCode() : 0);
        result = 31 * result + (activa != null ? activa.hashCode() : 0);
        return result;
    }
}
