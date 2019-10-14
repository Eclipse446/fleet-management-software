/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S
 */
@Entity
@Table(name = "affectation_chauffeur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AffectationChauffeur.findAll", query = "SELECT a FROM AffectationChauffeur a"),
    @NamedQuery(name = "AffectationChauffeur.findById", query = "SELECT a FROM AffectationChauffeur a WHERE a.id = :id"),
    @NamedQuery(name = "AffectationChauffeur.findByDateDebut", query = "SELECT a FROM AffectationChauffeur a WHERE a.dateDebut = :dateDebut"),
    @NamedQuery(name = "AffectationChauffeur.findByDateFin", query = "SELECT a FROM AffectationChauffeur a WHERE a.dateFin = :dateFin")})
public class AffectationChauffeur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_debut")
    @Temporal(TemporalType.DATE)
    private Date dateDebut;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @JoinColumn(name = "id_chauffeur", referencedColumnName = "id")
    @ManyToOne
    private Chauffeur idChauffeur;
    @JoinColumn(name = "id_vehicule", referencedColumnName = "id")
    @ManyToOne
    private Vehicule idVehicule;

    public AffectationChauffeur() {
    }

    public AffectationChauffeur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Chauffeur getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(Chauffeur idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public Vehicule getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Vehicule idVehicule) {
        this.idVehicule = idVehicule;
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
        if (!(object instanceof AffectationChauffeur)) {
            return false;
        }
        AffectationChauffeur other = (AffectationChauffeur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.AffectationChauffeur[ id=" + id + " ]";
    }
    
}
