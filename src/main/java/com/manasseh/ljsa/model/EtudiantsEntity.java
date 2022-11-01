package com.manasseh.ljsa.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Table(name = "etudiants", schema = "ljsa")
public class EtudiantsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "n_matricule")
    private String nMatricule;
    @Basic
    @Column(name = "nom")
    private String nom;
    @Basic
    @Column(name = "prenom")
    private String prenom;
    @Basic
    @Column(name = "classe")
    private String classe;
    @Basic
    @Column(name = "date_nais")
    private Date dateNais;

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

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Date getDateNais() {
        return dateNais;
    }

    public void setDateNais(Date dateNais) {
        this.dateNais = dateNais;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EtudiantsEntity that = (EtudiantsEntity) o;
        return id == that.id && Objects.equals(nMatricule, that.nMatricule) && Objects.equals(nom, that.nom) && Objects.equals(prenom, that.prenom) && Objects.equals(classe, that.classe) && Objects.equals(dateNais, that.dateNais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nMatricule, nom, prenom, classe, dateNais);
    }
}
