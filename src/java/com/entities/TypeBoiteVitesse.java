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
@Table(name = "type_boite_vitesse")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypeBoiteVitesse.findAll", query = "SELECT t FROM TypeBoiteVitesse t"),
    @NamedQuery(name = "TypeBoiteVitesse.findById", query = "SELECT t FROM TypeBoiteVitesse t WHERE t.id = :id"),
    @NamedQuery(name = "TypeBoiteVitesse.findByCode", query = "SELECT t FROM TypeBoiteVitesse t WHERE t.code = :code"),
    @NamedQuery(name = "TypeBoiteVitesse.findByLibelle", query = "SELECT t FROM TypeBoiteVitesse t WHERE t.libelle = :libelle")})
public class TypeBoiteVitesse implements Serializable {
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
    @OneToMany(mappedBy = "idTypeBoite")
    private List<Vehicule> vehiculeList;

    public TypeBoiteVitesse() {
    }

    public TypeBoiteVitesse(Integer id) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TypeBoiteVitesse)) {
            return false;
        }
        TypeBoiteVitesse other = (TypeBoiteVitesse) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.TypeBoiteVitesse[ id=" + id + " ]";
    }
    
}
