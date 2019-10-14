/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S
 */
@Entity
@Table(name = "depense")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Depense.findAll", query = "SELECT d FROM Depense d"),
    @NamedQuery(name = "Depense.findById", query = "SELECT d FROM Depense d WHERE d.id = :id"),
    @NamedQuery(name = "Depense.findByDate", query = "SELECT d FROM Depense d WHERE d.date = :date"),
    @NamedQuery(name = "Depense.findByLibelle", query = "SELECT d FROM Depense d WHERE d.libelle = :libelle"),
    @NamedQuery(name = "Depense.findByMontant", query = "SELECT d FROM Depense d WHERE d.montant = :montant"),
    @NamedQuery(name = "Depense.findByCompteurKm", query = "SELECT d FROM Depense d WHERE d.compteurKm = :compteurKm"),
    @NamedQuery(name = "Depense.findByReferenceDepense", query = "SELECT d FROM Depense d WHERE d.referenceDepense = :referenceDepense"),
    @NamedQuery(name = "Depense.findByMouvementCaisse", query = "SELECT d FROM Depense d WHERE d.mouvementCaisse = :mouvementCaisse")})
public class Depense implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    private Date date;
    @Size(max = 50)
    @Column(name = "libelle")
    private String libelle;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "montant")
    private BigDecimal montant;
    @Column(name = "compteur_km")
    private Float compteurKm;
    @Size(max = 50)
    @Column(name = "reference_depense")
    private String referenceDepense;
    @Column(name = "mouvement_caisse")
    private Boolean mouvementCaisse;
    @JoinColumn(name = "id_rubrique", referencedColumnName = "id")
    @ManyToOne
    private Rubrique idRubrique;
    @JoinColumn(name = "id_fournisseur", referencedColumnName = "id")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "id_vehicule", referencedColumnName = "id")
    @ManyToOne
    private Vehicule idVehicule;
    @JoinColumn(name = "id_chauffeur", referencedColumnName = "id")
    @ManyToOne
    private Chauffeur idChauffeur;

    public Depense() {
    }

    public Depense(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public Float getCompteurKm() {
        return compteurKm;
    }

    public void setCompteurKm(Float compteurKm) {
        this.compteurKm = compteurKm;
    }

    public String getReferenceDepense() {
        return referenceDepense;
    }

    public void setReferenceDepense(String referenceDepense) {
        this.referenceDepense = referenceDepense;
    }

    public Boolean getMouvementCaisse() {
        return mouvementCaisse;
    }

    public void setMouvementCaisse(Boolean mouvementCaisse) {
        this.mouvementCaisse = mouvementCaisse;
    }

    public Rubrique getIdRubrique() {
        return idRubrique;
    }

    public void setIdRubrique(Rubrique idRubrique) {
        this.idRubrique = idRubrique;
    }

    public Fournisseur getIdFournisseur() {
        return idFournisseur;
    }

    public void setIdFournisseur(Fournisseur idFournisseur) {
        this.idFournisseur = idFournisseur;
    }

    public Vehicule getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Vehicule idVehicule) {
        this.idVehicule = idVehicule;
    }

    public Chauffeur getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(Chauffeur idChauffeur) {
        this.idChauffeur = idChauffeur;
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
        if (!(object instanceof Depense)) {
            return false;
        }
        Depense other = (Depense) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Depense[ id=" + id + " ]";
    }
    
}
