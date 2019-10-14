
package com.jsfclasses;
import com.entities.Utilisateur;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;



@ManagedBean(name="authentification")
@SessionScoped 
public class authentificationBean implements Serializable {
  
    private String login;
    private String password;
    @EJB
    private com.sessionbean.UtilisateurFacade ejbFacade;
    public authentificationBean() {
    }



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

     

  public String seconnecter()
  {
    
 try
   {
    Utilisateur exist=(Utilisateur)ejbFacade.findByParameterSingleResult("Select u from Utilisateur u where u.login= :login","login",login);
    System.out.println("sortie"+login+password);
    if(exist.getPassword().equals(password)){
     return "Acceuil";
   }
     FacesContext facesContext = FacesContext.getCurrentInstance();
     FacesMessage facesMessage = new FacesMessage("Erreur de connexion", "mot de passe incorrecte.");
     facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
     facesContext.addMessage(null, facesMessage);
     return "index";
   
   }
   catch(Exception e)
   {
    FacesContext facesContext = FacesContext.getCurrentInstance();
    FacesMessage facesMessage = new FacesMessage("Erreur de connexion", "utilisateur non trouvé");
    facesMessage.setSeverity(FacesMessage.SEVERITY_ERROR);
    facesContext.addMessage(null, facesMessage);
    System.out.println("unable to reach object "+e.getCause()+e.getMessage());
    return "index";
   }

  }
  
  
  
  
  public String Deconnecter()
  {
  
    return "index";  
  }
    
}
