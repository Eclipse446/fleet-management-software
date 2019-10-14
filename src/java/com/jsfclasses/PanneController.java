package com.jsfclasses;

import com.entities.Chauffeur;
import com.entities.Panne;
import com.entities.TypePanne;
import com.entities.Vehicule;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.PanneFacade;
import com.jsfclasses.VehiculeController;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

@ManagedBean(name = "panneController")
@SessionScoped
public class PanneController implements Serializable {

     private Panne current;
     private DataModel items = null;
     private List<DataModel> filteredCars;
    @EJB
    private com.sessionbean.PanneFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @ManagedProperty(value="#{vehiculeController}")
    private VehiculeController vehiculeController;
    private List<String> villes=Arrays.asList("Tunis","Sousse", "kairouan","jendouba","Beja","Gafsa","kasserine","Tatouine","Mednin","Sfax","Tozeur");
     

    public PanneController() {

    }

    public List<String> getVilles() {

        return villes;
    }

    public void setVilles(List<String> villes) {
        this.villes = villes;
    }

    public VehiculeController getVehiculeController() {
        return vehiculeController;
    }

    public void setVehiculeController(VehiculeController vehiculeController) {
        this.vehiculeController = vehiculeController;
    }
    
    
    

     public float count()
    {
      
      int d=ejbFacade.count();
      int a=vehiculeController.count();
      float c=d*100/a;
      System.out.println("operation "+c);
      return(c);
    }
    
   
     
     
     
     
     
     public List<DataModel> getFilteredCars() {
        return filteredCars;
    }

    public void setFilteredCars(List<DataModel> filteredCars) {
        this.filteredCars = filteredCars;
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
         
      
      
     /* auto complete method  for typepanne**********************************************************************/
     
    public List<TypePanne> autocompletetypepanne(String code) {
         List<TypePanne> result = new ArrayList<TypePanne>();
         
        try{
       
        result=(List<TypePanne>) getLikepanneByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<TypePanne>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<TypePanne> getLikepanneByCode(String code){
          String q="";
           q="SELECT m FROM TypePanne m WHERE m.libelle like :code ";
           List<TypePanne> l=(List<TypePanne>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
           
      
      
      
      
      
      
      
      
    
    
    
    
    
    public Panne getSelected() {
        if (current == null) {
            current = new Panne();
            selectedItemIndex = -1;
        }
        return current;
    }

    private PanneFacade getFacade() {
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
        return "List_panne";
    }

    public String prepareView() {
        current = (Panne) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Panne();
        selectedItemIndex = -1;
        return "Create_panne";
    }

    public String create() {
        try {
            if(current.getDepart().equals(current.getDestination()))
            {
            JsfUtil.addErrorMessage("veuillez vérifier la destination");
             return null;
            }
            
            else
            {
            current.setId(new Integer(1));
            getFacade().create(current);
            JsfUtil.addSuccessMessage("effectué");
            return prepareList();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String prepareEdit() {
        current = (Panne) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit_panne";
    }

    public String update() {
        try {
             if(current.getDepart().equals(current.getDestination()))
            {
            JsfUtil.addErrorMessage("veuillez vérifier la destination");
             return null;
            }
            
            else
            {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("effectué");
            return prepareList();
            }
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String destroy() {
        current = (Panne) getItems().getRowData();
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
            JsfUtil.addSuccessMessage("effectué");
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

    @FacesConverter(forClass = Panne.class)
    public static class PanneControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            PanneController controller = (PanneController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "panneController");
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
            if (object instanceof Panne) {
                Panne o = (Panne) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + PanneController.class.getName());
            }
        }
    }
}
