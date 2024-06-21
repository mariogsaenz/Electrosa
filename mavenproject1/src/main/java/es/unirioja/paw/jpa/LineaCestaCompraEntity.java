package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table(name = "lineaenrealizacion")
public class LineaCestaCompraEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @Basic
    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad;

    @Basic
    @Column(name = "PRECIO", nullable = false, precision = 2)
    private BigDecimal precio;

    @Basic
    @Column(name = "FECHAENTREGADESEADA", nullable = true)
    private Date fechaEntregaDeseada;

    @ManyToOne
    @JoinColumn(name = "CODIGOARTICULO")
    private ArticuloEntity articulo;

    @ManyToOne
    @JoinColumn(name = "CODIGOPEDIDO")
    private CestaCompraEntity cesta;

    public void incrementCantidad(int i) {
        this.cantidad++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        LineaCestaCompraEntity that = (LineaCestaCompraEntity) o;

        if (cantidad != that.cantidad) {
            return false;
        }
        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) {
            return false;
        }
        if (precio != null ? !precio.equals(that.precio) : that.precio != null) {
            return false;
        }
        if (fechaEntregaDeseada != null ? !fechaEntregaDeseada.equals(that.fechaEntregaDeseada) : that.fechaEntregaDeseada != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + cantidad;
        result = 31 * result + (precio != null ? precio.hashCode() : 0);
        result = 31 * result + (fechaEntregaDeseada != null ? fechaEntregaDeseada.hashCode() : 0);
        return result;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Date getFechaEntregaDeseada() {
        return fechaEntregaDeseada;
    }

    public void setFechaEntregaDeseada(Date fechaEntregaDeseada) {
        this.fechaEntregaDeseada = fechaEntregaDeseada;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public CestaCompraEntity getCesta() {
        return cesta;
    }

    public void setCesta(CestaCompraEntity cesta) {
        this.cesta = cesta;
    }

}
