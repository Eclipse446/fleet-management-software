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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S
 */
@Entity
@Table(name = "panne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Panne.findAll", query = "SELECT p FROM Panne p"),
    @NamedQuery(name = "Panne.findById", query = "SELECT p FROM Panne p WHERE p.id = :id"),
    @NamedQuery(name = "Panne.findByDate", query = "SELECT p FROM Panne p WHERE p.date = :date"),
    @NamedQuery(name = "Panne.findByDepart", query = "SELECT p FROM Panne p WHERE p.depart = :depart"),
    @NamedQuery(name = "Panne.findByDestination", query = "SELECT p FROM Panne p WHERE p.destination = :destination")})
public class Panne implements Serializable {
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
    @Size(max = 45)
    @Column(name = "depart")
    private String depart;
    @Size(max = 45)
    @Column(name = "destination")
    private String destination;
    @JoinColumn(name = "id_vehicule", referencedColumnName = "id")
    @ManyToOne
    private Vehicule idVehicule;
    @JoinColumn(name = "id_typepanne", referencedColumnName = "id")
    @ManyToOne
    private TypePanne idTypepanne;
    @JoinColumn(name = "id_chauffeur", referencedColumnName = "id")
    @ManyToOne
    private Chauffeur idChauffeur;

    public Panne() {
    }

    public Panne(Integer id) {
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

    public String getDepart() {
        return depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Vehicule getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Vehicule idVehicule) {
        this.idVehicule = idVehicule;
    }

    public TypePanne getIdTypepanne() {
        return idTypepanne;
    }

    public void setIdTypepanne(TypePanne idTypepanne) {
        this.idTypepanne = idTypepanne;
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
        if (!(object instanceof Panne)) {
            return false;
        }
        Panne other = (Panne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Panne[ id=" + id + " ]";
    }
    
}
