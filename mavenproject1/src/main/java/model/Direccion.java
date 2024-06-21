package es.unirioja.paw.model;

import java.io.Serializable;

public class Direccion implements Serializable {

    private String calle;
    private String ciudad;
    private String provincia;
    private String cp;

    public Direccion() {
    }

    public Direccion(String calle, String ciudad, String provincia, String cp) {
        this.calle = calle;
        this.ciudad = ciudad;
        this.provincia = provincia;
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

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

}
