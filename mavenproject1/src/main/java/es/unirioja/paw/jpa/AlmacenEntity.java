package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "almacen")
public class AlmacenEntity {

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
    @Column(name = "CALLE", nullable = false, length = 50)
    private String calle;

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 20)
    private String ciudad;

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Basic
    @Column(name = "CP", nullable = false, length = 5)
    private String cp;

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    @Basic
    @Column(name = "PROVINCIA", nullable = false, length = 35)
    private String provincia;

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Basic
    @Column(name = "Zona", nullable = false)
    private int zona;

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    @Basic
    @Column(name = "coordX", nullable = false, precision = 6)
    private BigDecimal coordX;

    public BigDecimal getCoordX() {
        return coordX;
    }

    public void setCoordX(BigDecimal coordX) {
        this.coordX = coordX;
    }

    @Basic
    @Column(name = "coordY", nullable = false, precision = 6)
    private BigDecimal coordY;

    public BigDecimal getCoordY() {
        return coordY;
    }

    public void setCoordY(BigDecimal coordY) {
        this.coordY = coordY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AlmacenEntity that = (AlmacenEntity) o;

        if (zona != that.zona) {
            return false;
        }
        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) {
            return false;
        }
        if (calle != null ? !calle.equals(that.calle) : that.calle != null) {
            return false;
        }
        if (ciudad != null ? !ciudad.equals(that.ciudad) : that.ciudad != null) {
            return false;
        }
        if (cp != null ? !cp.equals(that.cp) : that.cp != null) {
            return false;
        }
        if (provincia != null ? !provincia.equals(that.provincia) : that.provincia != null) {
            return false;
        }
        if (coordX != null ? !coordX.equals(that.coordX) : that.coordX != null) {
            return false;
        }
        if (coordY != null ? !coordY.equals(that.coordY) : that.coordY != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (calle != null ? calle.hashCode() : 0);
        result = 31 * result + (ciudad != null ? ciudad.hashCode() : 0);
        result = 31 * result + (cp != null ? cp.hashCode() : 0);
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        result = 31 * result + zona;
        result = 31 * result + (coordX != null ? coordX.hashCode() : 0);
        result = 31 * result + (coordY != null ? coordY.hashCode() : 0);
        return result;
    }
}
