package com.manasseh.ljsa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "matiere", schema = "ljsa")
public class MatiereEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "designation")
    private String designation;
    @Basic
    @Column(name = "abreviation")
    private String abreviation;
    @Basic
    @Column(name = "description")
    private String description;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getAbreviation() {
        return abreviation;
    }

    public void setAbreviation(String abreviation) {
        this.abreviation = abreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatiereEntity that = (MatiereEntity) o;
        return id == that.id && Objects.equals(designation, that.designation) && Objects.equals(abreviation, that.abreviation) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, designation, abreviation, description);
    }
}
