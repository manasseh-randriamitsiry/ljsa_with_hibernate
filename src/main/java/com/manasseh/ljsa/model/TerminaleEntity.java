package com.manasseh.ljsa.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "terminale", schema = "ljsa", catalog = "")
public class TerminaleEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "mlg")
    private Double mlg;
    @Basic
    @Column(name = "frs")
    private Double frs;
    @Basic
    @Column(name = "anglais")
    private Double anglais;
    @Basic
    @Column(name = "histogeo")
    private Double histogeo;
    @Basic
    @Column(name = "phylo")
    private Double phylo;
    @Basic
    @Column(name = "math")
    private Double math;
    @Basic
    @Column(name = "spc")
    private Double spc;
    @Basic
    @Column(name = "svt")
    private Double svt;
    @Basic
    @Column(name = "ses")
    private Double ses;
    @Basic
    @Column(name = "eps")
    private Double eps;
    @Basic
    @Column(name = "nmat")
    private String nmat;
    @Basic
    @Column(name = "trimestre")
    private Integer trimestre;
    @Basic
    @Column(name = "annee_scolaire")
    private String anneeScolaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getMlg() {
        return mlg;
    }

    public void setMlg(Double mlg) {
        this.mlg = mlg;
    }

    public Double getFrs() {
        return frs;
    }

    public void setFrs(Double frs) {
        this.frs = frs;
    }

    public Double getAnglais() {
        return anglais;
    }

    public void setAnglais(Double anglais) {
        this.anglais = anglais;
    }

    public Double getHistogeo() {
        return histogeo;
    }

    public void setHistogeo(Double histogeo) {
        this.histogeo = histogeo;
    }

    public Double getPhylo() {
        return phylo;
    }

    public void setPhylo(Double phylo) {
        this.phylo = phylo;
    }

    public Double getMath() {
        return math;
    }

    public void setMath(Double math) {
        this.math = math;
    }

    public Double getSpc() {
        return spc;
    }

    public void setSpc(Double spc) {
        this.spc = spc;
    }

    public Double getSvt() {
        return svt;
    }

    public void setSvt(Double svt) {
        this.svt = svt;
    }

    public Double getSes() {
        return ses;
    }

    public void setSes(Double ses) {
        this.ses = ses;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public String getNmat() {
        return nmat;
    }

    public void setNmat(String nmat) {
        this.nmat = nmat;
    }

    public Integer getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(Integer trimestre) {
        this.trimestre = trimestre;
    }

    public String getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(String anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TerminaleEntity that = (TerminaleEntity) o;
        return id == that.id && Objects.equals(mlg, that.mlg) && Objects.equals(frs, that.frs) && Objects.equals(anglais, that.anglais) && Objects.equals(histogeo, that.histogeo) && Objects.equals(phylo, that.phylo) && Objects.equals(math, that.math) && Objects.equals(spc, that.spc) && Objects.equals(svt, that.svt) && Objects.equals(ses, that.ses) && Objects.equals(eps, that.eps) && Objects.equals(nmat, that.nmat) && Objects.equals(trimestre, that.trimestre) && Objects.equals(anneeScolaire, that.anneeScolaire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mlg, frs, anglais, histogeo, phylo, math, spc, svt, ses, eps, nmat, trimestre, anneeScolaire);
    }
}
