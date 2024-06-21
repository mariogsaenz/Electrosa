package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "cliente")
public class ClienteEntity {

    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    @Column(name = "CODIGO", nullable = false, length = 36)
    private String codigo;

    @Basic
    @Column(name = "CIF", nullable = false, length = 12)
    private String cif;

    @Basic
    @Column(name = "NOMBRE", nullable = false, length = 50)
    private String nombre;

    @Basic
    @Column(name = "CALLE", nullable = false, length = 50)
    private String calle;

    @Basic
    @Column(name = "CP", nullable = false, length = 5)
    private String cp;

    @Basic
    @Column(name = "CIUDAD", nullable = false, length = 20)
    private String ciudad;

    @Basic
    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Basic
    @Column(name = "provincia", nullable = false, length = 35)
    private String provincia;

    @Basic
    @Column(name = "tfno", nullable = true, length = 11)
    private String tfno;

    @Basic
    @Column(name = "username", nullable = false, length = 50)
    private String username;

    @Basic
    @Column(name = "puntos_fidelidad", nullable = true)
    private Integer puntosFidelidad;

    @Basic
    @Column(name = "sexo", nullable = true, length = 10)
    private String sexo;

    @Basic
    @Column(name = "gravatar_hashid", nullable = true, length = 200)
    private String gravatarHashId;

    @Basic
    @Column(name = "avatar_imagen", nullable = true, length = 200)
    private String imagenAvatar;

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
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
        final ClienteEntity other = (ClienteEntity) obj;
        return Objects.equals(this.codigo, other.codigo);
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Integer getPuntosFidelidad() {
        return puntosFidelidad;
    }

    public void setPuntosFidelidad(Integer puntosFidelidad) {
        this.puntosFidelidad = puntosFidelidad;
    }

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getTfno() {
        return tfno;
    }

    public void setTfno(String tfno) {
        this.tfno = tfno;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getImagenAvatar() {
        return imagenAvatar;
    }

    public void setImagenAvatar(String imagenAvatar) {
        this.imagenAvatar = imagenAvatar;
    }

    public String getGravatarHashId() {
        return gravatarHashId;
    }

    public void setGravatarHashId(String gravatarHashId) {
        this.gravatarHashId = gravatarHashId;
    }

}
