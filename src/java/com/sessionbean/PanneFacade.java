/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessionbean;

import com.entities.Panne;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author S
 */
@Stateless
public class PanneFacade extends AbstractFacade<Panne> {
    @PersistenceContext(unitName = "gestion_parc_vehiculesPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public PanneFacade() {
        super(Panne.class);
    }
    
}
