package es.unirioja.paw.model;

import java.io.Serializable;

public class TipoArticulo implements Serializable {

    private String nombre;

    public TipoArticulo() {
    }

    public TipoArticulo(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
