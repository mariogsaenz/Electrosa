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
import java.util.Objects;

@Entity
@Table(name = "lineaanulada")
public class LineaanuladaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @ManyToOne
    @JoinColumn(name = "CODIGOARTICULO")
    private ArticuloEntity articulo;

    @ManyToOne
    @JoinColumn(name = "CODIGOPEDIDO")
    private PedidoanuladoEntity pedido;

    @Basic
    @Column(name = "CANTIDAD", nullable = false)
    private int cantidad;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public ArticuloEntity getArticulo() {
        return articulo;
    }

    public void setArticulo(ArticuloEntity articulo) {
        this.articulo = articulo;
    }

    public PedidoanuladoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoanuladoEntity pedido) {
        this.pedido = pedido;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + Objects.hashCode(this.codigo);
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
        final LineaanuladaEntity other = (LineaanuladaEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

}
