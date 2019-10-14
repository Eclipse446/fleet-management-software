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
@Table(name = "consommation_carburant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConsommationCarburant.findAll", query = "SELECT c FROM ConsommationCarburant c"),
    @NamedQuery(name = "ConsommationCarburant.findById", query = "SELECT c FROM ConsommationCarburant c WHERE c.id = :id"),
    @NamedQuery(name = "ConsommationCarburant.findByDate", query = "SELECT c FROM ConsommationCarburant c WHERE c.date = :date"),
    @NamedQuery(name = "ConsommationCarburant.findByVolume", query = "SELECT c FROM ConsommationCarburant c WHERE c.volume = :volume"),
    @NamedQuery(name = "ConsommationCarburant.findByMontant", query = "SELECT c FROM ConsommationCarburant c WHERE c.montant = :montant"),
    @NamedQuery(name = "ConsommationCarburant.findByCompteurKilometre", query = "SELECT c FROM ConsommationCarburant c WHERE c.compteurKilometre = :compteurKilometre"),
    @NamedQuery(name = "ConsommationCarburant.findByReferenceBon", query = "SELECT c FROM ConsommationCarburant c WHERE c.referenceBon = :referenceBon"),
    @NamedQuery(name = "ConsommationCarburant.findByMouvementCaisse", query = "SELECT c FROM ConsommationCarburant c WHERE c.mouvementCaisse = :mouvementCaisse")})
public class ConsommationCarburant implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "volume")
    private Float volume;
    @Column(name = "montant")
    private BigDecimal montant;
    @Column(name = "compteur_kilometre")
    private BigDecimal compteurKilometre;
    @Size(max = 50)
    @Column(name = "reference_bon")
    private String referenceBon;
    @Column(name = "mouvement_caisse")
    private Boolean mouvementCaisse;
    @JoinColumn(name = "id_fournisseur", referencedColumnName = "id")
    @ManyToOne
    private Fournisseur idFournisseur;
    @JoinColumn(name = "id_vehicule", referencedColumnName = "id")
    @ManyToOne
    private Vehicule idVehicule;
    @JoinColumn(name = "id_chauffeur", referencedColumnName = "id")
    @ManyToOne
    private Chauffeur idChauffeur;

    public ConsommationCarburant() {
    }

    public ConsommationCarburant(Integer id) {
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

    public Float getVolume() {
        return volume;
    }

    public void setVolume(Float volume) {
        this.volume = volume;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }

    public BigDecimal getCompteurKilometre() {
        return compteurKilometre;
    }

    public void setCompteurKilometre(BigDecimal compteurKilometre) {
        this.compteurKilometre = compteurKilometre;
    }

    public String getReferenceBon() {
        return referenceBon;
    }

    public void setReferenceBon(String referenceBon) {
        this.referenceBon = referenceBon;
    }

    public Boolean getMouvementCaisse() {
        return mouvementCaisse;
    }

    public void setMouvementCaisse(Boolean mouvementCaisse) {
        this.mouvementCaisse = mouvementCaisse;
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
        if (!(object instanceof ConsommationCarburant)) {
            return false;
        }
        ConsommationCarburant other = (ConsommationCarburant) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.ConsommationCarburant[ id=" + id + " ]";
    }
    
}
