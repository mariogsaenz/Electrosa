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
import java.util.Optional;

@Entity
@Table(name = "pedidoenrealizacion")
public class CestaCompraEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @Basic
    @Column(name = "FECHAINICIO", nullable = false)
    private Timestamp fechaInicio;

    @Basic
    @Column(name = "CODIGOCLIENTE", nullable = false, length = 10)
    private String codigoCliente;

    @OneToMany(mappedBy = "cesta", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true) // TODO
    private List<LineaCestaCompraEntity> lineas;

    public Optional<LineaCestaCompraEntity> findLineaArticulo(String codigoArticulo) {
        if (lineas == null || lineas.isEmpty()) {
            return Optional.empty();
        }
        for (var linea : lineas) {
            if (codigoArticulo.equals(linea.getArticulo().getCodigo())) {
                return Optional.of(linea);
            }
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CestaCompraEntity that = (CestaCompraEntity) o;

        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) {
            return false;
        }
        if (fechaInicio != null ? !fechaInicio.equals(that.fechaInicio) : that.fechaInicio != null) {
            return false;
        }
        if (codigoCliente != null ? !codigoCliente.equals(that.codigoCliente) : that.codigoCliente != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (fechaInicio != null ? fechaInicio.hashCode() : 0);
        result = 31 * result + (codigoCliente != null ? codigoCliente.hashCode() : 0);
        return result;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public List<LineaCestaCompraEntity> getLineas() {
        return lineas;
    }

    public void setLineas(List<LineaCestaCompraEntity> lineas) {
        this.lineas = lineas;
    }

}
