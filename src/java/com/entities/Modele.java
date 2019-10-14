/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author S
 */
@Entity
@Table(name = "modele")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Modele.findAll", query = "SELECT m FROM Modele m"),
    @NamedQuery(name = "Modele.findById", query = "SELECT m FROM Modele m WHERE m.id = :id"),
    @NamedQuery(name = "Modele.findByCode", query = "SELECT m FROM Modele m WHERE m.code = :code"),
    @NamedQuery(name = "Modele.findByLibelle", query = "SELECT m FROM Modele m WHERE m.libelle = :libelle")})
public class Modele implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "code")
    private String code;
    @Size(max = 45)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(mappedBy = "idModele")
    private List<Vehicule> vehiculeList;
    @JoinColumn(name = "id_marque", referencedColumnName = "id")
    @ManyToOne
    private Marque idMarque;

    public Modele() {
    }

    public Modele(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @XmlTransient
    public List<Vehicule> getVehiculeList() {
        return vehiculeList;
    }

    public void setVehiculeList(List<Vehicule> vehiculeList) {
        this.vehiculeList = vehiculeList;
    }

    public Marque getIdMarque() {
        return idMarque;
    }

    public void setIdMarque(Marque idMarque) {
        this.idMarque = idMarque;
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
        if (!(object instanceof Modele)) {
            return false;
        }
        Modele other = (Modele) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Modele[ id=" + id + " ]";
    }
    
}
