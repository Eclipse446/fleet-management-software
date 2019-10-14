/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sessionbean;

import com.entities.Utilisateur;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author S
 */
@Stateless
public class UtilisateurFacade extends AbstractFacade<Utilisateur> {
    @PersistenceContext(unitName = "gestion_parc_vehiculesPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }

    public UtilisateurFacade() {
        super(Utilisateur.class);
    }
    
     public String verifier_connexion(String login,String password)
       {
        try
        {
            
        Query query = em.createQuery("Select u from Utilisateur u where u.login= :login").setParameter("login", login);
        Utilisateur obj = (Utilisateur)query.getSingleResult();
        System.out.println("sortie"+login+password+query.toString());
        return "Acceuil";
       
       }
        catch(Exception e)
        {
          System.out.println("unable to reach object "+e.getCause()+e.getMessage());  
          
        
        }
        return null;
       }
    
}
