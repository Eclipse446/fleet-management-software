package com.jsfclasses;


import com.entities.CompagnieAssurance;
import com.entities.Marque;
import com.entities.Modele;
import com.entities.TypeAffectation;
import com.entities.TypeBoiteVitesse;
import com.entities.TypeCarburant;
import com.entities.TypeContratAssurance;
import com.entities.Vehicule;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.VehiculeFacade;
import java.io.Serializable;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;

import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "vehiculeController")
@SessionScoped
public class VehiculeController implements Serializable {

    private Vehicule current;
    private DataModel items = null;
    @EJB
    private com.sessionbean.VehiculeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
   
    public VehiculeController() {
    }

    
    
     /* nombre de vehicule par type affectation */
    
     public List<vehiculepartypeaffectation> nbvehiculetypeaffectation()
     {
          System.out.println("in  method vehicule par typeaffectation" );
          String q="";
          q="SELECT new com.jsfclasses.vehiculepartypeaffectation (count(m.immatriculation),m.idAffectation) FROM Vehicule m group by m.idAffectation";
          List l=(List)(Object)(ejbFacade.getSimpleList(q));
          System.out.println("done v par typee affectation");
          l=(List<vehiculepartypeaffectation>)(Object)l;
          return l;
     }
    
    
    
    
    
    
    /* nombre de vehicule par marque */
    
     public List<vehiculeparMarque> nbvehiculemarque()
     {
          System.out.println("in  method vehicule par marque" );
          String q="";
          q="SELECT new com.jsfclasses.vehiculeparMarque (count(m.immatriculation),m.idModele.idMarque) FROM Vehicule m group by m.idModele.idMarque";
          List l=(List)(Object)(ejbFacade.getSimpleList(q));
        System.out.println("done");
          l=(List<vehiculeparMarque>)(Object)l;
          return l;
     }
    
    
    
    
    
    /* cout d'acuiquisition totale */
     public BigDecimal coutacquisition()
     {
         
          String q="";
          q="SELECT sum(m.coutAcquisition) FROM Vehicule m";
          System.out.println(q);
          BigDecimal l= (BigDecimal)(ejbFacade.findSingleResult(q));
          return(l);
     
     }
    
    
    
    
    /* liste des vehicules avec des vignettes expirées */
    
       public List<Vehicule> getexpiredvignette(){
          Date date =new Date();
          
           String q="";
           q="SELECT m FROM Vehicule m WHERE m.dateExpirationVignette <= :date ";
           System.out.println(q);
           List<Vehicule> l=(List<Vehicule>)(Object)(ejbFacade.execCommandeList(q,"date",date));
         
           
           return l;
   
    }
       
       
     
        /* liste des vehicules avec des assurances expirées */
          public List<Vehicule> getexpiredinsurance(){
           Date date =new Date();
          
           String q="";
           q="SELECT m FROM Vehicule m WHERE m.dateExpirationAssurance <= :date ";
           System.out.println(q);
           List<Vehicule> l=(List<Vehicule>)(Object)(ejbFacade.execCommandeList(q,"date",date));
           return l;
   
    }
    
          
            /* liste des vehicules avec des viistetchniq expirées */
          public List<Vehicule> getexpiredtechnicalvisit(){
           Date date =new Date();
          
           String q="";
           q="SELECT m FROM Vehicule m WHERE m.dateExpirationVisiteTechnique <= :date ";
           System.out.println(q);
           List<Vehicule> l=(List<Vehicule>)(Object)(ejbFacade.execCommandeList(q,"date",date));
           return l;
   
    }  
    
    
    
    
    
    
    
    
    public Vehicule getCurrent() {
        return current;
    }

    public void setCurrent(Vehicule current) {
        this.current = current;
    }
    
    
    public int count()
    {
      return(ejbFacade.count());
    }
            
    
    
  /*--------------------------auto complete work -----------------------------------------------------------*/
    
 
  
    
    
    /* auto complete method  for modele***********************************************************************/
     
    public List<Modele> autocompleteModele(String code) {
         List<Modele> result = new ArrayList<Modele>();
         
        try{
       
        result=(List<Modele>) getLikeModeleByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<Modele>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<Modele> getLikeModeleByCode(String code){
          String q="";
           q="SELECT m FROM Modele m WHERE m.code like :code ";
           List<Modele> l=(List<Modele>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
   

 /* auto complete method  for boitevitesse***********************************************************************/
     
    public List<TypeBoiteVitesse> autocompleteboitevitesse(String code) {
         List<TypeBoiteVitesse> result = new ArrayList<TypeBoiteVitesse>();
         
        try{
       
        result=(List<TypeBoiteVitesse>) getLikeTypeBoiteVitesseByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<TypeBoiteVitesse>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<TypeBoiteVitesse> getLikeTypeBoiteVitesseByCode(String code){
          String q="";
           q="SELECT m FROM TypeBoiteVitesse m WHERE m.code like :code ";
           List<TypeBoiteVitesse> l=(List<TypeBoiteVitesse>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
      /* ---------------------------------end-------------------------------- */
  
      
      
      
  /* auto complete method  for  compagnie assurance***********************************************************************/
     
    public List<CompagnieAssurance> autocompleteassurance(String code) {
         List<CompagnieAssurance> result = new ArrayList<CompagnieAssurance>();
         
        try{
       
        result=(List<CompagnieAssurance>) getLikeAssuranceByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<CompagnieAssurance>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<CompagnieAssurance> getLikeAssuranceByCode(String code){
          String q="";
           q="SELECT m FROM CompagnieAssurance m WHERE m.code like :code ";
           List<CompagnieAssurance> l=(List<CompagnieAssurance>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
      /* ---------------------------------end-------------------------------- */  
      
      
      
      
      
      
      
      
      
   /* auto complete method  for type contrat assurance***********************************************************************/
     
    public List<TypeContratAssurance> autocompleteTypeContratAssurance(String code) {
         List<TypeContratAssurance> result = new ArrayList<TypeContratAssurance>();
         
        try{
       
        result=(List<TypeContratAssurance>) getLikeTypeContratAssuranceByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<TypeContratAssurance>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<TypeContratAssurance> getLikeTypeContratAssuranceByCode(String code){
          String q="";
           q="SELECT m FROM TypeContratAssurance m WHERE m.code like :code ";
           List<TypeContratAssurance> l=(List<TypeContratAssurance>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
      /* ---------------------------------end-------------------------------- */  
      
      
      
      
      
      
      
      
      
      
      
       /* auto complete method  for type affecation***********************************************************************/
     
    public List<TypeAffectation> autocompleteTypeAffectation(String code) {
         List<TypeAffectation> result = new ArrayList<TypeAffectation>();
         
        try{
       
        result=(List<TypeAffectation>) getLikeTypeAffectationByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<TypeAffectation>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<TypeAffectation> getLikeTypeAffectationByCode(String code){
          String q="";
           q="SELECT m FROM TypeAffectation m WHERE m.code like :code ";
           List<TypeAffectation> l=(List<TypeAffectation>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
      /* ---------------------------------end-------------------------------- */ 
      
      
      
      
      
            /* auto complete method  for type affecation***********************************************************************/
     
    public List<TypeCarburant> autocompleteTypeCarburant(String code) {
         List<TypeCarburant> result = new ArrayList<TypeCarburant>();
         
        try{
       
        result=(List<TypeCarburant>) getLikeTypeCarburantByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<TypeCarburant>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<TypeCarburant> getLikeTypeCarburantByCode(String code){
          String q="";
           q="SELECT m FROM TypeCarburant m WHERE m.code like :code ";
           List<TypeCarburant> l=(List<TypeCarburant>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
      /* ---------------------------------end-------------------------------- */ 
      
      
      
      
      
      
      
      

          public Vehicule getSelected() {
        if (current == null) {
            current = new Vehicule();
            selectedItemIndex = -1;
        }
        return current;
    }
      
      
      
      private VehiculeFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List_vehicule";
    }
    
   public  String prepareExpiredVignette(){
     recreateModel();
     return "expired_vig";
   }

    public  String prepareExpiredInsurance(){
     recreateModel();
     return "expired_ins";
   }
   
    
    public  String prepareExpiredtechnicalVisite(){
     recreateModel();
     return "expired_vt";
   }
   
    public String prepareView() {
        current = (Vehicule) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View_vehicule";
    }

    public String prepareCreate() {
        current = new Vehicule();
        selectedItemIndex = -1;
        return "Create_vehicule";
    }

     public String create() {
        try {
            current.setId(new Integer(0));
           getFacade().create(current);
           JsfUtil.addSuccessMessage("Vehicule crée");
           return prepareList();
        
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Vehicule) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit_vehicule";
    }

    public String update() {
        try {
            getFacade().edit(current);
           JsfUtil.addSuccessMessage("Vehicule modifié");
           return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
          
            return null;
        }
    }

    public String destroy() {
        current = (Vehicule) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return prepareList();
    }
 
   

    private void performDestroy() {
        try {
            getFacade().remove(current);
            System.out.println("in destroy vehicule");
           JsfUtil.addSuccessMessage("Vehicule supprimé");
        } catch (Exception e) {
          JsfUtil.addSuccessMessage("Erreur");
        }
    }

    private void recreateModel() {
        items = null;
    }
    
    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    
     private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

 

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    @FacesConverter(forClass = Vehicule.class)
    public static class VehiculeControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            VehiculeController controller = (VehiculeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "vehiculeController");
            return controller.ejbFacade.find(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuffer sb = new StringBuffer();
            sb.append(value);
            return sb.toString();
        }

        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Vehicule) {
                Vehicule o = (Vehicule) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + VehiculeController.class.getName());
            }
        }
    }
    
    
    
    
    
    
}
