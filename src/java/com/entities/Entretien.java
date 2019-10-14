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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author S
 */
@Entity
@Table(name = "entretien")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Entretien.findAll", query = "SELECT e FROM Entretien e"),
    @NamedQuery(name = "Entretien.findById", query = "SELECT e FROM Entretien e WHERE e.id = :id"),
    @NamedQuery(name = "Entretien.findByDate", query = "SELECT e FROM Entretien e WHERE e.date = :date"),
    @NamedQuery(name = "Entretien.findByDateExecution", query = "SELECT e FROM Entretien e WHERE e.dateExecution = :dateExecution"),
    @NamedQuery(name = "Entretien.findByExecution", query = "SELECT e FROM Entretien e WHERE e.execution = :execution"),
    @NamedQuery(name = "Entretien.findByCout", query = "SELECT e FROM Entretien e WHERE e.cout = :cout")})
public class Entretien implements Serializable {
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
    @Column(name = "date_execution")
    @Temporal(TemporalType.DATE)
    private Date dateExecution;
    @Column(name = "execution")
    private Boolean execution;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cout")
    private BigDecimal cout;
    @JoinColumn(name = "id_vehicule", referencedColumnName = "id")
    @ManyToOne
    private Vehicule idVehicule;
    @JoinColumn(name = "id_operation", referencedColumnName = "id")
    @ManyToOne
    private Operation idOperation;

    public Entretien() {
    }

    public Entretien(Integer id) {
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

    public Date getDateExecution() {
        return dateExecution;
    }

    public void setDateExecution(Date dateExecution) {
        this.dateExecution = dateExecution;
    }

    public Boolean getExecution() {
        return execution;
    }

    public void setExecution(Boolean execution) {
        this.execution = execution;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public Vehicule getIdVehicule() {
        return idVehicule;
    }

    public void setIdVehicule(Vehicule idVehicule) {
        this.idVehicule = idVehicule;
    }

    public Operation getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(Operation idOperation) {
        this.idOperation = idOperation;
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
        if (!(object instanceof Entretien)) {
            return false;
        }
        Entretien other = (Entretien) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Entretien[ id=" + id + " ]";
    }
    
}
