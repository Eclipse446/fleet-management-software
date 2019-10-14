/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author S
 */
@Entity
@Table(name = "chauffeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Chauffeur.findAll", query = "SELECT c FROM Chauffeur c"),
    @NamedQuery(name = "Chauffeur.findById", query = "SELECT c FROM Chauffeur c WHERE c.id = :id"),
    @NamedQuery(name = "Chauffeur.findByNom", query = "SELECT c FROM Chauffeur c WHERE c.nom = :nom"),
    @NamedQuery(name = "Chauffeur.findByPrenom", query = "SELECT c FROM Chauffeur c WHERE c.prenom = :prenom"),
    @NamedQuery(name = "Chauffeur.findByCin", query = "SELECT c FROM Chauffeur c WHERE c.cin = :cin"),
    @NamedQuery(name = "Chauffeur.findByDateValiditePermis", query = "SELECT c FROM Chauffeur c WHERE c.dateValiditePermis = :dateValiditePermis")})
public class Chauffeur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "nom")
    private String nom;
    @Size(max = 45)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "cin")
    private Integer cin;
    @Column(name = "date_validite_permis")
    @Temporal(TemporalType.DATE)
    private Date dateValiditePermis;
    @OneToMany(mappedBy = "idChauffeur")
    private List<ConsommationCarburant> consommationCarburantList;
    @OneToMany(mappedBy = "idChauffeur")
    private List<Depense> depenseList;
    @OneToMany(mappedBy = "idChauffeur")
    private List<Panne> panneList;
    @OneToMany(mappedBy = "idChauffeur")
    private List<AffectationChauffeur> affectationChauffeurList;

    public Chauffeur() {
    }

    public Chauffeur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getCin() {
        return cin;
    }

    public void setCin(Integer cin) {
        this.cin = cin;
    }

    public Date getDateValiditePermis() {
        return dateValiditePermis;
    }

    public void setDateValiditePermis(Date dateValiditePermis) {
        this.dateValiditePermis = dateValiditePermis;
    }

    @XmlTransient
    public List<ConsommationCarburant> getConsommationCarburantList() {
        return consommationCarburantList;
    }

    public void setConsommationCarburantList(List<ConsommationCarburant> consommationCarburantList) {
        this.consommationCarburantList = consommationCarburantList;
    }

    @XmlTransient
    public List<Depense> getDepenseList() {
        return depenseList;
    }

    public void setDepenseList(List<Depense> depenseList) {
        this.depenseList = depenseList;
    }

    @XmlTransient
    public List<Panne> getPanneList() {
        return panneList;
    }

    public void setPanneList(List<Panne> panneList) {
        this.panneList = panneList;
    }

    @XmlTransient
    public List<AffectationChauffeur> getAffectationChauffeurList() {
        return affectationChauffeurList;
    }

    public void setAffectationChauffeurList(List<AffectationChauffeur> affectationChauffeurList) {
        this.affectationChauffeurList = affectationChauffeurList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Chauffeur)) {
            return false;
        }
        Chauffeur other = (Chauffeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Chauffeur[ id=" + id + " ]";
    }
    
}
