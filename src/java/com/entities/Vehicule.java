/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "vehicule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vehicule.findAll", query = "SELECT v FROM Vehicule v"),
    @NamedQuery(name = "Vehicule.findByImmatriculation", query = "SELECT v FROM Vehicule v WHERE v.immatriculation = :immatriculation"),
    @NamedQuery(name = "Vehicule.findByDateMiseCirculation", query = "SELECT v FROM Vehicule v WHERE v.dateMiseCirculation = :dateMiseCirculation"),
    @NamedQuery(name = "Vehicule.findByDateAcquisition", query = "SELECT v FROM Vehicule v WHERE v.dateAcquisition = :dateAcquisition"),
    @NamedQuery(name = "Vehicule.findByCoutAcquisition", query = "SELECT v FROM Vehicule v WHERE v.coutAcquisition = :coutAcquisition"),
    @NamedQuery(name = "Vehicule.findByKilometrageActuel", query = "SELECT v FROM Vehicule v WHERE v.kilometrageActuel = :kilometrageActuel"),
    @NamedQuery(name = "Vehicule.findByNumeroMoteur", query = "SELECT v FROM Vehicule v WHERE v.numeroMoteur = :numeroMoteur"),
    @NamedQuery(name = "Vehicule.findByNumeroChassis", query = "SELECT v FROM Vehicule v WHERE v.numeroChassis = :numeroChassis"),
    @NamedQuery(name = "Vehicule.findByDateDebutVignette", query = "SELECT v FROM Vehicule v WHERE v.dateDebutVignette = :dateDebutVignette"),
    @NamedQuery(name = "Vehicule.findByDateExpirationVignette", query = "SELECT v FROM Vehicule v WHERE v.dateExpirationVignette = :dateExpirationVignette"),
    @NamedQuery(name = "Vehicule.findByDateDebutAssurance", query = "SELECT v FROM Vehicule v WHERE v.dateDebutAssurance = :dateDebutAssurance"),
    @NamedQuery(name = "Vehicule.findByDateExpirationAssurance", query = "SELECT v FROM Vehicule v WHERE v.dateExpirationAssurance = :dateExpirationAssurance"),
    @NamedQuery(name = "Vehicule.findByMontantAnnuelleAssurance", query = "SELECT v FROM Vehicule v WHERE v.montantAnnuelleAssurance = :montantAnnuelleAssurance"),
    @NamedQuery(name = "Vehicule.findByDateDebutVisiteTechnique", query = "SELECT v FROM Vehicule v WHERE v.dateDebutVisiteTechnique = :dateDebutVisiteTechnique"),
    @NamedQuery(name = "Vehicule.findByDateExpirationVisiteTechnique", query = "SELECT v FROM Vehicule v WHERE v.dateExpirationVisiteTechnique = :dateExpirationVisiteTechnique"),
    @NamedQuery(name = "Vehicule.findByPuissance", query = "SELECT v FROM Vehicule v WHERE v.puissance = :puissance"),
    @NamedQuery(name = "Vehicule.findByPoid", query = "SELECT v FROM Vehicule v WHERE v.poid = :poid"),
    @NamedQuery(name = "Vehicule.findById", query = "SELECT v FROM Vehicule v WHERE v.id = :id")})

public class Vehicule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Size(max = 45)
    @Column(name = "immatriculation")
    private String immatriculation;
    @Column(name = "date_mise_circulation")
    @Temporal(TemporalType.DATE)
    private Date dateMiseCirculation;
    @Column(name = "date_acquisition")
    @Temporal(TemporalType.DATE)
    private Date dateAcquisition;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cout_acquisition")
    private BigDecimal coutAcquisition;
    @Column(name = "kilometrage_actuel")
    private BigDecimal kilometrageActuel;
    @Column(name = "numero_moteur")
    private Integer numeroMoteur;
    @Column(name = "numero_chassis")
    private Integer numeroChassis;
    @Column(name = "date_debut_vignette")
    @Temporal(TemporalType.DATE)
    private Date dateDebutVignette;
    @Column(name = "date_expiration_vignette")
    @Temporal(TemporalType.DATE)
    private Date dateExpirationVignette;
    @Column(name = "date_debut_assurance")
    @Temporal(TemporalType.DATE)
    private Date dateDebutAssurance;
    @Column(name = "date_expiration_assurance")
    @Temporal(TemporalType.DATE)
    private Date dateExpirationAssurance;
    @Column(name = "montant_annuelle_assurance")
    private BigDecimal montantAnnuelleAssurance;
    @Column(name = "date_debut_visite_technique")
    @Temporal(TemporalType.DATE)
    private Date dateDebutVisiteTechnique;
    @Column(name = "date_expiration_visite_technique")
    @Temporal(TemporalType.DATE)
    private Date dateExpirationVisiteTechnique;
    @Column(name = "puissance")
    private Float puissance;
    @Column(name = "poid")
    private BigDecimal poid;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<ConsommationCarburant> consommationCarburantList;
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<Depense> depenseList;
    @JoinColumn(name = "id_type_contrat_assurance", referencedColumnName = "id")
    @ManyToOne
    private TypeContratAssurance idTypeContratAssurance;
    @JoinColumn(name = "id_typecarburant", referencedColumnName = "id")
    @ManyToOne
    private TypeCarburant idTypecarburant;
    @JoinColumn(name = "id_type_boite", referencedColumnName = "id")
    @ManyToOne
    private TypeBoiteVitesse idTypeBoite;
    @JoinColumn(name = "id_modele", referencedColumnName = "id")
    @ManyToOne
    private Modele idModele;
    
    @JoinColumn(name = "id_assurance", referencedColumnName = "id")
    @ManyToOne
    private CompagnieAssurance idAssurance;
    
    @JoinColumn(name = "id_affectation", referencedColumnName = "id")
    @ManyToOne
    private TypeAffectation idAffectation;
   
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<Panne> panneList;
   
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<Entretien> entretienList;
    
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<AffectationChauffeur> affectationChauffeurList;
    
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<MemoVehicule> memoVehiculeList;
   
    @OneToMany(mappedBy = "idVehicule",cascade=CascadeType.REMOVE)
    private List<VisiteTechnique> visiteTechniqueList;

    public Vehicule() {
    }

    public Vehicule(Integer id) {
        this.id = id;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public Date getDateMiseCirculation() {
        return dateMiseCirculation;
    }

    public void setDateMiseCirculation(Date dateMiseCirculation) {
        this.dateMiseCirculation = dateMiseCirculation;
    }

    public Date getDateAcquisition() {
        return dateAcquisition;
    }

    public void setDateAcquisition(Date dateAcquisition) {
        this.dateAcquisition = dateAcquisition;
    }

    public BigDecimal getCoutAcquisition() {
        return coutAcquisition;
    }

    public void setCoutAcquisition(BigDecimal coutAcquisition) {
        this.coutAcquisition = coutAcquisition;
    }

    public BigDecimal getKilometrageActuel() {
        return kilometrageActuel;
    }

    public void setKilometrageActuel(BigDecimal kilometrageActuel) {
        this.kilometrageActuel = kilometrageActuel;
    }

    public Integer getNumeroMoteur() {
        return numeroMoteur;
    }

    public void setNumeroMoteur(Integer numeroMoteur) {
        this.numeroMoteur = numeroMoteur;
    }

    public Integer getNumeroChassis() {
        return numeroChassis;
    }

    public void setNumeroChassis(Integer numeroChassis) {
        this.numeroChassis = numeroChassis;
    }

    public Date getDateDebutVignette() {
        return dateDebutVignette;
    }

    public void setDateDebutVignette(Date dateDebutVignette) {
        this.dateDebutVignette = dateDebutVignette;
    }

    public Date getDateExpirationVignette() {
        return dateExpirationVignette;
    }

    public void setDateExpirationVignette(Date dateExpirationVignette) {
        this.dateExpirationVignette = dateExpirationVignette;
    }

    public Date getDateDebutAssurance() {
        return dateDebutAssurance;
    }

    public void setDateDebutAssurance(Date dateDebutAssurance) {
        this.dateDebutAssurance = dateDebutAssurance;
    }

    public Date getDateExpirationAssurance() {
        return dateExpirationAssurance;
    }

    public void setDateExpirationAssurance(Date dateExpirationAssurance) {
        this.dateExpirationAssurance = dateExpirationAssurance;
    }

    public BigDecimal getMontantAnnuelleAssurance() {
        return montantAnnuelleAssurance;
    }

    public void setMontantAnnuelleAssurance(BigDecimal montantAnnuelleAssurance) {
        this.montantAnnuelleAssurance = montantAnnuelleAssurance;
    }

    public Date getDateDebutVisiteTechnique() {
        return dateDebutVisiteTechnique;
    }

    public void setDateDebutVisiteTechnique(Date dateDebutVisiteTechnique) {
        this.dateDebutVisiteTechnique = dateDebutVisiteTechnique;
    }

    public Date getDateExpirationVisiteTechnique() {
        return dateExpirationVisiteTechnique;
    }

    public void setDateExpirationVisiteTechnique(Date dateExpirationVisiteTechnique) {
        this.dateExpirationVisiteTechnique = dateExpirationVisiteTechnique;
    }

    public Float getPuissance() {
        return puissance;
    }

    public void setPuissance(Float puissance) {
        this.puissance = puissance;
    }

    public BigDecimal getPoid() {
        return poid;
    }

    public void setPoid(BigDecimal poid) {
        this.poid = poid;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TypeContratAssurance getIdTypeContratAssurance() {
        return idTypeContratAssurance;
    }

    public void setIdTypeContratAssurance(TypeContratAssurance idTypeContratAssurance) {
        this.idTypeContratAssurance = idTypeContratAssurance;
    }

    public TypeCarburant getIdTypecarburant() {
        return idTypecarburant;
    }

    public void setIdTypecarburant(TypeCarburant idTypecarburant) {
        this.idTypecarburant = idTypecarburant;
    }

    public TypeBoiteVitesse getIdTypeBoite() {
        return idTypeBoite;
    }

    public void setIdTypeBoite(TypeBoiteVitesse idTypeBoite) {
        this.idTypeBoite = idTypeBoite;
    }

    public Modele getIdModele() {
        return idModele;
    }

    public void setIdModele(Modele idModele) {
        this.idModele = idModele;
    }

    public CompagnieAssurance getIdAssurance() {
        return idAssurance;
    }

    public void setIdAssurance(CompagnieAssurance idAssurance) {
        this.idAssurance = idAssurance;
    }

    public TypeAffectation getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(TypeAffectation idAffectation) {
        this.idAffectation = idAffectation;
    }

    @XmlTransient
    public List<Panne> getPanneList() {
        return panneList;
    }

    public void setPanneList(List<Panne> panneList) {
        this.panneList = panneList;
    }

    @XmlTransient
    public List<Entretien> getEntretienList() {
        return entretienList;
    }

    public void setEntretienList(List<Entretien> entretienList) {
        this.entretienList = entretienList;
    }

    @XmlTransient
    public List<AffectationChauffeur> getAffectationChauffeurList() {
        return affectationChauffeurList;
    }

    public void setAffectationChauffeurList(List<AffectationChauffeur> affectationChauffeurList) {
        this.affectationChauffeurList = affectationChauffeurList;
    }

    @XmlTransient
    public List<MemoVehicule> getMemoVehiculeList() {
        return memoVehiculeList;
    }

    public void setMemoVehiculeList(List<MemoVehicule> memoVehiculeList) {
        this.memoVehiculeList = memoVehiculeList;
    }

    @XmlTransient
    public List<VisiteTechnique> getVisiteTechniqueList() {
        return visiteTechniqueList;
    }

    public void setVisiteTechniqueList(List<VisiteTechnique> visiteTechniqueList) {
        this.visiteTechniqueList = visiteTechniqueList;
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
        if (!(object instanceof Vehicule)) {
            return false;
        }
        Vehicule other = (Vehicule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Vehicule[ id=" + id + " ]";
    }
    
}
