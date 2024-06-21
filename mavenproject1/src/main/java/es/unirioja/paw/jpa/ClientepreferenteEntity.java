package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "clientepreferente")
public class ClientepreferenteEntity {

    @Basic
    @Id
    @Column(name = "codigoCliente", nullable = false, length = 10)
    private String codigoCliente;

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    @Basic
    @Column(name = "descuento", nullable = false, precision = 2)
    private BigDecimal descuento;

    public BigDecimal getDescuento() {
        return descuento;
    }

    public void setDescuento(BigDecimal descuento) {
        this.descuento = descuento;
    }

    @Basic
    @Column(name = "incrFact", nullable = true, precision = 2)
    private BigDecimal incrFact;

    public BigDecimal getIncrFact() {
        return incrFact;
    }

    public void setIncrFact(BigDecimal incrFact) {
        this.incrFact = incrFact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ClientepreferenteEntity that = (ClientepreferenteEntity) o;

        if (codigoCliente != null ? !codigoCliente.equals(that.codigoCliente) : that.codigoCliente != null) {
            return false;
        }
        if (descuento != null ? !descuento.equals(that.descuento) : that.descuento != null) {
            return false;
        }
        if (incrFact != null ? !incrFact.equals(that.incrFact) : that.incrFact != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigoCliente != null ? codigoCliente.hashCode() : 0;
        result = 31 * result + (descuento != null ? descuento.hashCode() : 0);
        result = 31 * result + (incrFact != null ? incrFact.hashCode() : 0);
        return result;
    }
}
