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
import java.util.Objects;

@Entity
@Table(name = "linea")
public class LineaPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @Basic
    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad;

    @Basic
    @Column(name = "PRECIOBASE", nullable = false, precision = 2)
    private BigDecimal precioBase;

    @Basic
    @Column(name = "FECHAENTREGADESEADA", nullable = true)
    private Date fechaEntregaDeseada;

    @Basic
    @Column(name = "FECHAENTREGAPREVISTA", nullable = true)
    private Date fechaEntregaPrevista;

    @Basic
    @Column(name = "FECHAENTREGAREAL", nullable = true)
    private Date fechaEntregaReal;

    @Basic
    @Column(name = "FECHARECEPCION", nullable = true)
    private Date fechaRecepcion;

    @Basic
    @Column(name = "PRECIOREAL", nullable = false, precision = 2)
    private Double precioReal;

    @ManyToOne
    @JoinColumn(name = "CODIGOARTICULO")
    private ArticuloEntity articulo;

    @ManyToOne
    @JoinColumn(name = "CODIGOPEDIDO")
    private PedidoEntity pedido;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineaPedidoEntity other = (LineaPedidoEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
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

    public BigDecimal getPrecioBase() {
        return precioBase;
    }

    public void setPrecioBase(BigDecimal precioBase) {
        this.precioBase = precioBase;
    }

    public Date getFechaEntregaDeseada() {
        return fechaEntregaDeseada;
    }

    public void setFechaEntregaDeseada(Date fechaEntregaDeseada) {
        this.fechaEntregaDeseada = fechaEntregaDeseada;
    }

    public Date getFechaEntregaPrevista() {
        return fechaEntregaPrevista;
    }

    public void setFechaEntregaPrevista(Date fechaEntregaPrevista) {
        this.fechaEntregaPrevista = fechaEntregaPrevista;
    }

    public Date getFechaEntregaReal() {
        return fechaEntregaReal;
    }

    public void setFechaEntregaReal(Date fechaEntregaReal) {
        this.fechaEntregaReal = fechaEntregaReal;
    }

    public Date getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(Date fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }

    public Double getPrecioReal() {
        return precioReal;
    }

    public void setPrecioReal(Double precioReal) {
        this.precioReal = precioReal;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }

}
