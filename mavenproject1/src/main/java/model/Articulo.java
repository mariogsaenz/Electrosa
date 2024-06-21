package es.unirioja.paw.model;

import java.io.Serializable;

public class Articulo implements Serializable {
    private String codigo;
    private String nombre;
    private double pvp;
    private String tipo;
    private String fabricante;
    private String foto;
    private String descripcion;

    public Articulo() {
    }


    /**
     * Construye un objeto artículo a partir de su código, nombre, pvp, tipo,
     * fabricante, fichero que contiene la foto y descripción
     *
     * @param codigo
     * @param nombre
     * @param pvp
     * @param tipo
     * @param fabricante
     * @param foto
     * @param descripcion
     */
    public Articulo(String codigo, String nombre, double pvp, String tipo, String fabricante, String foto, String descripcion) {
//    Debug.prec(codigo, "Código de artículo nulo");
//    Debug.prec(nombre, "Nombre de artículo nulo");
//    Debug.prec(pvp>=0, "El precio de un artículo no puede ser negativo");
//    Debug.prec(tipo, "tipo de artículo nulo");
//    Debug.prec(fabricante, "fabricante de artículo nulo");
        this.codigo = codigo;
        this.nombre = nombre;
        this.pvp = pvp;
        this.tipo = tipo;
        this.fabricante = fabricante;
        this.foto = foto;
        this.descripcion = descripcion;
    }

    /**
     * Devuelve el código del artículo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * Devuelve el nombre (descripción) del artículo.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Devuelve el precio de venta al púlico del artículo, precio base sobre el
     * que se aplicarán descuentos.
     */
    public double getPvp() {
        return pvp;
    }

    /**
     * Get the value of tipo
     *
     * @return the value of tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Get the value of fabricante
     *
     * @return the value of fabricante
     */
    public String getFabricante() {
        return fabricante;
    }

    /**
     * Get the value of foto
     *
     * @return the value of foto
     */
    public String getFoto() {
        return foto;
    }

    /**
     * Get the value of descripcion
     *
     * @return the value of descripcion
     */
    public String getDescripcion() {
        return descripcion;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPvp(double pvp) {
        this.pvp = pvp;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    /**
     * Compara el artículo actual con otro entregado como parámetro.
     *
     * @param art artículo con el que comparar
     * @return true si todos los campos del artículo actual coinciden con los del
     * otro (en la comparación del nombre no se tienen en cuenta las
     * mayúsculas)
     */
    public boolean equals(Object art) {
        if (!(art instanceof Articulo)) {
            throw new IllegalArgumentException("Esperaba un objeto de tipo Articulo");
        }
        Articulo arti = (Articulo) art;
        return arti.getNombre().equalsIgnoreCase(this.nombre) && arti.getPvp() == this.pvp
                && arti.getCodigo().equals(this.codigo);
    }

    /**
     * @return formato codigo / nombre (pvp €)
     */
    public String toString() {
        return codigo + " / " + nombre + " (" + pvp + " €). " + tipo + ". " + fabricante + ". " + foto;
    }

}