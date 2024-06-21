package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "codigopostal")
public class CodigopostalEntity {

    @Basic
    @Column(name = "Zona", nullable = false)
    private int zona;

    public int getZona() {
        return zona;
    }

    public void setZona(int zona) {
        this.zona = zona;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "PROVINCIA", nullable = false, length = 35)
    private String provincia;

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    @Basic
    @Column(name = "CPMIN", nullable = false, length = 5)
    private String cpmin;

    public String getCpmin() {
        return cpmin;
    }

    public void setCpmin(String cpmin) {
        this.cpmin = cpmin;
    }

    @Basic
    @Column(name = "CPMAX", nullable = false, length = 5)
    private String cpmax;

    public String getCpmax() {
        return cpmax;
    }

    public void setCpmax(String cpmax) {
        this.cpmax = cpmax;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CodigopostalEntity that = (CodigopostalEntity) o;

        if (zona != that.zona) {
            return false;
        }
        if (provincia != null ? !provincia.equals(that.provincia) : that.provincia != null) {
            return false;
        }
        if (cpmin != null ? !cpmin.equals(that.cpmin) : that.cpmin != null) {
            return false;
        }
        if (cpmax != null ? !cpmax.equals(that.cpmax) : that.cpmax != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = zona;
        result = 31 * result + (provincia != null ? provincia.hashCode() : 0);
        result = 31 * result + (cpmin != null ? cpmin.hashCode() : 0);
        result = 31 * result + (cpmax != null ? cpmax.hashCode() : 0);
        return result;
    }
}
