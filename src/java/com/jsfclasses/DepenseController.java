package com.jsfclasses;

import com.entities.Chauffeur;
import com.entities.Depense;
import com.entities.Fournisseur;
import com.entities.Rubrique;
import com.entities.Vehicule;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.DepenseFacade;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "depenseController")
@SessionScoped
public class DepenseController implements Serializable {

    private Depense current;
    private DataModel items = null;
    @EJB
    private com.sessionbean.DepenseFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
   
    
    public DepenseController() {
        
    }

    
     /* auto complete method  for chauffeur**********************************************************************/
     
    public List<Chauffeur> autocompletechauffeur(String code) {
         List<Chauffeur> result = new ArrayList<Chauffeur>();
         
        try{
       
        result=(List<Chauffeur>) getLikechauffeurByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<Chauffeur>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<Chauffeur> getLikechauffeurByCode(String code){
          String q="";
           q="SELECT m FROM Chauffeur m WHERE m.cin like :code ";
           List<Chauffeur> l=(List<Chauffeur>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
         
    
    
    
    
     
     public List<depstat> getdeplist(){
          
           String q="";
           q="SELECT new com.jsfclasses.depstat(sum(m.montant),m.idRubrique) FROM Depense m group by m.idRubrique";
           System.out.println(q);
           List l=(List)(Object)(ejbFacade.getSimpleList(q));
           System.out.println("size ******** liste"+l.size());
  
l=(List<depstat>)(Object)l;
 return l;
     }
    
    
   
     /* auto complete method  for Vehicule**********************************************************************/
     
    public List<Vehicule> autocompleteVehicule(String code) {
         List<Vehicule> result = new ArrayList<Vehicule>();
         
        try{
       
        result=(List<Vehicule>) getLikeVehiculeByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<Vehicule>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<Vehicule> getLikeVehiculeByCode(String code){
          String q="";
           q="SELECT m FROM Vehicule m WHERE m.immatriculation like :code ";
           List<Vehicule> l=(List<Vehicule>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
    
    
    
    
    
      /* auto complete method  for rubrique**********************************************************************/
     
    public List<Rubrique> autocompleterubrique(String code) {
         List<Rubrique> result = new ArrayList<Rubrique>();
         
        try{
       
        result=(List<Rubrique>) getLikerubriqueByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<Rubrique>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<Rubrique> getLikerubriqueByCode(String code){
          String q="";
           q="SELECT m FROM Rubrique m WHERE m.libelle like :code ";
           List<Rubrique> l=(List<Rubrique>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
    
    

    
         /* auto complete method  for fournisseur**********************************************************************/
     
    public List<Fournisseur> autocompletefournisseur(String code) {
         List<Fournisseur> result = new ArrayList<Fournisseur>();
         
        try{
       
        result=(List<Fournisseur>) getLikefourByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<Fournisseur>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<Fournisseur> getLikefourByCode(String code){
          String q="";
           q="SELECT m FROM Fournisseur m WHERE m.libelle like :code ";
           List<Fournisseur> l=(List<Fournisseur>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
    
    
    
    
    public Depense getSelected() {
        if (current == null) {
            current = new Depense();
            selectedItemIndex = -1;
        }
        return current;
    }

    private DepenseFacade getFacade() {
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
        return "List_depense";
    }

    public String prepareView() {
        current = (Depense) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Depense();
        selectedItemIndex = -1;
        return "Create_depense";
    }

    public String create() {
        try {
            current.setId(new Integer(1));
            getFacade().create(current);
            JsfUtil.addSuccessMessage("effectué");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }
    
    
    public void createout(Depense obj) {
        System.out.println("in creation from outside");
        try {
            obj.setId(new Integer(1));
            getFacade().create(obj);
            System.out.println("created from outside");
        } catch (Exception e) {
           System.out.println("Error creation from outside");
        }
    }

    public String prepareEdit() {
        current = (Depense) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Modifié");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String destroy() {
        current = (Depense) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return prepareList();
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage("Supprimé");
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
        }
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

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
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

    @FacesConverter(forClass = Depense.class)
    public static class DepenseControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            DepenseController controller = (DepenseController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "depenseController");
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
            if (object instanceof Depense) {
                Depense o = (Depense) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + DepenseController.class.getName());
            }
        }
    }
}
