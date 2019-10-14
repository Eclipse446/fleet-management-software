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
@Table(name = "fournisseur")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fournisseur.findAll", query = "SELECT f FROM Fournisseur f"),
    @NamedQuery(name = "Fournisseur.findById", query = "SELECT f FROM Fournisseur f WHERE f.id = :id"),
    @NamedQuery(name = "Fournisseur.findByLibelle", query = "SELECT f FROM Fournisseur f WHERE f.libelle = :libelle"),
    @NamedQuery(name = "Fournisseur.findByType", query = "SELECT f FROM Fournisseur f WHERE f.type = :type")})
public class Fournisseur implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 50)
    @Column(name = "libelle")
    private String libelle;
    @Size(max = 50)
    @Column(name = "type")
    private String type;
    @OneToMany(mappedBy = "idFournisseur")
    private List<ConsommationCarburant> consommationCarburantList;
    @OneToMany(mappedBy = "idFournisseur")
    private List<Depense> depenseList;

    public Fournisseur() {
    }

    public Fournisseur(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fournisseur)) {
            return false;
        }
        Fournisseur other = (Fournisseur) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Fournisseur[ id=" + id + " ]";
    }
    
}
