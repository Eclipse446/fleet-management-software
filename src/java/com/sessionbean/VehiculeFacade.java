/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessionbean;

import com.entities.Vehicule;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author S
 */
@Stateless
public class VehiculeFacade extends AbstractFacade<Vehicule> {
    @PersistenceContext(unitName = "gestion_parc_vehiculesPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public VehiculeFacade() {
        super(Vehicule.class);
    }
    
}
