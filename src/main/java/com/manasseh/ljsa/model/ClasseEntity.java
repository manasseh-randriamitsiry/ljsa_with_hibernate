package com.manasseh.ljsa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "coefficient", schema = "ljsa", catalog = "")
public class ClasseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "classe")
    private String classe;
    @Basic
    @Column(name = "coefficient_total")
    private int coefficientTotal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Integer getCoefficientTotal() {
        return coefficientTotal;
    }

    public void setCoefficientTotal(int coefficientTotal) {
        this.coefficientTotal = coefficientTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClasseEntity that = (ClasseEntity) o;
        return id == that.id && coefficientTotal == that.coefficientTotal && Objects.equals(classe, that.classe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, classe, coefficientTotal);
    }
}
