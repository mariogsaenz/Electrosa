package es.unirioja.paw.jpa;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "numdoc")
public class NumdocEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ANIO", nullable = false)
    private int anio;

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    @Basic
    @Column(name = "NUM", nullable = false)
    private int num;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        NumdocEntity that = (NumdocEntity) o;

        if (anio != that.anio) {
            return false;
        }
        if (num != that.num) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int result = anio;
        result = 31 * result + num;
        return result;
    }
}
