package com.jsfclasses;

import com.entities.TypeContratAssurance;
import com.jsfclasses.util.JsfUtil;
import com.jsfclasses.util.PaginationHelper;
import com.sessionbean.TypeContratAssuranceFacade;

import java.io.Serializable;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@ManagedBean(name = "typeContratAssuranceController")
@SessionScoped
public class TypeContratAssuranceController implements Serializable {

    private TypeContratAssurance current;
    private DataModel items = null;
    @EJB
    private com.sessionbean.TypeContratAssuranceFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;

    public TypeContratAssuranceController() {
    }

    public TypeContratAssurance getSelected() {
        if (current == null) {
            current = new TypeContratAssurance();
            selectedItemIndex = -1;
        }
        return current;
    }

    private TypeContratAssuranceFacade getFacade() {
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
        return "List_contratassurance";
    }

    public String prepareView() {
        current = (TypeContratAssurance) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new TypeContratAssurance();
        selectedItemIndex = -1;
        return "Create_contratassurance";
    }

    public String create() {
        try {
            current.setId(new Integer(1));
            getFacade().create(current);
            JsfUtil.addSuccessMessage("type contrat crée");
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage("Erreur");
            return null;
        }
    }

    public String prepareEdit() {
        current = (TypeContratAssurance) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit_contratassurance";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle3").getString("TypeContratAssuranceUpdated"));
            return prepareList();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle3").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (TypeContratAssurance) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreateModel();
        return "List_contratassurance";
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

    @FacesConverter(forClass = TypeContratAssurance.class)
    public static class TypeContratAssuranceControllerConverter implements Converter {

        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            TypeContratAssuranceController controller = (TypeContratAssuranceController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "typeContratAssuranceController");
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
            if (object instanceof TypeContratAssurance) {
                TypeContratAssurance o = (TypeContratAssurance) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + TypeContratAssuranceController.class.getName());
            }
        }
    }
}
