package com.jsfclasses;

import com.entities.Chauffeur;
import com.entities.ConsommationCarburant;
import com.entities.Depense;
import com.entities.Rubrique;
import com.entities.Vehicule;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.ConsommationCarburantFacade;

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

@ManagedBean(name = "consommationCarburantController")
@SessionScoped
public class ConsommationCarburantController implements Serializable {

    private ConsommationCarburant current;
    private DataModel items = null;
    @EJB
    private com.sessionbean.ConsommationCarburantFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @ManagedProperty(value="#{depenseController}")
    private DepenseController depenseController;
    private Depense dep;
    private Rubrique rub;
    @ManagedProperty(value="#{rubriqueController}")
    private RubriqueController rubriqueController;
    
    public RubriqueController getRubriqueController() {
        return rubriqueController;
    }

    public void setRubriqueController(RubriqueController rubriqueController) {
        this.rubriqueController = rubriqueController;
    }


    public DepenseController getDepenseController() {
        return depenseController;
    }

    public void setDepenseController(DepenseController depenseController) {
        this.depenseController = depenseController;
    }
  
    
    
    
    public ConsommationCarburantController() {
    }

      public Double getsum(){
          
           String q="";
           q="SELECT sum(m.volume) FROM ConsommationCarburant m ";
           System.out.println(q);
           Double l= (Double)(ejbFacade.findSingleResult(q));
           return(l);
     
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
         
      
    
      /* consommation par month */
         
         public List consopermonth(){
          
           String q="";
           q="SELECT sum(m.volume),m.date a FROM ConsommationCarburant m group by a;";
          
           List l= (List)(ejbFacade.getSimpleList(q));
            System.out.println("here in conso");
           return(l);
     
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
    
    
    

    
    
    
    public ConsommationCarburant getSelected() {
        if (current == null) {
            current = new ConsommationCarburant();
            selectedItemIndex = -1;
        }
        return current;
    }

    private ConsommationCarburantFacade getFacade() {
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
        return "List_consommationcarburant";
    }

    public String prepareView() {
        current = (ConsommationCarburant) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new ConsommationCarburant();
        selectedItemIndex = -1;
        return "Create_consommationcarburant";
    }

    public String create() {
        try {
            //* get rubrique items *//
            rub=rubriqueController.getItem(4);
            //* insert into depense *//
            dep=new Depense();
            dep.setDate(current.getDate());
            dep.setIdVehicule(current.getIdVehicule());
            dep.setIdRubrique(rub);
            dep.setMontant(current.getMontant());
            depenseController.createout(dep);
            
            
            
            
            
            
            current.setId(new Integer(1));
            getFacade().create(current);
            JsfUtil.addSuccessMessage("Enregistré");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String prepareEdit() {
        current = (ConsommationCarburant) getItems().getRowData();
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
        current = (ConsommationCarburant) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return "List";
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

    @FacesConverter(forClass = ConsommationCarburant.class)
    public static class ConsommationCarburantControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ConsommationCarburantController controller = (ConsommationCarburantController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "consommationCarburantController");
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
            if (object instanceof ConsommationCarburant) {
                ConsommationCarburant o = (ConsommationCarburant) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ConsommationCarburantController.class.getName());
            }
        }
    }
}
