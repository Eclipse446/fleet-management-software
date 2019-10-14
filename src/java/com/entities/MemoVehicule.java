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
@Table(name = "memo_vehicule")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MemoVehicule.findAll", query = "SELECT m FROM MemoVehicule m"),
    @NamedQuery(name = "MemoVehicule.findById", query = "SELECT m FROM MemoVehicule m WHERE m.id = :id"),
    @NamedQuery(name = "MemoVehicule.findByDateDebut", query = "SELECT m FROM MemoVehicule m WHERE m.dateDebut = :dateDebut"),
    @NamedQuery(name = "MemoVehicule.findByCommentaire", query = "SELECT m FROM MemoVehicule m WHERE m.commentaire = :commentaire"),
    @NamedQuery(name = "MemoVehicule.findByDateFin", query = "SELECT m FROM MemoVehicule m WHERE m.dateFin = :dateFin")})
public class MemoVehicule implements Serializable {
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
    @Size(max = 100)
    @Column(name = "commentaire")
    private String commentaire;
    @Column(name = "date_fin")
    @Temporal(TemporalType.DATE)
    private Date dateFin;
    @JoinColumn(name = "id_vehicule", referencedColumnName = "id")
    @ManyToOne
    private Vehicule idVehicule;

    public MemoVehicule() {
    }

    public MemoVehicule(Integer id) {
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

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
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
        if (!(object instanceof MemoVehicule)) {
            return false;
        }
        MemoVehicule other = (MemoVehicule) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.MemoVehicule[ id=" + id + " ]";
    }
    
}
