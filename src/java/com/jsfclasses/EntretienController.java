package com.jsfclasses;

import com.entities.Depense;
import com.entities.Entretien;
import com.entities.Operation;
import com.entities.Rubrique;
import com.entities.Vehicule;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.EntretienFacade;

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

@ManagedBean(name = "entretienController")
@SessionScoped
public class EntretienController implements Serializable {

    private Entretien current;
    private DataModel items = null;
    @EJB
    private com.sessionbean.EntretienFacade ejbFacade;
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
    
    
    
    

    public EntretienController() {
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
      

    
      /* auto complete method  for operation**********************************************************************/
     
    public List<Operation> autocompleteoperation(String code) {
         List<Operation> result = new ArrayList<Operation>();
         
        try{
       
        result=(List<Operation>) getLikeoperationByCode(code);
        
        return result;
        }
        catch(Exception d)
        {
            System.out.println("in catch block Error occured"+d.getMessage());
            d.printStackTrace();
        result = new ArrayList<Operation>();
        
        return result;
        }}
    
    /* end auto complete method */
    
      public List<Operation> getLikeoperationByCode(String code){
          String q="";
           q="SELECT m FROM Operation m WHERE m.libelle like :code ";
           List<Operation> l=(List<Operation>)(Object)(ejbFacade.execCommandeList(q,"code",code+"%"));
         return l;
     }
    
    
    
    
    
    
    
    
    public Entretien getSelected() {
        if (current == null) {
            current = new Entretien();
            selectedItemIndex = -1;
        }
        return current;
    }

    private EntretienFacade getFacade() {
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
        return "List_entretien";
    }

    public String prepareView() {
        current = (Entretien) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new Entretien();
        selectedItemIndex = -1;
        return "Create_entretien";
    }

    public String create() {
        try {
            //* get rubrique items *//
            rub=rubriqueController.getItem(2);
            //* insert into depense *//
            dep=new Depense();
            dep.setDate(current.getDate());
            dep.setIdVehicule(current.getIdVehicule());
            dep.setIdRubrique(rub);
            dep.setMontant(current.getCout());
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
        current = (Entretien) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage("Modifié avec succés");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String destroy() {
        current = (Entretien) getItems().getRowData();
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

    @FacesConverter(forClass = Entretien.class)
    public static class EntretienControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            EntretienController controller = (EntretienController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "entretienController");
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
            if (object instanceof Entretien) {
                Entretien o = (Entretien) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + EntretienController.class.getName());
            }
        }
    }
}
