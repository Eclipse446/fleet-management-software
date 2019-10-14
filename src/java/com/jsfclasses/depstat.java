
package com.jsfclasses;

import com.entities.Rubrique;
import java.math.BigDecimal;

class depstat {
     private BigDecimal montant;
     private Rubrique idrubrique;
    
    public depstat() {
    }

    public depstat(BigDecimal montant, Rubrique idrubrique) {
        this.montant = montant;
        this.idrubrique = idrubrique;
    }
    
    

    public Rubrique getIdrubrique() {
        return idrubrique;
    }

    public BigDecimal getMontant() {
        return montant;
    }

    public void setIdrubrique(Rubrique idrubrique) {
        this.idrubrique = idrubrique;
    }

    public void setMontant(BigDecimal montant) {
        this.montant = montant;
    }
    
    
    
}
