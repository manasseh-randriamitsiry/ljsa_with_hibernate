package com.manasseh.ljsa.model;

import com.manasseh.ljsa.DAO.ClasseDAO;
import com.manasseh.ljsa.DAO.EtudiantDAO;

import javax.persistence.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.Objects;

@Entity
@Table(name = "premiere", schema = "ljsa")
public class PremiereEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "malagasy")
    private double malagasy;
    @Basic
    @Column(name = "francais")
    private double francais;
    @Basic
    @Column(name = "anglais")
    private double anglais;
    @Basic
    @Column(name = "histogeo")
    private double histogeo;
    @Basic
    @Column(name = "eac")
    private double eac;
    @Basic
    @Column(name = "ses")
    private double ses;
    @Basic
    @Column(name = "spc")
    private double spc;
    @Basic
    @Column(name = "svt")
    private double svt;
    @Basic
    @Column(name = "mats")
    private double mats;
    @Basic
    @Column(name = "eps")
    private double eps;
    @Basic
    @Column(name = "tice")
    private double tice;
    @Basic
    @Column(name = "phylo")
    private double phylo;
    @Basic
    @Column(name = "n_mat")
    private String nMat;
    @Basic
    @Column(name = "trimestre")
    private String trimestre;
    @Basic
    @Column(name = "annee_scolaire")
    private int anneeScolaire;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getMalagasy() {
        return malagasy;
    }

    public void setMalagasy(double malagasy) {
        this.malagasy = malagasy;
    }

    public double getFrancais() {
        return francais;
    }

    public void setFrancais(double francais) {
        this.francais = francais;
    }

    public double getAnglais() {
        return anglais;
    }

    public void setAnglais(double anglais) {
        this.anglais = anglais;
    }

    public double getHistogeo() {
        return histogeo;
    }

    public void setHistogeo(double histogeo) {
        this.histogeo = histogeo;
    }

    public double getEac() {
        return eac;
    }

    public void setEac(double eac) {
        this.eac = eac;
    }

    public double getSes() {
        return ses;
    }

    public void setSes(double ses) {
        this.ses = ses;
    }

    public double getSpc() {
        return spc;
    }

    public void setSpc(double spc) {
        this.spc = spc;
    }

    public double getSvt() {
        return svt;
    }

    public void setSvt(double svt) {
        this.svt = svt;
    }

    public double getMats() {
        return mats;
    }

    public void setMats(double mats) {
        this.mats = mats;
    }

    public double getEps() {
        return eps;
    }

    public void setEps(double eps) {
        this.eps = eps;
    }

    public double getTice() {
        return tice;
    }

    public void setTice(double tice) {
        this.tice = tice;
    }

    public double getPhylo() {
        return phylo;
    }

    public void setPhylo(double phylo) {
        this.phylo = phylo;
    }

    public String getnMat() {
        return nMat;
    }

    public void setnMat(String nMat) {
        this.nMat = nMat;
    }

    public String getTrimestre() {
        return trimestre;
    }

    public void setTrimestre(String trimestre) {
        this.trimestre = trimestre;
    }

    public int getAnneeScolaire() {
        return anneeScolaire;
    }

    public void setAnneeScolaire(int anneeScolaire) {
        this.anneeScolaire = anneeScolaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PremiereEntity that = (PremiereEntity) o;
        return id == that.id && Double.compare(that.malagasy, malagasy) == 0 && Double.compare(that.francais, francais) == 0 && Double.compare(that.anglais, anglais) == 0 && Double.compare(that.histogeo, histogeo) == 0 && Double.compare(that.eac, eac) == 0 && Double.compare(that.ses, ses) == 0 && Double.compare(that.spc, spc) == 0 && Double.compare(that.svt, svt) == 0 && Double.compare(that.mats, mats) == 0 && Double.compare(that.eps, eps) == 0 && Double.compare(that.tice, tice) == 0 && Double.compare(that.phylo, phylo) == 0 && anneeScolaire == that.anneeScolaire && Objects.equals(nMat, that.nMat) && Objects.equals(trimestre, that.trimestre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, malagasy, francais, anglais, histogeo, eac, ses, spc, svt, mats, eps, tice, phylo, nMat, trimestre, anneeScolaire);
    }

    public String getMoyenne() throws SQLException {
        DecimalFormat df = new DecimalFormat("###.##");
        ClasseDAO classeDAO = new ClasseDAO();
        EtudiantDAO etudiantDAO = new EtudiantDAO();
        int coeff = classeDAO.getClasse(etudiantDAO.getClasse(nMat));
        double sum = getTotal()/coeff;
        return df.format(sum);
    }

    public double getTotal() {
        return malagasy+ francais+ anglais+ histogeo+ eac+ ses+ spc+ svt+ mats+ eps+ tice+phylo;
    }
}
