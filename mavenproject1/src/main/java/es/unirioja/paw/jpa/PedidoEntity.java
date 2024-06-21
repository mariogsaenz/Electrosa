package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @Basic
    @Column(name = "CODIGOCLIENTE", nullable = false, length = 10)
    private String codigoCliente;

    @Basic
    @Column(name = "FECHACIERRE", nullable = false)
    private Date fechacierre;

    @Basic
    @Column(name = "CURSADO", nullable = true)
    private Integer cursado;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true) // TODO
    private List<LineaPedidoEntity> lineas;

    @Embedded
    private DireccionEntity direccion;

    @Transient
    private Double importe;

    @Transient
    private boolean completado; // TODO
 
    public boolean isCompletado() {
        if (cursado != null && cursado == 1) {
            return true;
        }
        return false;
    }

    public Double getImporte() {
        return importe;
    }

    public void setImporte(Double importe) {
        this.importe = importe;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public Date getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Date fechacierre) {
        this.fechacierre = fechacierre;
    }

    public Integer getCursado() {
        return cursado;
    }

    public void setCursado(Integer cursado) {
        this.cursado = cursado;
    }

    public List<LineaPedidoEntity> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaPedidoEntity> lineas) {
        this.lineas = lineas;
    }

    public DireccionEntity getDireccion() {
        return direccion;
    }

    public void setDireccion(DireccionEntity direccion) {
        this.direccion = direccion;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.codigo);
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
        final PedidoEntity other = (PedidoEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    public void buildImporte() {
        double d = 0.0D;
        if (lineas != null) {
            for (LineaPedidoEntity linea : lineas) {
                d += linea.getPrecioReal();
            }
        }
        this.importe = d;
    }
}
