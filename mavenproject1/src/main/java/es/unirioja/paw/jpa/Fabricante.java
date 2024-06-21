package es.unirioja.paw.jpa;

import java.io.Serializable;

public class Fabricante implements Serializable {

    private String nombre;

    public Fabricante() {
    }

    public Fabricante(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
