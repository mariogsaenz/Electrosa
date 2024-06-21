package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.sql.Date;

@Entity
@Table(name = "reposicion")
public class ReposicionEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODIGO", nullable = false, length = 10)
    private String codigo;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    @Basic
    @Column(name = "CODIGOARTICULO", nullable = false, length = 10)
    private String codigoarticulo;

    public String getCodigoarticulo() {
        return codigoarticulo;
    }

    public void setCodigoarticulo(String codigoarticulo) {
        this.codigoarticulo = codigoarticulo;
    }

    @Basic
    @Column(name = "CODIGOALMACEN", nullable = false, length = 10)
    private String codigoalmacen;

    public String getCodigoalmacen() {
        return codigoalmacen;
    }

    public void setCodigoalmacen(String codigoalmacen) {
        this.codigoalmacen = codigoalmacen;
    }

    @Basic
    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad;

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Basic
    @Column(name = "FECHAENTRADA", nullable = false)
    private Date fechaentrada;

    public Date getFechaentrada() {
        return fechaentrada;
    }

    public void setFechaentrada(Date fechaentrada) {
        this.fechaentrada = fechaentrada;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ReposicionEntity that = (ReposicionEntity) o;

        if (cantidad != that.cantidad) {
            return false;
        }
        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) {
            return false;
        }
        if (codigoarticulo != null ? !codigoarticulo.equals(that.codigoarticulo) : that.codigoarticulo != null) {
            return false;
        }
        if (codigoalmacen != null ? !codigoalmacen.equals(that.codigoalmacen) : that.codigoalmacen != null) {
            return false;
        }
        if (fechaentrada != null ? !fechaentrada.equals(that.fechaentrada) : that.fechaentrada != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (codigoarticulo != null ? codigoarticulo.hashCode() : 0);
        result = 31 * result + (codigoalmacen != null ? codigoalmacen.hashCode() : 0);
        result = 31 * result + cantidad;
        result = 31 * result + (fechaentrada != null ? fechaentrada.hashCode() : 0);
        return result;
    }
}
