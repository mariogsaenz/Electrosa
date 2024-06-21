package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pedidoanulado")
public class PedidoanuladoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @Basic
    @Column(name = "FECHACIERRE", nullable = false)
    private Timestamp fechacierre;

    @Basic
    @Column(name = "FECHAANULACION", nullable = false)
    private Timestamp fechaanulacion;

    @Basic
    @Column(name = "CODIGOCLIENTE", nullable = false, length = 10)
    private String codigoCliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, fetch = FetchType.EAGER) // TODO
    private List<LineaanuladaEntity> lineas;

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFechacierre() {
        return fechacierre;
    }

    public void setFechacierre(Timestamp fechacierre) {
        this.fechacierre = fechacierre;
    }

    public Timestamp getFechaanulacion() {
        return fechaanulacion;
    }

    public void setFechaanulacion(Timestamp fechaanulacion) {
        this.fechaanulacion = fechaanulacion;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<LineaanuladaEntity> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaanuladaEntity> lineas) {
        this.lineas = lineas;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.codigo);
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
        final PedidoanuladoEntity other = (PedidoanuladoEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

}
