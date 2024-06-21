package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class DireccionEntity {

    @Basic
    @Column(name = "CP", nullable = false, length = 5)
    private String cp;

    @Basic
    @Column(name = "CALLE", nullable = false, length = 50)
    private String calle;

    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 20)
    private String ciudad;

    @Basic
    @Column(name = "PROVINCIA", nullable = false, length = 35)
    private String provincia;

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

}
