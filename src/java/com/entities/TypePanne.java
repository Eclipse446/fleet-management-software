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
@Table(name = "type_panne")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TypePanne.findAll", query = "SELECT t FROM TypePanne t"),
    @NamedQuery(name = "TypePanne.findById", query = "SELECT t FROM TypePanne t WHERE t.id = :id"),
    @NamedQuery(name = "TypePanne.findByLibelle", query = "SELECT t FROM TypePanne t WHERE t.libelle = :libelle")})
public class TypePanne implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 45)
    @Column(name = "libelle")
    private String libelle;
    @OneToMany(mappedBy = "idTypepanne")
    private List<Panne> panneList;

    public TypePanne() {
    }

    public TypePanne(Integer id) {
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

    @XmlTransient
    public List<Panne> getPanneList() {
        return panneList;
    }

    public void setPanneList(List<Panne> panneList) {
        this.panneList = panneList;
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
        if (!(object instanceof TypePanne)) {
            return false;
        }
        TypePanne other = (TypePanne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.TypePanne[ id=" + id + " ]";
    }
    
}
