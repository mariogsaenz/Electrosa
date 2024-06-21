package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.math.BigDecimal;
import java.util.Random;

@Entity
@Table(name = "articulo")
public class ArticuloEntity {

    @Transient
    private Integer stock;

    @Transient
    private Integer rating;

    public ArticuloEntity() {
        this.stock = randomInteger(0, 100);
        this.rating = randomInteger(1, 5);
    }

    private int randomInteger(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "CODIGO", nullable = false, length = 10)
    private String codigo;

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Basic
    @Column(name = "PVP", nullable = false, precision = 2)
    private BigDecimal pvp;

    @Basic
    @Column(name = "TIPO", nullable = false, length = 15)
    private String tipo;

    @Basic
    @Column(name = "FABRICANTE", nullable = false, length = 15)
    private String fabricante;

    @Basic
    @Column(name = "foto", nullable = true, length = 50)
    private String foto;

    @Basic
    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArticuloEntity that = (ArticuloEntity) o;

        if (codigo != null ? !codigo.equals(that.codigo) : that.codigo != null) {
            return false;
        }
        if (nombre != null ? !nombre.equals(that.nombre) : that.nombre != null) {
            return false;
        }
        if (pvp != null ? !pvp.equals(that.pvp) : that.pvp != null) {
            return false;
        }
        if (tipo != null ? !tipo.equals(that.tipo) : that.tipo != null) {
            return false;
        }
        if (fabricante != null ? !fabricante.equals(that.fabricante) : that.fabricante != null) {
            return false;
        }
        if (foto != null ? !foto.equals(that.foto) : that.foto != null) {
            return false;
        }
        if (descripcion != null ? !descripcion.equals(that.descripcion) : that.descripcion != null) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = codigo != null ? codigo.hashCode() : 0;
        result = 31 * result + (nombre != null ? nombre.hashCode() : 0);
        result = 31 * result + (pvp != null ? pvp.hashCode() : 0);
        result = 31 * result + (tipo != null ? tipo.hashCode() : 0);
        result = 31 * result + (fabricante != null ? fabricante.hashCode() : 0);
        result = 31 * result + (foto != null ? foto.hashCode() : 0);
        result = 31 * result + (descripcion != null ? descripcion.hashCode() : 0);
        return result;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public BigDecimal getPvp() {
        return pvp;
    }

    public void setPvp(BigDecimal pvp) {
        this.pvp = pvp;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
