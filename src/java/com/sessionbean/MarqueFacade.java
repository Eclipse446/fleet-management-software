/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessionbean;

import com.entities.Marque;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author S
 */
@Stateless
public class MarqueFacade extends AbstractFacade<Marque> {
    @PersistenceContext(unitName = "gestion_parc_vehiculesPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public MarqueFacade() {
        super(Marque.class);
    }
    
}
