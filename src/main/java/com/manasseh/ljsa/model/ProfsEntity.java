package com.manasseh.ljsa.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "profs", schema = "ljsa")
public class ProfsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "n_matricule")
    private String nMatricule;
    @Basic
    @Column(name = "nom_prof")
    private String nomProf;
    @Basic
    @Column(name = "prenom_prof")
    private String prenomProf;
    @Basic
    @Column(name = "date_nais")
    private LocalDate dateNais;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getnMatricule() {
        return nMatricule;
    }

    public void setnMatricule(String nMatricule) {
        this.nMatricule = nMatricule;
    }

    public String getNomProf() {
        return nomProf;
    }

    public void setNomProf(String nomProf) {
        this.nomProf = nomProf;
    }

    public String getPrenomProf() {
        return prenomProf;
    }

    public void setPrenomProf(String prenomProf) {
        this.prenomProf = prenomProf;
    }

    public LocalDate getDateNais() {
        return dateNais;
    }

    public void setDateNais(LocalDate dateNais) {
        this.dateNais = dateNais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProfsEntity that = (ProfsEntity) o;
        return id == that.id && Objects.equals(nMatricule, that.nMatricule) && Objects.equals(nomProf, that.nomProf) && Objects.equals(prenomProf, that.prenomProf) && Objects.equals(dateNais, that.dateNais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nMatricule, nomProf, prenomProf, dateNais);
    }
}
