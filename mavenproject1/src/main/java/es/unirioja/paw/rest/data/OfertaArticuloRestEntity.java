/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package es.unirioja.paw.rest.data;

/**
 *
 * @author ASUS
 */
public class OfertaArticuloRestEntity {
    private String codigoArticulo;

    private Integer cantidadComprada;

    private Integer cantidadPagada;

    private Float precioOriginal;

    private Float precioOfertado;

    private Long vigenciaDias;
    private Long vigenciaHoras;
    private Long vigenciaMinutos;
    private Long vigenciaSegundos;

    public String getCodigoArticulo() {
        return codigoArticulo;
    }

    public void setCodigoArticulo(String codigoArticulo) {
        this.codigoArticulo = codigoArticulo;
    }

    public Integer getCantidadComprada() {
        return cantidadComprada;
    }

    public void setCantidadComprada(Integer cantidadComprada) {
        this.cantidadComprada = cantidadComprada;
    }

    public Integer getCantidadPagada() {
        return cantidadPagada;
    }

    public void setCantidadPagada(Integer cantidadPagada) {
        this.cantidadPagada = cantidadPagada;
    }

    public Float getPrecioOriginal() {
        return precioOriginal;
    }

    public void setPrecioOriginal(Float precioOriginal) {
        this.precioOriginal = precioOriginal;
    }

    public Float getPrecioOfertado() {
        return precioOfertado;
    }

    public void setPrecioOfertado(Float precioOfertado) {
        this.precioOfertado = precioOfertado;
    }

    public Long getVigenciaDias() {
        return vigenciaDias;
    }

    public void setVigenciaDias(Long vigenciaDias) {
        this.vigenciaDias = vigenciaDias;
    }

    public Long getVigenciaHoras() {
        return vigenciaHoras;
    }

    public void setVigenciaHoras(Long vigenciaHoras) {
        this.vigenciaHoras = vigenciaHoras;
    }

    public Long getVigenciaMinutos() {
        return vigenciaMinutos;
    }

    public void setVigenciaMinutos(Long vigenciaMinutos) {
        this.vigenciaMinutos = vigenciaMinutos;
    }

    public Long getVigenciaSegundos() {
        return vigenciaSegundos;
    }

    public void setVigenciaSegundos(Long vigenciaSegundos) {
        this.vigenciaSegundos = vigenciaSegundos;
    }
}
