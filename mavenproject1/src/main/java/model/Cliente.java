package es.unirioja.paw.model;

import java.io.Serializable;

public class Cliente implements Serializable {

    private String codigo;
    private String username;
    private String nombre;
    private String cif;
    private Direccion direccion;
    private String email;
    private String telefono;

    public Cliente() {
    }

    public Cliente(String codigo, String username, String nombre, String cif, Direccion direccion, String email, String telefono) {
        this.codigo = codigo;
        this.username = username;
        this.nombre = nombre;
        this.cif = cif;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

}
